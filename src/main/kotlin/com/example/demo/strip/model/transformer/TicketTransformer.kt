package com.example.demo.strip.model.transformer

import com.example.demo.strip.model.ElementType.TICKET
import com.example.demo.strip.model.entity.Ticket
import com.example.demo.strip.model.dto.TicketDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class TicketTransformer : IStripElementTransformer<TicketDto, Ticket> {

    @Autowired
    @Lazy
    private lateinit var stripElementTransformerFacade: StripElementTransformerFacade

    override val type = TICKET

    override fun transform(element: Ticket) =
        TicketDto(
            id = element.id,
            uiOrder = element.uiOrder,
            parentId = element.parent!!.id,
            type = element.type,
            elements = stripElementTransformerFacade.transform(element.elements)
        )
}
