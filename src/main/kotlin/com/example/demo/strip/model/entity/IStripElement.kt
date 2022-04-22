package com.example.demo.strip.model.entity

import com.example.demo.strip.model.ElementType

interface IStripElement {

    val uiOrder: Int

    val parent: BaseStripElement?

    val type: ElementType

    fun fill(value: Any?)
}
