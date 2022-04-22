package com.example.demo.strip.model.entity

import javax.persistence.MappedSuperclass
import javax.persistence.OneToMany
import javax.persistence.OrderBy

@MappedSuperclass
abstract class BaseStripCompositeElement : BaseStripElement(), IStripCompositeElement {

    @OrderBy("uiOrder ASC")
    @OneToMany(mappedBy = "parent", targetEntity = BaseStripElement::class)
    override val elements: MutableList<BaseStripElement> = mutableListOf()
}
