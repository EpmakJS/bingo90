package com.example.demo

import com.example.demo.strip.builder.ColumnBuilderDto
import com.example.demo.strip.builder.StripBuilderDto
import com.example.demo.strip.builder.TicketBuilderDto
import com.example.demo.strip.model.Cell
import com.example.demo.strip.model.ElementType.CELL
import com.example.demo.strip.model.ElementType.ROW
import com.example.demo.strip.model.ElementType.STRIP
import com.example.demo.strip.model.ElementType.TICKET
import com.example.demo.strip.model.Row
import com.example.demo.strip.model.Strip
import com.example.demo.strip.model.Ticket

fun createDefaultStripBuilderDto(tickets: MutableList<TicketBuilderDto>) = StripBuilderDto().apply {
    this.tickets = tickets
}

fun createDefaultTicketBuilderDto(uiOrder: Int = 0, columns: MutableList<ColumnBuilderDto>) = TicketBuilderDto().apply {
    this.uiOrder = uiOrder
    this.columns = columns
}

fun createDefaultColumnBuilderDto(uiOrder: Int = 0, values: MutableList<Int>) = ColumnBuilderDto().apply {
    this.uiOrder = uiOrder
    this.values = values
}

fun getTestStripBuilderDto() = createDefaultStripBuilderDto(getListTicketBuilderDto())

fun getListTicketBuilderDto() = mutableListOf(
    createDefaultTicketBuilderDto(0, getListColumnBuilderDtoForFirstTicket()),
    createDefaultTicketBuilderDto(1, getListColumnBuilderDtoForSecondTicket()),
    createDefaultTicketBuilderDto(2, getListColumnBuilderDtoForThirdTicket()),
    createDefaultTicketBuilderDto(3, getListColumnBuilderDtoForFourthTicket()),
    createDefaultTicketBuilderDto(4, getListColumnBuilderDtoForFifthTicket()),
    createDefaultTicketBuilderDto(5, getListColumnBuilderDtoForSixthTicket()),
)

fun getListColumnBuilderDtoForFirstTicket() = mutableListOf(
    createDefaultColumnBuilderDto(0, mutableListOf(4)),
    createDefaultColumnBuilderDto(1, mutableListOf(13)),
    createDefaultColumnBuilderDto(2, mutableListOf(20, 22)),
    createDefaultColumnBuilderDto(3, mutableListOf(32, 35, 36)),
    createDefaultColumnBuilderDto(4, mutableListOf(43, 44)),
    createDefaultColumnBuilderDto(5, mutableListOf(56)),
    createDefaultColumnBuilderDto(6, mutableListOf(64, 69)),
    createDefaultColumnBuilderDto(7, mutableListOf(71, 79)),
    createDefaultColumnBuilderDto(8, mutableListOf(85))
)

fun getListColumnBuilderDtoForSecondTicket() = mutableListOf(
    createDefaultColumnBuilderDto(0, mutableListOf(1, 7)),
    createDefaultColumnBuilderDto(1, mutableListOf(12)),
    createDefaultColumnBuilderDto(2, mutableListOf(26)),
    createDefaultColumnBuilderDto(3, mutableListOf(34)),
    createDefaultColumnBuilderDto(4, mutableListOf(41, 45)),
    createDefaultColumnBuilderDto(5, mutableListOf(52, 58)),
    createDefaultColumnBuilderDto(6, mutableListOf(60, 61, 63)),
    createDefaultColumnBuilderDto(7, mutableListOf(72)),
    createDefaultColumnBuilderDto(8, mutableListOf(86, 88))
)

fun getListColumnBuilderDtoForThirdTicket() = mutableListOf(
    createDefaultColumnBuilderDto(0, mutableListOf(5)),
    createDefaultColumnBuilderDto(1, mutableListOf(14, 19)),
    createDefaultColumnBuilderDto(2, mutableListOf(24, 29)),
    createDefaultColumnBuilderDto(3, mutableListOf(38, 39)),
    createDefaultColumnBuilderDto(4, mutableListOf(42)),
    createDefaultColumnBuilderDto(5, mutableListOf(53)),
    createDefaultColumnBuilderDto(6, mutableListOf(67)),
    createDefaultColumnBuilderDto(7, mutableListOf(70, 76)),
    createDefaultColumnBuilderDto(8, mutableListOf(83, 84, 90))
)

fun getListColumnBuilderDtoForFourthTicket() = mutableListOf(
    createDefaultColumnBuilderDto(0, mutableListOf(3, 9)),
    createDefaultColumnBuilderDto(1, mutableListOf(10)),
    createDefaultColumnBuilderDto(2, mutableListOf(23, 28)),
    createDefaultColumnBuilderDto(3, mutableListOf(37)),
    createDefaultColumnBuilderDto(4, mutableListOf(40)),
    createDefaultColumnBuilderDto(5, mutableListOf(54, 59)),
    createDefaultColumnBuilderDto(6, mutableListOf(65, 68)),
    createDefaultColumnBuilderDto(7, mutableListOf(77)),
    createDefaultColumnBuilderDto(8, mutableListOf(81, 87, 89))
)

fun getListColumnBuilderDtoForFifthTicket() = mutableListOf(
    createDefaultColumnBuilderDto(0, mutableListOf(2)),
    createDefaultColumnBuilderDto(1, mutableListOf(11, 18)),
    createDefaultColumnBuilderDto(2, mutableListOf(27)),
    createDefaultColumnBuilderDto(3, mutableListOf(30, 33)),
    createDefaultColumnBuilderDto(4, mutableListOf(46, 47, 48)),
    createDefaultColumnBuilderDto(5, mutableListOf(55, 57)),
    createDefaultColumnBuilderDto(6, mutableListOf(62)),
    createDefaultColumnBuilderDto(7, mutableListOf(73, 78)),
    createDefaultColumnBuilderDto(8, mutableListOf(82))
)

fun getListColumnBuilderDtoForSixthTicket() = mutableListOf(
    createDefaultColumnBuilderDto(0, mutableListOf(6, 8)),
    createDefaultColumnBuilderDto(1, mutableListOf(15, 16, 17)),
    createDefaultColumnBuilderDto(2, mutableListOf(21, 25)),
    createDefaultColumnBuilderDto(3, mutableListOf(31)),
    createDefaultColumnBuilderDto(4, mutableListOf(49)),
    createDefaultColumnBuilderDto(5, mutableListOf(50, 51)),
    createDefaultColumnBuilderDto(6, mutableListOf(66)),
    createDefaultColumnBuilderDto(7, mutableListOf(74, 75)),
    createDefaultColumnBuilderDto(8, mutableListOf(80))
)

fun createDefaultStrip(tickets: MutableList<Ticket> = mutableListOf()) = Strip().apply {
    id = 1L
    uiOrder = 0
    parent = null
    type = STRIP
    fill(tickets)
}

fun createDefaultTicket(order: Int = 0, rows: MutableList<Row> = mutableListOf()) = Ticket().apply {
    id = 1L
    uiOrder = order
    parent = createDefaultStrip()
    type = TICKET
    elements.addAll(rows)
}

fun createDefaultRow(order: Int = 0, cells: MutableList<Cell> = mutableListOf()) = Row().apply {
    id = 1L
    uiOrder = order
    parent = createDefaultTicket()
    type = ROW
    elements.addAll(cells)
}

fun createDefaultCell(order: Int, value: Int? = null) = Cell().apply {
    id = 1L
    uiOrder = order
    parent = createDefaultRow()
    type = CELL
    this.value = value
}

fun getTestStrip() = createDefaultStrip(getListOfTickets())

fun getListOfTickets() = mutableListOf(
    createDefaultTicket(0, getListOfRowsForFirstTicket()),
    createDefaultTicket(1, getListOfRowsForSecondTicket()),
    createDefaultTicket(2, getListOfRowsForThirdTicket()),
    createDefaultTicket(3, getListOfRowsForFourthTicket()),
    createDefaultTicket(4, getListOfRowsForFifthTicket()),
    createDefaultTicket(5, getListOfRowsForSixthTicket()),
)

fun getListOfRowsForFirstTicket() = mutableListOf(
    createDefaultRow(0, generateListOfCell(value0 = 4, value1 = 13, value3 = 32, value7 = 79, value8 = 81)),
    createDefaultRow(1, generateListOfCell(value2 = 20, value3 = 35, value4 = 43, value6 = 64, value8 = 85)),
    createDefaultRow(2, generateListOfCell(value2 = 22, value3 = 36, value4 = 44, value5 = 56, value6 = 69)),
)

fun getListOfRowsForSecondTicket() = mutableListOf(
    createDefaultRow(0, generateListOfCell(value0 = 1, value1 = 12, value6 = 60, value7 = 72, value8 = 86)),
    createDefaultRow(1, generateListOfCell(value0 = 7, value2 = 26, value4 = 41, value5 = 52, value6 = 61)),
    createDefaultRow(2, generateListOfCell(value3 = 34, value4 = 45, value5 = 58, value6 = 63, value8 = 88)),
)

fun getListOfRowsForThirdTicket() = mutableListOf(
    createDefaultRow(0, generateListOfCell(value4 = 42, value5 = 53, value6 = 67, value7 = 70, value8 = 83)),
    createDefaultRow(1, generateListOfCell(value1 = 14, value2 = 24, value3 = 38, value7 = 76, value8 = 84)),
    createDefaultRow(2, generateListOfCell(value0 = 5, value1 = 19, value2 = 29, value3 = 39, value8 = 90)),
)

fun getListOfRowsForFourthTicket() = mutableListOf(
    createDefaultRow(0, generateListOfCell(value2 = 28, value4 = 40, value7 = 77, value8 = 81)),
    createDefaultRow(1, generateListOfCell(value0 = 3, value3 = 37, value5 = 54, value6 = 65, value8 = 87)),
    createDefaultRow(2, generateListOfCell(value0 = 9, value1 = 10, value5 = 59, value6 = 68, value8 = 89)),
)

fun getListOfRowsForFifthTicket() = mutableListOf(
    createDefaultRow(0, generateListOfCell(value0 = 2, value2 = 27, value4 = 46, value5 = 55, value8 = 82)),
    createDefaultRow(1, generateListOfCell(value0 = 11, value3 = 30, value4 = 47, value5 = 57, value7 = 73)),
    createDefaultRow(2, generateListOfCell(value1 = 18, value3 = 33, value4 = 48, value6 = 62, value7 = 78)),
)

fun getListOfRowsForSixthTicket() = mutableListOf(
    createDefaultRow(0, generateListOfCell(value1 = 15, value2 = 21, value5 = 50, value7 = 75, value8 = 84)),
    createDefaultRow(1, generateListOfCell(value0 = 6, value1 = 16, value3 = 31, value5 = 51, value6 = 66)),
    createDefaultRow(2, generateListOfCell(value0 = 8, value1 = 17, value2 = 25, value4 = 49, value8 = 80)),
)

fun generateListOfCell(
    value0: Int? = null,
    value1: Int? = null,
    value2: Int? = null,
    value3: Int? = null,
    value4: Int? = null,
    value5: Int? = null,
    value6: Int? = null,
    value7: Int? = null,
    value8: Int? = null
) = mutableListOf(
    createDefaultCell(0, value0),
    createDefaultCell(1, value1),
    createDefaultCell(2, value2),
    createDefaultCell(3, value3),
    createDefaultCell(4, value4),
    createDefaultCell(5, value5),
    createDefaultCell(6, value6),
    createDefaultCell(7, value7),
    createDefaultCell(8, value8),
)

