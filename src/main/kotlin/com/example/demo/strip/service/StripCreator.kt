package com.example.demo.strip.service

import com.example.demo.strip.model.Cell
import com.example.demo.strip.model.Row
import com.example.demo.strip.model.Strip
import com.example.demo.strip.model.Ticket
import com.example.demo.strip.repository.ICellRepository
import com.example.demo.strip.repository.IRowRepository
import com.example.demo.strip.repository.IStripRepository
import com.example.demo.strip.repository.ITicketRepository
import org.springframework.stereotype.Component

@Component
internal class StripCreator(
    private val stripRepository: IStripRepository,
    private val ticketRepository: ITicketRepository,
    private val rowRepository: IRowRepository,
    private val cellRepository: ICellRepository
) : IStripCreator {

    override fun create() = stripRepository.save(Strip()).apply { fill(createTickets()) }

    // TODO: Refactor

    private fun Row.createCells(): MutableList<Cell> {
        val cells = mutableListOf<Cell>()

        for (i in 0..CELLS_IN_ROW_AMOUNT.minus(1)) {
            cells.add(createCell(i, this))
        }

        return cells
    }

    private fun Ticket.createRows(): MutableList<Row> {
        val rows = mutableListOf<Row>()

        for (i in 0..ROWS_IN_TICKET_AMOUNT.minus(1)) {
            rows.add(createRow(i, this).apply { fill(createCells()) })
        }

        return rows
    }

    private fun Strip.createTickets(): MutableList<Ticket> {
        val tickets = mutableListOf<Ticket>()

        for (i in 0..TICKETS_IN_STRIP_AMOUNT.minus(1)) {
            tickets.add(createTicket(i, this).apply { fill(createRows()) })
        }

        return tickets
    }

    private fun createCell(order: Int, parentEntity: Row) =
        Cell().apply {
            uiOrder = order
            parent = parentEntity
        }.let { cellRepository.save(it) }

    private fun createRow(order: Int, parentEntity: Ticket) =
        Row().apply {
            uiOrder = order
            parent = parentEntity
        }.let { rowRepository.save(it) }

    private fun createTicket(order: Int, parentEntity: Strip) =
        Ticket().apply {
            uiOrder = order
            parent = parentEntity
        }.let { ticketRepository.save(it) }

    companion object {

        const val TICKETS_IN_STRIP_AMOUNT = 6
        const val ROWS_IN_TICKET_AMOUNT = 3
        const val CELLS_IN_ROW_AMOUNT = 9
    }
}
