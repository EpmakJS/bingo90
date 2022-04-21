package com.example.demo.strip.model.transformer

import com.example.demo.strip.model.ElementType.ROW
import com.example.demo.strip.model.Row
import com.example.demo.strip.model.dto.RowDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class RowTransformer : IStripElementTransformer<RowDto, Row> {

    @Autowired
    @Lazy
    private lateinit var stripElementTransformerFacade: StripElementTransformerFacade

    override val type = ROW

    override fun transform(element: Row) =
        RowDto(
            id = element.id,
            uiOrder = element.uiOrder,
            parentId = element.parent!!.id,
            type = element.type,
            elements = stripElementTransformerFacade.transform(element.elements)
        )
}
