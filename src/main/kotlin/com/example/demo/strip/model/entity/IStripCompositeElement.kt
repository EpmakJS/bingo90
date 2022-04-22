package com.example.demo.strip.model.entity

interface IStripCompositeElement : IStripElement {

    val elements: MutableList<out IStripElement>
}
