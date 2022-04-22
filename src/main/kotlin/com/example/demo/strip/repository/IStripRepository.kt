package com.example.demo.strip.repository

import com.example.demo.strip.model.entity.Strip
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IStripRepository : JpaRepository<Strip, Long> {
}
