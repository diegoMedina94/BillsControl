package com.example.billscontrol.expenses.ui.model

import java.util.Date

data class Transaction(
    val id: String? = null,
    val amount : String,
    val type : TransactionTypeEnum,
    val date: Date = Date()
)