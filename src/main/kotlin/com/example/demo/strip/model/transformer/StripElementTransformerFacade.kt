package com.example.demo.strip.model.transformer

import com.example.demo.strip.model.BaseStripElement
import com.example.demo.strip.model.dto.BaseStripElementDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import com.example.demo.strip.model.ElementType
import javax.annotation.PostConstruct

@Component
class StripElementTransformerFacade {

    @Autowired
    private lateinit var transformerList: List<IStripElementTransformer<*, *>>

    private lateinit var transformers: Map<ElementType,
        IStripElementTransformer<BaseStripElementDto, BaseStripElement>>

    @Suppress("UNCHECKED_CAST")
    @PostConstruct
    fun init() {
        this.transformers = transformerList
            .associate { it.type to it as IStripElementTransformer<BaseStripElementDto, BaseStripElement> }
    }

    fun transform(element: BaseStripElement) = getTransformer(element.type).transform(element)

    fun transform(elements: Collection<BaseStripElement>) =
        elements.map { getTransformer(it.type).transform(it) }

    private fun getTransformer(type: ElementType) =
        transformers[type] ?: throw IllegalArgumentException("Unexpected element type $type")
}
