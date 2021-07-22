package com.example.moneyinc.source

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.moneyinc.R

class MySecondCustomAdapter(var ctx: Context, var resources: Int, var paymentItems: ArrayList<PaymentModel>): ArrayAdapter<PaymentModel>(ctx,resources,paymentItems) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater= LayoutInflater.from(ctx)
        val view= layoutInflater.inflate(resources,null)

        val paymentType: TextView = view.findViewById<TextView>(R.id.clientName)
        val paymentDescription: TextView = view.findViewById<TextView>(R.id.clientDescription)
        val img: ImageView= view.findViewById<ImageView>(R.id.profileCell)

        paymentType.text= paymentItems[position].paymentType
        paymentDescription.text= paymentItems[position].paymentDescription
        img.setImageDrawable(ctx.resources.getDrawable(paymentItems[position].Image))

        return  view
    }
}