package com.example.demo.strip.model

import java.io.Serializable
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.SEQUENCE
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity : Serializable {

    @Id
    @GeneratedValue(generator = SEQUENCE_GENERATOR, strategy = SEQUENCE)
    open var id: Long = 0

    companion object {
        const val ALLOCATION_SIZE = 1
        const val SEQUENCE_GENERATOR = "sequence"
    }
}
