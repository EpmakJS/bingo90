package com.example.demo.strip.model.transformer

import com.example.demo.strip.model.ElementType.STRIP
import com.example.demo.strip.model.Strip
import com.example.demo.strip.model.dto.StripDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class StripTransformer : IStripElementTransformer<StripDto, Strip> {

    @Autowired
    @Lazy
    private lateinit var stripElementTransformerFacade: StripElementTransformerFacade

    override val type = STRIP

    override fun transform(element: Strip) =
        StripDto(
            id = element.id,
            uiOrder = element.uiOrder,
            parentId = null,
            type = element.type,
            elements = stripElementTransformerFacade.transform(element.elements)
        )
}
