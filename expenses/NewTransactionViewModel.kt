package com.example.billscontrol.expenses

import androidx.lifecycle.ViewModel
import com.example.billscontrol.expenses.ui.model.Transaction
import com.example.billscontrol.expenses.ui.model.TransactionTypeEnum
import com.example.billscontrol.expenses.utils.isCurrency
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewTransactionViewModel : ViewModel() {

    private val _newTransactionUiState = MutableStateFlow(NewTransactionUiState())
    val newTransactionUiState : StateFlow<NewTransactionUiState> = _newTransactionUiState.asStateFlow()

    fun saveTransaction(type: TransactionTypeEnum) {
        if(_newTransactionUiState.value.isValidAmount){
            _newTransactionUiState.update {
                it.copy(type = type)
            }
            //TODO connect to repository
        }
    }

    private fun newTransaction() = Transaction(
        amount = _newTransactionUiState.value.amount,
        type = _newTransactionUiState.value.type
    )

    fun onAmountChanged(amount: String) {
        val isValidAmount = amount.isCurrency()
        _newTransactionUiState.update {
            it.copy(
                amount = amount,
                isValidAmount = isValidAmount
            )
        }

    }
}

data class NewTransactionUiState(
    val amount: String = "",
    val type: TransactionTypeEnum = TransactionTypeEnum.UNKNOWN,
    val isValidAmount : Boolean = false,
)