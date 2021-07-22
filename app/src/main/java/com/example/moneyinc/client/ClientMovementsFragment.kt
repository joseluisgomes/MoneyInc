package com.example.moneyinc.client

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.moneyinc.R
import com.example.moneyinc.bank.BankCard
import com.example.moneyinc.bank.Transaction
import com.example.moneyinc.bank.Transactions
import com.example.moneyinc.databinding.ClientMovementsFragmentBinding
import com.example.moneyinc.source.API
import com.example.moneyinc.source.MainActivity
import com.example.moneyinc.source.Model
import com.example.moneyinc.source.MyCustomAdapter
import retrofit2.Call
import retrofit2.Response


class ClientMovementsFragment : Fragment(R.layout.client_movements_fragment) {
    private lateinit var binding: ClientMovementsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= ClientMovementsFragmentBinding.bind(view)

        binding.searchMovements.setOnClickListener {
            getAccountMovements(MainActivity.userTOKEN!!, binding.movementsPage.text.toString().toInt())
            binding.movementsPage.setText("")
        }
    }

    private fun getAccountMovements(token: String, page: Int) {
        val tokenCode= "token $token"

        API.retrofitService.getTransactions(tokenCode, page).enqueue(object : retrofit2.Callback<Transactions> {
            override fun onResponse(call: Call<Transactions>, response: Response<Transactions>) {
                Log.i("List of movements:", response.body()?.results.toString())

                val myMovements= response.body()?.results
                initializeListView(myMovements!!)
            }

            override fun onFailure(call: Call<Transactions>, t: Throwable) {
                println("Couldn't show the accounts movements !")
            }
        })
    }

    private fun initializeListView(movements: List<Transaction>) {
        val items= ArrayList<Model>()

        val amount= "Montante: "
        val destiny= "Destinatário: "
        val date= "Data: "

        for (i in movements.indices) {
            items.add(Model(movements[i].description, amount+ movements[i].amount+ "€\n"+ destiny+ movements[i].account.titular1+ "\n"
            + date+ movements[i].operation_date, R.drawable.payment))
        }

        binding.movementsList.adapter= context?.let { MyCustomAdapter(it, R.layout.custom_item_layout, items) }
    }
}