package com.example.demo.strip.model.dto

import com.example.demo.strip.model.ElementType

open class BaseCompositeStripElementDto(
    id: Long,
    uiOrder: Int,
    parentId: Long?,
    type: ElementType,
    val elements: List<BaseStripElementDto>
): BaseStripElementDto(id, uiOrder, parentId, type)
