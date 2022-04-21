package com.example.demo

import com.example.demo.strip.builder.StripBuilder
import com.example.demo.strip.model.Cell
import com.example.demo.strip.model.Row
import com.example.demo.strip.model.Ticket
import com.example.demo.strip.model.transformer.StripTransformer
import com.example.demo.strip.repository.ICellRepository
import com.example.demo.strip.repository.IStripRepository
import com.example.demo.strip.service.IStripCreator
import com.example.demo.strip.service.StripService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class StripServiceTest {

    private val stripRepository = mockk<IStripRepository>()
    private val cellRepository = mockk<ICellRepository>()
    private val stripCreator = mockk<IStripCreator>()
    private val stripTransformer = mockk<StripTransformer>()
    private val stripBuilder = mockk<StripBuilder>()

    private val stripService = StripService(stripRepository, cellRepository, stripCreator, stripTransformer)

    @Test
    fun `verify each row include exact 5 non-null values`() {

        every { stripBuilder.build() } returns getTestStripBuilderDto()
        every { stripCreator.create() } returns stripWithEmptyCells
        every { cellRepository.save(any()) } answers { firstArg() }
        every { stripRepository.save(any()) } answers { firstArg() }

        val result = stripService.create(1)

        val rowValuesList = (result.first().elements as MutableList<Ticket>).flatMap { ticket ->
            (ticket.elements as MutableList<Row>).map { row ->
                (row.elements as MutableList<Cell>).map { cell -> cell.value } } }

        val rowsNotNullValues = rowValuesList.map { it.filter { el -> el != null } }

        for (rowValues in rowsNotNullValues) {
            assertEquals(rowValues.size, 5)
        }

        verify { stripCreator.create() }
        verify { stripCreator.create() }
    }

    companion object {

        val stripWithEmptyCells = getTestStrip().apply {
            (elements as MutableList<Ticket>).onEach { ticket ->
            (ticket.elements as MutableList<Row>).onEach { row ->
                (row.elements as MutableList<Cell>).onEach { cell -> cell.value = null } } }
        }
    }

}
