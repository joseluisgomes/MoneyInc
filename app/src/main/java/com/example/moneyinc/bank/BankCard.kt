package com.example.moneyinc.bank

data class BankCard(val id: Int, val account: Int, val user: Int, val type: String, val subtype: String, val cost_per_year: Double, val plafond: Double,
                    val name_on_card: String, val number: String, val valid_until: String, val cvc: String)