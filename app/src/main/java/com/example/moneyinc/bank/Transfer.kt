package com.example.moneyinc.bank

data class Transfer(val id: Int, val account: Int, val to_nib: String, val valor: Double, val data_transferencia: String)