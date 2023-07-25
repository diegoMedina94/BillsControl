package com.example.billscontrol.transactions.ui.transactions
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TransactionsViewModel() : ViewModel() {

    private val _openTransactionState = MutableStateFlow(false)
    val openTransactionState : StateFlow<Boolean> = _openTransactionState.asStateFlow()

    private fun toggleTransaction(){
        _openTransactionState.update {
            !_openTransactionState.value
        }
    }

    fun onEvent(event: TransactionsEvents){
        when(event){
            TransactionsEvents.ToggleAddTransaction -> toggleTransaction()
        }
    }

}

sealed interface TransactionsEvents {
    object ToggleAddTransaction : TransactionsEvents
}