package com.example.demo.strip.model

interface IStripElement {

    val uiOrder: Int

    val parent: BaseStripElement?

    val type: ElementType

    fun fill(value: Any?)
}
