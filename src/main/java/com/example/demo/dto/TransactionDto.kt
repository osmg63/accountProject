package com.example.demo.dto

import com.example.demo.model.TransactionType
import java.math.BigDecimal
import java.time.LocalDateTime

data class TransactionDto(
        val id:String?,
        val transaction: TransactionType?,
        val amount: BigDecimal?,
        val transactionDate: LocalDateTime?,
)
