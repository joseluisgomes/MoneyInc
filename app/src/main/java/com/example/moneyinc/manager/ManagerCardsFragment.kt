package com.example.moneyinc.manager

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.moneyinc.source.API
import com.example.moneyinc.bank.BankCard
import com.example.moneyinc.source.MainActivity
import com.example.moneyinc.R
import com.example.moneyinc.databinding.ManagerCardsFragmentBinding
import retrofit2.Call
import retrofit2.Response

class ManagerCardsFragment : Fragment(R.layout.manager_cards_fragment) {
    private lateinit var binding: ManagerCardsFragmentBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= ManagerCardsFragmentBinding.bind(view)

        binding.createCardButton.setOnClickListener {
            Toast.makeText(this.context,"Cartão criado!",Toast.LENGTH_SHORT).show()
            createCard(MainActivity.userTOKEN!!)
        }
    }

    private fun createCard(userToken: String) {
        val tokenCode= "token $userToken"

        val cardAccount= binding.cardAccount.text.toString().toInt()
        val cardUser= binding.cardUser.text.toString().toInt()
        val cardName= binding.cardName.text.toString()
        val cardNumber= binding.cardNumber.text.toString()
        val cardValidity= binding.cardValidity.text.toString()
        val cardCVC= binding.cardCVC.text.toString()

        Log.i("Card Account", cardAccount.toString())
        API.retrofitService.postCard(tokenCode,
            BankCard(cardAccount+1,cardAccount,cardUser,"CR","VS",30.0,24.0,cardName,
        cardNumber,cardValidity,cardCVC)
        ).enqueue(object : retrofit2.Callback<BankCard> {
            override fun onResponse(call: Call<BankCard>, response: Response<BankCard>) {
                Log.i("Card description: ", response.body().toString())
            }

            override fun onFailure(call: Call<BankCard>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}