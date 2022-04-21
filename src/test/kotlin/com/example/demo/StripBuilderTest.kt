package com.example.demo

import com.example.demo.strip.builder.StripBuilder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class StripBuilderTest {

    private val stripBuilder = StripBuilder()

    @Test
    fun `verify tickets include exact 15 values`() {
        val stripDto = stripBuilder.build()
        val ticketsDtoList = stripDto.tickets
        val ticketsValues = ticketsDtoList.map { ticket ->
            ticket.columns.fold(0) { sum, column -> sum + column.values.size }
        }
        for (ticketsValue in ticketsValues) {
            assertEquals(ticketsValue, 15)
        }
    }

    @Test
    fun `verify tickets columns ordered asc`() {
        val stripDto = stripBuilder.build()
        val ticketsDtoList = stripDto.tickets
        val flattenListOfValuesFromColumns = ticketsDtoList.flatMap { it.columns.flatMap { column -> column.values } }
        val sortedFlattenListOfValuesFromColumns =
            ticketsDtoList.flatMap { it.columns.flatMap { column -> column.values.sorted() } }

        assertEquals(flattenListOfValuesFromColumns, sortedFlattenListOfValuesFromColumns)
    }

    @Test
    fun `verify no duplicate numbers in the strip`() {
        val stripDto = stripBuilder.build()
        val ticketsDtoList = stripDto.tickets
        val sortedValueListFromStrip =
            ticketsDtoList.flatMap { it.columns.flatMap { column -> column.values } }.sorted()
        val expectedResult = (1..90).toList()

        assertEquals(sortedValueListFromStrip, expectedResult)
    }
}
