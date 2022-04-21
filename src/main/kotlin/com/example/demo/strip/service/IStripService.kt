package com.example.demo.strip.service

import com.example.demo.strip.model.dto.StripDto

interface IStripService {

    fun lookup(): List<StripDto>

    fun getById(id: Long): StripDto

    fun create(batchSize: Int)
}
