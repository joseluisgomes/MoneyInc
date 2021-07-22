package com.example.moneyinc.bank

data class Accounts(val count: Int, val results: List<Account>) {
    data class Account(val id: Int, val type: String, val titular1: String, val titular2: String, val titular3: String,
    val iban: String, val nib: String, val swift: String, val active: Boolean, val approved: Boolean, val createdOn: String,
    val saldo: Double)
}