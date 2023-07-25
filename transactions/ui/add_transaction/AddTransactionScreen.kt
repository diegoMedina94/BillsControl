package com.example.billscontrol.transactions.ui.add_transaction

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.billscontrol.R
import com.example.billscontrol.transactions.ui.model.TransactionTypeEnum

@Composable
fun AddTransactionScreen(
    viewModel: AddTransactionViewModel = viewModel(),
    closeDialog: () -> Unit
) {
    val state = viewModel.newTransactionUiState.collectAsState()
    AddTransaction(
        state = state,
        onSaveExpense = viewModel::saveTransaction,
        onAmountChanged = viewModel::onAmountChanged,
        closeDialog = closeDialog
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransaction(
    modifier: Modifier = Modifier,
    state: State<NewTransactionUiState>,
    onSaveExpense: (type: TransactionTypeEnum) -> Unit,
    onAmountChanged: (amount: String) -> Unit,
    closeDialog: () -> Unit
) {

    if(state.value.closeDialog) closeDialog()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = state.value.amount,
            onValueChange = { onAmountChanged(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            label = {
                Text(text = stringResource(id = R.string.add_new_expenses_label))
            },
            singleLine = true,
            isError = state.value.isValidAmount == ValidAmount.Error
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                onClick = { onSaveExpense(TransactionTypeEnum.INCOME) }) {
                Text(text = stringResource(id = R.string.add_new_expenses_save_button))
            }
            Button(onClick = { onSaveExpense(TransactionTypeEnum.OUTCOME) }) {
                Text(text = stringResource(id = R.string.add_new_expenses_expense_button))
            }
        }
    }
}


@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun AddTransactionPreview(
    modifier: Modifier = Modifier
) {
    AddTransaction(
        state = mutableStateOf(value = NewTransactionUiState()),
        onSaveExpense = {},
        onAmountChanged = {},
        closeDialog = {}
    )
}
