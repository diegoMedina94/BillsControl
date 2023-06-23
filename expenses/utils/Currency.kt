package com.example.billscontrol.expenses.utils

fun String.isCurrency(): Boolean {
    val currencyRegex = "^\\$?(([1-9]\\d{0,2}(,\\d{3})*)|0)?(\\.\\d{1,2})?$"
    return matches(currencyRegex.toRegex())
}