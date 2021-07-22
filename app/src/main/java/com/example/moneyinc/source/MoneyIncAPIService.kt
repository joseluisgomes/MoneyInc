package com.example.moneyinc.source

import com.example.moneyinc.bank.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL= "http://moneyinc.carrola.com/"

private val moshi= Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
val retrofit: Retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build()

object API {
    val retrofitService: MoneyIncAPIService by lazy { retrofit.create(MoneyIncAPIService::class.java) }
}

interface MoneyIncAPIService {
    @POST("tokens/create")
    fun getToken(@Body user: User): Call<Token>

    @POST("accounts/")
    fun postAccount(@Header("Authorization") stringToken: String?, @Body account: BankAccount): Call<BankAccount>

    @GET("accounts/")
    fun getAccountsList(@Header("Authorization") stringToken: String?): Call<Accounts>

    @GET("accounts/")
    fun getAccountsList(@Header("Authorization") stringToken: String?, @Query("page") id: Int): Call<Accounts>

    @GET("accounts/{id}/")
    fun getAccountDescription(@Header("Authorization") stringToken: String?, @Path("id") id: Int): Call<BankAccount>

    @POST("cards/")
    fun postCard(@Header("Authorization") stringToken: String?, @Body card: BankCard): Call<BankCard>

    @GET("cards/")
    fun getCards(@Header("Authorization") stringToken: String?, @Query("page") id: Int): Call<Cards>

    @POST("transfer/")
    fun makeTransfer(@Header("Authorization") stringToken: String?, @Body transfer: Transfer): Call<Transfer>

    @POST("payments/")
    fun makePayment(@Header("Authorization") stringToken: String?, @Body payment: Payment): Call<Payment>

    @GET("transactions/")
    fun getTransactions(@Header("Authorization") stringToken: String?, @Query("page") id: Int): Call<Transactions>
}

