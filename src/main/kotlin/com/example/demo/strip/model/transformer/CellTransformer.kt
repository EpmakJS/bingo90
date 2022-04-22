package com.example.demo.strip.model.transformer

import com.example.demo.strip.model.entity.Cell
import com.example.demo.strip.model.ElementType.CELL
import com.example.demo.strip.model.dto.CellDto
import org.springframework.stereotype.Component

@Component
class CellTransformer : IStripElementTransformer<CellDto, Cell> {

    override val type = CELL

    override fun transform(element: Cell) =
        CellDto(
            id = element.id,
            uiOrder = element.uiOrder,
            parentId = element.parent!!.id,
            type = element.type,
            value = element.value
        )
}
