package com.example.billscontrol.transactions.ui.transactions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.billscontrol.transactions.ui.add_transaction.AddTransactionScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionsScreen(
    viewModel: TransactionsViewModel = viewModel()
) {
    val openDialogState by viewModel.openTransactionState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(TransactionsEvents.ToggleAddTransaction)
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Open new transaction")
            }
        }) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            if (openDialogState) {
                Dialog(onDismissRequest = {
                    viewModel.onEvent(TransactionsEvents.ToggleAddTransaction)
                }) {
                    AddTransactionScreen {
                        viewModel.onEvent(TransactionsEvents.ToggleAddTransaction)
                    }
                }
            }
        }
    }
}