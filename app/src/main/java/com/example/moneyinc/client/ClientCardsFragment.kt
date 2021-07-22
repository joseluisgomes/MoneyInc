package com.example.moneyinc.client

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.moneyinc.R
import com.example.moneyinc.bank.BankCard
import com.example.moneyinc.bank.Cards
import com.example.moneyinc.databinding.ClientCardsFragmentBinding
import com.example.moneyinc.source.API
import com.example.moneyinc.source.MainActivity
import com.example.moneyinc.source.Model
import com.example.moneyinc.source.MyCustomAdapter
import retrofit2.Call
import retrofit2.Response

class ClientCardsFragment : Fragment(R.layout.client_cards_fragment) {
    private lateinit var binding: ClientCardsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= ClientCardsFragmentBinding.bind(view)

        binding.searchCard.setOnClickListener {
            getCards(MainActivity.userTOKEN!!, binding.cardPage.text.toString().toInt())
            binding.cardPage.setText("")
        }
    }

    private fun getCards(token: String, page: Int) {
        val tokenCode= "token $token"

        API.retrofitService.getCards(tokenCode, page).enqueue(object : retrofit2.Callback<Cards> {
            override fun onResponse(call: Call<Cards>, response: Response<Cards>) {
                Log.i("List of cards: \n", response.body()?.results.toString())

                val myCards= response.body()?.results
                initializeListView(myCards!!)
            }

            override fun onFailure(call: Call<Cards>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun initializeListView(cards: List<BankCard>) {
        val items= ArrayList<Model>()

        val cardType= "Tipo de cartão: "
        val cardNumber= "Número: "
        val cardValidity= "Validade: "
        val cardCost= "Custo anual: "

        Log.i("#cards= ", cards.size.toString())
        Log.i("List of my cards:", cards.toString())

        for (i in cards.indices) {
            items.add(Model(cards[i].name_on_card, cardType+ cards[i].type+ "\n"+cardNumber + cards[i].number+"\n"
            +cardValidity+ cards[i].valid_until+ "\n"+ cardCost+ cards[i].cost_per_year+ "€", R.drawable.credit_card2))
        }

        binding.cardsList.adapter= context?.let { MyCustomAdapter(it, R.layout.custom_item_layout, items) }
    }
}
