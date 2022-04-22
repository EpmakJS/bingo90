package com.example.demo.strip.model.dto

import com.example.demo.strip.model.ElementType

abstract class BaseStripElementDto(
    val id: Long,
    val uiOrder: Int,
    val parentId: Long?,
    val type: ElementType
): Dto
