package com.example.demo.strip.builder

import java.security.SecureRandom

class StripBuilder : IStripBuilder {

    override fun build(): StripBuilderDto {
        val numbersMap: Map<Int, MutableList<Int>> = mapOf(
            generatePair(0, 1, 9),
            generatePair(1, 10, 19),
            generatePair(2, 20, 29),
            generatePair(3, 30, 39),
            generatePair(4, 40, 49),
            generatePair(5, 50, 59),
            generatePair(6, 60, 69),
            generatePair(7, 70, 79),
            generatePair(8, 80, 90)
        )

        val stripBuilderDto = StripBuilderDto().apply {
            for (i in 0..TICKETS_IN_STRIP_AMOUNT.minus(1)) {
                tickets.add(TicketBuilderDto().apply { uiOrder = i })
            }

            tickets.run {
                addNumbersToEachTicketFromEachTen(numbersMap)
                addNumberFromLastTenToRandomTicket(numbersMap.values.last().removeFirst())
                fillTicketsWithRemainingNumbers(numbersMap)
                sortNumbersInColumns()
            }
        }

        return stripBuilderDto
    }

    private fun List<TicketBuilderDto>.addNumbersToEachTicketFromEachTen(numbersMap: Map<Int, MutableList<Int>>) =
        onEach { ticket -> ticket.createAndFillColumnsOfTicket(numbersMap.takeOneNumberFromEachTen()) }

    private fun TicketBuilderDto.createAndFillColumnsOfTicket(numbers: List<Int?>): MutableList<ColumnBuilderDto> {
        for (i in 0..COLUMNS_IN_TICKET_AMOUNT.minus(1)) {
            columns.add(ColumnBuilderDto().apply { uiOrder = i })
        }

        return columns.onEachIndexed { index, column -> column.values.add(numbers[index]!!) }
    }

    private fun List<TicketBuilderDto>.addNumberFromLastTenToRandomTicket(number: Int) =
        apply { getRandomTicket().columns.last().values.add(number) }

    private fun List<TicketBuilderDto>.fillTicketsWithRemainingNumbers(
        numbersMap: Map<Int, MutableList<Int>>
    ): List<TicketBuilderDto> {
        for (i in 1..3) {
            numbersMap.takeOneNumberFromEachTen()
                .forEachIndexed { index, number -> addNumberToRandomTicket(index, number!!, 2) }
        }

        numbersMap.takeOneNumberFromEachTen()
            .forEachIndexed { index, number -> if (number != null) addNumberToRandomTicket(index, number) }

        return this
    }

    private fun List<TicketBuilderDto>.sortNumbersInColumns() = onEach { ticket ->
        ticket.columns.map { column -> column.values.sort() }
    }

    private fun List<TicketBuilderDto>.addNumberToRandomTicket(
        columnOrder: Int,
        value: Int,
        maxColumnSize: Int = MAX_COLUMN_SIZE
    ) {
        val ticket = getRandomTicket()

        ticket.columns[columnOrder].takeIf {
            (it.values.size < maxColumnSize) && (ticket.columns.fold(0) { sum, column -> sum + column.values.size } < 15)
        }
            ?.values
            ?.add(value)
            ?: addNumberToRandomTicket(columnOrder, value, maxColumnSize)
    }

    private fun List<TicketBuilderDto>.getRandomTicket() = get(SecureRandom().nextInt(size))

    private fun Map<Int, MutableList<Int>>.takeOneNumberFromEachTen() = values.map { ten -> ten.removeFirstOrNull() }

    private fun generatePair(key: Int, numberFrom: Int, numberTo: Int) =
        Pair(key, (numberFrom..numberTo).shuffled().toMutableList())

    companion object {

        const val MAX_COLUMN_SIZE = 3
        const val TICKETS_IN_STRIP_AMOUNT = 6
        const val COLUMNS_IN_TICKET_AMOUNT = 9
    }

}
