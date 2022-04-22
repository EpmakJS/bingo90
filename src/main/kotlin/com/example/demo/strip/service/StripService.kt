package com.example.demo.strip.service

import com.example.demo.strip.builder.ColumnBuilderDto
import com.example.demo.strip.builder.StripBuilder
import com.example.demo.strip.model.entity.BaseStripElement
import com.example.demo.strip.model.entity.Cell
import com.example.demo.strip.model.entity.Row
import com.example.demo.strip.model.entity.Strip
import com.example.demo.strip.model.entity.Ticket
import com.example.demo.strip.model.dto.StripDto
import com.example.demo.strip.model.transformer.StripTransformer
import com.example.demo.strip.repository.ICellRepository
import com.example.demo.strip.repository.IStripRepository
import org.springframework.stereotype.Service
import java.security.SecureRandom
import javax.persistence.EntityNotFoundException
import javax.transaction.Transactional

@Service
internal class StripService(
    private val stripRepository: IStripRepository,
    private val cellRepository: ICellRepository,
    private val stripCreator: IStripCreator,
    private val stripTransformer: StripTransformer
) : IStripService {

    override fun lookup(): List<StripDto> = stripRepository.findAll().map { stripTransformer.transform(it) }

    override fun getById(id: Long): StripDto = stripRepository.findById(id)
        .orElseThrow { EntityNotFoundException() }
        .let { stripTransformer.transform(it) }

    // Initially, the method did not return anything. After writing the mock data and at the end of writing the test,
    // I set the return type, the one for which the test was written.
    // In order not to rewrite the test, I decided to return an entity, not a DTO.
    @Transactional
    override fun create(batchSize: Int): List<Strip> {
        val strips = mutableListOf<Strip>()

        for (i in 1..batchSize) {
            val stripBuilderDto = StripBuilder().build()
            val stripEntity = stripCreator.create()

            stripBuilderDto.tickets.forEach { ticketDto ->
                val ticket = stripEntity.elements.find { it.uiOrder == ticketDto.uiOrder } as Ticket
                val columns = ticketDto.columns

                fillFirstRow(getRowByIndex(ticket.elements, FIRST_ROW_INDEX), columns)
                fillSecondRow(getRowByIndex(ticket.elements, SECOND_ROW_INDEX), columns)
                fillThirdRow(getRowByIndex(ticket.elements, THIRD_ROW_INDEX), columns)
            }
            strips.add(stripEntity)
        }
        return strips.toList()
    }

    private fun fillFirstRow(firstRow: MutableList<Cell>, columns: MutableList<ColumnBuilderDto>) {
        val fullColumnList = columns.filter { column -> column.values.size == NUMBERS_IN_COLUMN }.toMutableList()
        firstRow.fillRowWithColumnValues(fullColumnList)
        fillRemainingEmptyCells(firstRow, columns)
    }

    private fun fillSecondRow(secondRow: MutableList<Cell>, columns: MutableList<ColumnBuilderDto>) {
        val columnList = columns.filter { column -> column.values.size == NUMBERS_IN_COLUMN.minus(1) }.toMutableList()
        secondRow.fillRowWithColumnValues(columnList)
        fillRemainingEmptyCells(secondRow, columns)
    }

    private fun fillThirdRow(thirdRow: MutableList<Cell>, columns: MutableList<ColumnBuilderDto>) {
        val columnList = columns.filter { column -> column.values.size == NUMBERS_IN_COLUMN.minus(2) }.toMutableList()

        columnList.forEach { column ->
            thirdRow.find { cell -> cell.uiOrder == column.uiOrder }
                ?.run {
                    value = column.values.removeFirstOrNull()
                    cellRepository.save(this)
                }
        }
    }

    private fun MutableList<Cell>.fillRowWithColumnValues(columns: MutableList<ColumnBuilderDto>) =
        map { cell ->
            columns.find { column -> column.uiOrder == cell.uiOrder }
                .let { cell.value = it?.values?.removeFirstOrNull() }
                .also { cellRepository.save(cell) }
        }

    private fun fillRemainingEmptyCells(rowCells: MutableList<Cell>, columns: MutableList<ColumnBuilderDto>) {
        var cellsAmountShouldBeFilled = NUMBERS_IN_ROW.minus(rowCells.count { it.value != null })
        val emptyCells = rowCells.filter { it.value == null }.toMutableList()

        while (cellsAmountShouldBeFilled != ZERO) {
            fillEmptyCell(emptyCells, columns)
            cellsAmountShouldBeFilled--
        }
    }

    private fun fillEmptyCell(emptyCells: MutableList<Cell>, columns: MutableList<ColumnBuilderDto>) {
        val cell = emptyCells.getRandomCell()
        val column = columns[cell.uiOrder]

        if (column.values.isNotEmpty()) {
            cell.value = column.values.removeFirst()
            cellRepository.save(cell)
        } else {
            emptyCells.remove(cell)
            fillEmptyCell(emptyCells, columns)
        }
    }

    private fun List<Cell>.getRandomCell() = get(SecureRandom().nextInt(size))

    private fun getRowByIndex(rows: MutableList<BaseStripElement>, rowIndex: Int) =
        (rows[rowIndex] as Row).elements as MutableList<Cell>

    companion object {

        private const val ZERO = 0
        private const val NUMBERS_IN_ROW = 5
        private const val NUMBERS_IN_COLUMN = 3
        private const val FIRST_ROW_INDEX = 0
        private const val SECOND_ROW_INDEX = 1
        private const val THIRD_ROW_INDEX = 2
    }

}
