package com.example.moneyinc.source

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.moneyinc.R

class MyCustomAdapter(var ctx: Context, var resources: Int, var items: ArrayList<Model>): ArrayAdapter<Model>(ctx,resources,items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater= LayoutInflater.from(ctx)
        val view= layoutInflater.inflate(resources,null)

        val clientName: TextView = view.findViewById<TextView>(R.id.clientName)
        val clientDescription: TextView = view.findViewById<TextView>(R.id.clientDescription)
        val img: ImageView= view.findViewById<ImageView>(R.id.profileCell)

        clientName.text= items[position].clientName
        clientDescription.text= items[position].clientDescription
        img.setImageDrawable(ctx.resources.getDrawable(items[position].Image))

        return  view
    }
}