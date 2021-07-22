package com.example.moneyinc.bank

data class Payment(val id: Int, val account: Int, val referencia: String, val codigo: String, val valor: Double, val data_pagamento: String)
