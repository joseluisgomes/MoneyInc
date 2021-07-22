package com.example.moneyinc.client

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.moneyinc.source.API
import com.example.moneyinc.source.MainActivity
import com.example.moneyinc.R
import com.example.moneyinc.bank.Payment
import com.example.moneyinc.databinding.ClientPaymentsFragmentBinding
import retrofit2.Call
import retrofit2.Response

class ClientPaymentFragment : Fragment(R.layout.client_payments_fragment) {
    private lateinit var binding: ClientPaymentsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= ClientPaymentsFragmentBinding.bind(view)

        binding.paymentButton.setOnClickListener {
            makePayment(MainActivity.userTOKEN!!)
            Toast.makeText(this.context,"Pagamento efetuado", Toast.LENGTH_SHORT).show()
            clearTextBoxes()
        }
    }

    private fun makePayment(token: String) {
        val userToken= "token $token"

        val id= binding.paymentID.text.toString().toInt()
        val account= binding.paymentAccount.text.toString().toInt()
        val reference= binding.paymentReference.text.toString()
        val code= binding.paymentCode.text.toString()
        val value= binding.paymentAmount.text.toString().toDouble()
        val date= binding.paymentDate.text.toString()

        val payment= Payment(id, account, reference, code, value, date)

        API.retrofitService.makePayment(userToken, payment).enqueue(object : retrofit2.Callback<Payment> {
            override fun onResponse(call: Call<Payment>, response: Response<Payment>) {
                Log.i("Payment details: ", response.body().toString())
            }

            override fun onFailure(call: Call<Payment>, t: Throwable) {
                println("Couldn't make the payment")
            }
        })
    }

    private fun clearTextBoxes() {
        binding.paymentID.setText("")
        binding.paymentAccount.setText("")
        binding.paymentReference.setText("")
        binding.paymentCode.setText("")
        binding.paymentAmount.setText("")
        binding.paymentDate.setText("")
    }
}