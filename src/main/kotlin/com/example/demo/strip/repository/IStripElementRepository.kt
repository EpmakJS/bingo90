package com.example.demo.strip.repository

import com.example.demo.strip.model.entity.BaseStripElement
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IStripElementRepository : JpaRepository<BaseStripElement, Long> {
}
