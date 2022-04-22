package com.example.demo.strip.builder

class StripBuilderDto {

    var tickets: MutableList<TicketBuilderDto> = mutableListOf()
}

class TicketBuilderDto {

    var uiOrder: Int = 0
    var columns: MutableList<ColumnBuilderDto> = mutableListOf()
}

class ColumnBuilderDto {

    var uiOrder: Int = 0
    var values: MutableList<Int> = mutableListOf()
}
