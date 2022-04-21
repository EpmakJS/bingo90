package com.example.demo.strip.service

import com.example.demo.strip.model.BaseStripCompositeElement
import com.example.demo.strip.model.BaseStripElement
import com.example.demo.strip.model.Cell
import com.example.demo.strip.model.Row
import com.example.demo.strip.model.Strip
import com.example.demo.strip.model.Ticket
import com.example.demo.strip.repository.ICellRepository
import com.example.demo.strip.repository.IRowRepository
import com.example.demo.strip.repository.IStripRepository
import com.example.demo.strip.repository.ITicketRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

@Component
internal class StripCreator(
    private val stripRepository: IStripRepository,
    private val ticketRepository: ITicketRepository,
    private val rowRepository: IRowRepository,
    private val cellRepository: ICellRepository
) : IStripCreator {

    override fun create() = stripRepository.save(Strip()).apply { fill(createTickets()) }

    private fun Row.createCells(): MutableList<Cell> {
        val cells = mutableListOf<Cell>()

        for (i in 0..CELLS_IN_ROW_AMOUNT.minus(1)) {
            cells.add(Cell().setFieldsAndSave(i, this, cellRepository))
        }

        return cells
    }

    private fun Ticket.createRows(): MutableList<Row> {
        val rows = mutableListOf<Row>()

        for (i in 0..ROWS_IN_TICKET_AMOUNT.minus(1)) {
            rows.add(Row().setFieldsAndSave(i, this, rowRepository).apply { fill(createCells()) })
        }

        return rows
    }

    private fun Strip.createTickets(): MutableList<Ticket> {
        val tickets = mutableListOf<Ticket>()

        for (i in 0..TICKETS_IN_STRIP_AMOUNT.minus(1)) {
            tickets.add(Ticket().setFieldsAndSave(i, this, ticketRepository).apply { fill(createRows()) })
        }

        return tickets
    }

    private fun <T : BaseStripElement, R : BaseStripCompositeElement> T.setFieldsAndSave(
        order: Int,
        parentEntity: R,
        repo: JpaRepository<T, Long>
    ) =
        apply {
            uiOrder = order
            parent = parentEntity
        }.let(repo::save)

    companion object {

        const val TICKETS_IN_STRIP_AMOUNT = 6
        const val ROWS_IN_TICKET_AMOUNT = 3
        const val CELLS_IN_ROW_AMOUNT = 9
    }
}
