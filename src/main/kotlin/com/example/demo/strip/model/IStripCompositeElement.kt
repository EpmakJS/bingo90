package com.example.demo.strip.model

interface IStripCompositeElement : IStripElement {

    val elements: MutableList<out IStripElement>
}
