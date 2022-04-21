package com.example.demo.strip.model

import com.example.demo.strip.model.ElementType.TICKET
import javax.persistence.Entity
import javax.persistence.PrimaryKeyJoinColumn
import javax.persistence.Table

@Entity
@Table(name = "TICKETS")
@PrimaryKeyJoinColumn
class Ticket : BaseStripCompositeElement() {

    @Suppress("UNCHECKED_CAST")
    override fun fill(value: Any?) {
        val rows = value as? MutableList<Row>
        rows?.let { elements.addAll(rows) } ?: emptyList<Row>()
    }

    init {
        type = TICKET
    }
}
