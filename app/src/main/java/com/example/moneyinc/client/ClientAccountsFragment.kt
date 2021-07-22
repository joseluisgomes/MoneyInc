package com.example.moneyinc.client

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.moneyinc.R
import com.example.moneyinc.bank.Accounts
import com.example.moneyinc.databinding.ClientAccountsFragmentBinding
import com.example.moneyinc.source.API
import com.example.moneyinc.source.MainActivity
import com.example.moneyinc.source.Model
import com.example.moneyinc.source.MyCustomAdapter
import retrofit2.Call
import retrofit2.Response

class ClientAccountsFragment : Fragment(R.layout.client_accounts_fragment) {

    private lateinit var binding: ClientAccountsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= ClientAccountsFragmentBinding.bind(view)

        binding.searchAccounts.setOnClickListener {
            getAccounts(MainActivity.userTOKEN!!, binding.accountPage.text.toString().toInt())
            binding.accountPage.setText("")
        }
    }

    private fun getAccounts(token: String, page: Int) {
        val tokenCode= "token $token"

        API.retrofitService.getAccountsList(tokenCode, page).enqueue(object : retrofit2.Callback<Accounts> {
            override fun onResponse(call: Call<Accounts>, response: Response<Accounts>) {
                Log.i("Body:", response.toString())
                Log.i("CODE: ", response.code().toString())

                val clientsList: List<Accounts.Account>?= response.body()?.results
                initializeListView(clientsList)
            }

            override fun onFailure(call: Call<Accounts>, t: Throwable) { println("Failed to execute") }
        })
    }

    private fun initializeListView(clients: List<Accounts.Account>?) {
        val items= ArrayList<Model>()
        val accountType= "Conta: "
        val accountBalance= "Saldo: "
        val accountState= "Estado: "

        Log.i("#ACCOUNTS:", clients!!.size.toString())
        Log.i("List", clients.toString())
        for (i in clients.indices) {
            items.add(
                Model(clients[i].titular1,accountState+clients[i].active+"\n"+
                        accountType+clients[i].type + "\n"+accountBalance+clients[i].saldo+ "€", R.drawable.profile))
        }

        binding.clientsList.adapter= context?.let { MyCustomAdapter(it,
            R.layout.custom_item_layout,items) }
    }
}