package com.example.demo.strip.repository

import com.example.demo.strip.model.Cell
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ICellRepository : JpaRepository<Cell, Long> {
}
