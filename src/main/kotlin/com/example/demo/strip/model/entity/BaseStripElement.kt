package com.example.demo.strip.model.entity

import com.example.demo.strip.model.ElementType
import com.example.demo.strip.model.entity.BaseEntity.Companion.ALLOCATION_SIZE
import com.example.demo.strip.model.entity.BaseEntity.Companion.SEQUENCE_GENERATOR
import com.example.demo.strip.model.entity.BaseStripElement.Companion.SEQUENCE_NAME
import com.example.demo.strip.model.entity.BaseStripElement.Companion.TABLE_NAME
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
import javax.persistence.Inheritance
import javax.persistence.InheritanceType.JOINED
import javax.persistence.ManyToOne
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = TABLE_NAME)
@Inheritance(strategy = JOINED)
@SequenceGenerator(name = SEQUENCE_GENERATOR, sequenceName = SEQUENCE_NAME, allocationSize = ALLOCATION_SIZE)
abstract class BaseStripElement : BaseEntity(), IStripElement {

    @Column(nullable = false)
    override var uiOrder: Int = 0

    @ManyToOne
    override var parent: BaseStripElement? = null

    @Column(nullable = false)
    @Enumerated(STRING)
    override lateinit var type: ElementType

    abstract override fun fill(value: Any?)

    companion object {
        const val TABLE_NAME = "STRIP_ELEMENTS"
        const val SEQUENCE_NAME = "seq_strip_elements"
    }
}
