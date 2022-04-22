package com.example.demo.strip.model.entity

import com.example.demo.strip.model.ElementType.STRIP
import javax.persistence.Entity
import javax.persistence.PrimaryKeyJoinColumn
import javax.persistence.Table

@Entity
@Table(name = "STRIPS")
@PrimaryKeyJoinColumn
class Strip : BaseStripCompositeElement() {

    @Suppress("UNCHECKED_CAST")
    override fun fill(value: Any?) {
        val tickets = value as? MutableList<Ticket>
        tickets?.let { elements.addAll(tickets) } ?: emptyList<Ticket>()
    }

    init {
        type = STRIP
    }
}
