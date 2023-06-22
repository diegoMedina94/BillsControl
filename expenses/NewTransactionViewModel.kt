package com.example.billscontrol.expenses

import androidx.lifecycle.ViewModel
import com.example.billscontrol.expenses.ui.model.Transaction
import com.example.billscontrol.expenses.ui.model.TransactionTypeEnum
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewTransactionViewModel : ViewModel() {

    private val _newTransactionUiState = MutableStateFlow(NewTransactionUiState())
    val newTransactionUiState : StateFlow<NewTransactionUiState> = _newTransactionUiState.asStateFlow()

    fun saveTransaction(type: TransactionTypeEnum) {
        _newTransactionUiState.update {
            it.copy(type = type)
        }
        //TODO connect to repository
    }

    private fun newTransaction() = Transaction(
        amount = _newTransactionUiState.value.amount,
        type = _newTransactionUiState.value.type
    )

    fun onAmountChanged(amount: String) {
        _newTransactionUiState.update {
            it.copy(
                amount = amount
            )
        }
    }
}

data class NewTransactionUiState(
    val amount: String = "",
    val type: TransactionTypeEnum = TransactionTypeEnum.UNKNOWN,
    val isAmountAvailable : Boolean = false,
)