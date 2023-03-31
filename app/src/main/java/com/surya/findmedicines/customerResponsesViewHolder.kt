package com.surya.findmedicines

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class customerResponsesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val MedicineName: TextView = itemView.findViewById(R.id.medicine_name_customer)
    val Quantity: TextView = itemView.findViewById(R.id.quantity_customer)
    val Status: TextView = itemView.findViewById(R.id.status_customer)
    fun bind(request: Requests_Data_Class){
        MedicineName.text = request.MedicineName
        Quantity.text = request.Quantity.toString()
    }
}