package com.example.demo.strip.model.dto

import com.example.demo.strip.model.ElementType

class TicketDto(
    id: Long,
    uiOrder: Int,
    parentId: Long?,
    type: ElementType,
    elements: List<BaseStripElementDto>
) : BaseCompositeStripElementDto(id, uiOrder, parentId, type, elements)
