package com.example.moneyinc.bank

data class Transactions(val count: Double, val next: String?, val previous: String?, val results: List<Transaction>)
data class Transaction(val id: Double, val debit: Double, val operation_date: String, val description: String, val amount: Double, val account: Accounts.Account,
                       val transfer: Transfer?, val payment: Payment?)
