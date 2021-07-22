package com.example.moneyinc.manager

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.example.moneyinc.source.API
import com.example.moneyinc.bank.BankAccount
import com.example.moneyinc.source.MainActivity
import com.example.moneyinc.R
import com.example.moneyinc.databinding.ManagerCreateAccountFragmentBinding
import retrofit2.Call
import retrofit2.Response
import java.util.*

class ManagerCreateAccountFragment : Fragment(R.layout.manager_create_account_fragment) {
    private lateinit var binding: ManagerCreateAccountFragmentBinding
    private lateinit var accountType: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= ManagerCreateAccountFragmentBinding.bind(view)

        binding.button.setOnClickListener {
            createAccount(MainActivity.userTOKEN!!)
            Toast.makeText(this.context,"Conta registada,",Toast.LENGTH_SHORT).show()
        }

        binding.accountTypeSpinner.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView <*>?, view: View?, position: Int, id: Long) {
                accountType= adapterView?.getItemAtPosition(position).toString()
                Toast.makeText(this@ManagerCreateAccountFragment.context,"Conta ${adapterView?.getItemAtPosition(position).toString()}",Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) { println("Please select an account type!") }
        }
    }

    private fun createAccount(userToken: String) {
        val owner= binding.titular.text.toString()
        val iban= binding.iban.text.toString()
        val nib= binding.nib.text.toString()
        val swift= binding.nib.text.toString()

        val tokenCode= "token $userToken"
        val account= BankAccount(accountType,owner,"","",iban,nib,swift,true,true,0.0)

        API.retrofitService.postAccount(tokenCode,account).enqueue(object : retrofit2.Callback<BankAccount> {
            override fun onResponse(call: Call<BankAccount>, response: Response<BankAccount>) {
                Log.i("RESPONSE", response.toString())
                Log.i("New account", response.body().toString())
            }

            override fun onFailure(call: Call<BankAccount>, t: Throwable) { println("Failed to execute") }
        })
    }
}