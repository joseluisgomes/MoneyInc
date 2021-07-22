package com.example.moneyinc.source

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.moneyinc.R
import com.example.moneyinc.bank.Token
import com.example.moneyinc.bank.User
import com.example.moneyinc.client.ClientActivity
import com.example.moneyinc.databinding.ActivityMainBinding
import com.example.moneyinc.manager.ManagerActivity
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object { var userTOKEN: String?= null }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Main)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            val userName: String= binding.codigoDeAcesso.text.toString()
            val password: String= binding.password.text.toString()
            Log.i("PASSWORD:" ,password)

            API.retrofitService.getToken(User(userName,password)).enqueue(object : retrofit2.Callback<Token> {
                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                    userTOKEN = response.body()?.token
                    Log.i("Body: ", response.toString())
                    Log.i("TOKEN: ", userTOKEN!!)
                }

                override fun onFailure(call: Call<Token>, t: Throwable) { println("Failed to execute") }
            })

            loginValidation(userName)
        }

        binding.cancel.setOnClickListener {
            binding.codigoDeAcesso.setText("")
            binding.password.setText("")
        }
    }

    private fun loginValidation(user: String) {
        if (user == "funcionario") {
            Toast.makeText(this,"Gestor",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ManagerActivity::class.java))
        } else {
            Toast.makeText(this,"Cliente",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ClientActivity::class.java))
        }
    }
}