package com.example.billscontrol.transactions.utils

import com.example.billscontrol.transactions.ui.add_transaction.ValidAmount

fun String.isCurrency(): ValidAmount {
    val currencyRegex = "^\\$?(([1-9]\\d{0,2}(,\\d{3})*)|0)?(\\.\\d{1,2})?$"
    return if(matches(currencyRegex.toRegex())) ValidAmount.Valid else ValidAmount.Error
}