package com.surya.findmedicines

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RequestsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
    val MedicineName: TextView = itemView.findViewById(R.id.medicine_name_pharma)
    val Quantity: TextView = itemView.findViewById(R.id.quantity_pharma)
    val ihave: Button = itemView.findViewById(R.id.pharma_ihave)
    val idonthave: Button = itemView.findViewById(R.id.pharma_dont_have)
    fun bind(request: Requests_Data_Class){
        MedicineName.text = request.MedicineName
        Quantity.text = request.Quantity.toString()
    }
}