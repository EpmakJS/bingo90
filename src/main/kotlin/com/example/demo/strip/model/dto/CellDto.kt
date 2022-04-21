package com.example.demo.strip.model.dto

import com.example.demo.strip.model.ElementType

class CellDto(
    id: Long,
    uiOrder: Int,
    parentId: Long?,
    type: ElementType,
    val value: Int?
) : BaseStripElementDto(id, uiOrder, parentId, type)
