package com.example.demo.strip.model.transformer

import com.example.demo.strip.model.BaseStripElement
import com.example.demo.strip.model.ElementType
import com.example.demo.strip.model.dto.BaseStripElementDto

interface IStripElementTransformer<F : BaseStripElementDto, M : BaseStripElement> {

    val type: ElementType

    fun transform(element: M): F
}
