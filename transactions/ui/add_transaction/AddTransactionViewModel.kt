package com.example.billscontrol.transactions.ui.add_transaction

import androidx.lifecycle.ViewModel
import com.example.billscontrol.transactions.ui.model.Transaction
import com.example.billscontrol.transactions.ui.model.TransactionTypeEnum
import com.example.billscontrol.transactions.utils.isCurrency
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddTransactionViewModel : ViewModel() {

    private val _newTransactionUiState = MutableStateFlow(NewTransactionUiState())
    val newTransactionUiState : StateFlow<NewTransactionUiState> = _newTransactionUiState.asStateFlow()

    fun saveTransaction(type: TransactionTypeEnum) {
        if(_newTransactionUiState.value.isValidAmount == ValidAmount.Valid){
            _newTransactionUiState.update {
                it.copy(
                    type = type,
                    closeDialog = true
                )
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
    val isValidAmount : ValidAmount = ValidAmount.Idle,
    val closeDialog : Boolean = false
)

sealed interface ValidAmount {
    object Idle: ValidAmount
    object Valid: ValidAmount
    object Error: ValidAmount
}