package com.surya.findmedicines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CustomerListAdapter(private val responselist: List<customer_responses_data_class>) : RecyclerView.Adapter<customerResponsesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): customerResponsesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.customer_list_item, parent, false)
        return customerResponsesViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return responselist.size
    }

    override fun onBindViewHolder(holder: customerResponsesViewHolder, position: Int) {
        val currentMedicine = responselist[position]
        holder.MedicineName.text = currentMedicine.MedicineName
        holder.Quantity.text = currentMedicine.Quantity.toString()
    }
}