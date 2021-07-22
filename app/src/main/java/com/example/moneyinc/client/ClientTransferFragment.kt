package com.example.moneyinc.client

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.moneyinc.source.API
import com.example.moneyinc.source.MainActivity
import com.example.moneyinc.R
import com.example.moneyinc.bank.Transfer
import com.example.moneyinc.databinding.ClientTransfersFragmentBinding
import retrofit2.Call
import retrofit2.Response

class ClientTransferFragment : Fragment(R.layout.client_transfers_fragment) {
    private lateinit var binding: ClientTransfersFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= ClientTransfersFragmentBinding.bind(view)

        binding.transferButton.setOnClickListener {
            makeTransfer(MainActivity.userTOKEN!!)
            Toast.makeText(this.context,"Transferência efetuada!", Toast.LENGTH_SHORT).show()
            clearTextBoxes()
        }
    }

    private fun makeTransfer(token: String) {
        val tokenCode= "token $token"

        val id= binding.transferID.text.toString().toInt()
        val account= binding.transferAccount.text.toString().toInt()
        val nib= binding.transferNIB.text.toString()
        val amount= binding.transferValue.text.toString().toDouble()
        val date= binding.transferDate.text.toString()

        val transfer= Transfer(id, account, nib, amount, date)
        API.retrofitService.makeTransfer(tokenCode, transfer).enqueue(object : retrofit2.Callback<Transfer> {
            override fun onResponse(call: Call<Transfer>, response: Response<Transfer>) {
                Log.i("Transfer details: ", response.body().toString())
            }

            override fun onFailure(call: Call<Transfer>, t: Throwable) {
                println("Couldn't make the transfer pretended !")
            }
        })
    }

    private fun clearTextBoxes() {
        binding.transferID.setText("")
        binding.transferAccount.setText("")
        binding.transferNIB.setText("")
        binding.transferValue.setText("")
        binding.transferDate.setText("")
    }
}