package com.example.demo.strip.controller

import com.example.demo.strip.model.entity.Strip
import com.example.demo.strip.model.dto.StripDto
import com.example.demo.strip.service.IStripService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/strips")
class StripController(private val stripService: IStripService) {

    @GetMapping
    fun lookup(): List<StripDto> = stripService.lookup()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): StripDto = stripService.getById(id)

    @PostMapping
    fun create(@RequestParam batchSize: Int): List<Strip> = stripService.create(batchSize)
}
