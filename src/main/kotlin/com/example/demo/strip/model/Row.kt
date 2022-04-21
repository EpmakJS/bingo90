package com.example.demo.strip.model

import com.example.demo.strip.model.ElementType.ROW
import javax.persistence.Entity
import javax.persistence.PrimaryKeyJoinColumn
import javax.persistence.Table

@Entity
@Table(name = "ROWS")
@PrimaryKeyJoinColumn
class Row : BaseStripCompositeElement() {

    @Suppress("UNCHECKED_CAST")
    override fun fill(value: Any?) {
        val cells = value as? MutableList<Cell>
        cells?.let { elements.addAll(cells) } ?: emptyList<Cell>()
    }

    init {
        type = ROW
    }
}
