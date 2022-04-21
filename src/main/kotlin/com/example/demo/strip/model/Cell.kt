package com.example.demo.strip.model

import com.example.demo.strip.model.ElementType.CELL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.PrimaryKeyJoinColumn
import javax.persistence.Table

@Entity
@Table(name = "CELLS")
@PrimaryKeyJoinColumn
class Cell : BaseStripElement() {

    @Column
    var value: Int? = null

    override fun fill(value: Any?) {
        this.value = value as? Int
    }

    init {
        type = CELL
    }
}
