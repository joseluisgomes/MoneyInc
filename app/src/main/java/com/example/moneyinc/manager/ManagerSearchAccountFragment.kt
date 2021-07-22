package com.example.moneyinc.manager

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import com.example.moneyinc.source.API
import com.example.moneyinc.bank.BankAccount
import com.example.moneyinc.source.MainActivity
import com.example.moneyinc.R
import com.example.moneyinc.databinding.ManagerSearhAccountFragmentBinding
import retrofit2.Call
import retrofit2.Response

class ManagerSearchAccountFragment : Fragment(R.layout.manager_searh_account_fragment) {
    private lateinit var binding: ManagerSearhAccountFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= ManagerSearhAccountFragmentBinding.bind(view)

        binding.searchAccount.setOnClickListener {
            Log.i("Account id:", binding.accountID.text.toString())
            searchAccount(MainActivity.userTOKEN!!, binding.accountID.text.toString().toInt())
        }
    }

    private fun searchAccount(token: String, id: Int) {
        val tokenCode= "token $token"

        API.retrofitService.getAccountDescription(tokenCode, id).enqueue(object : retrofit2.Callback<BankAccount> {
            override fun onResponse(call: Call<BankAccount>, response: Response<BankAccount>) {
                val account= response.body()

                binding.tipoDeConta.text= account?.type
                binding.titular1.text= account?.titular1
                binding.iban.text= account?.iban
                binding.nib.text= account?.nib
                binding.swift.text= account?.swift
            }

            override fun onFailure(call: Call<BankAccount>, t: Throwable) {
                Log.i("Throwable: ", t.message!!)
            }
        })
    }
}