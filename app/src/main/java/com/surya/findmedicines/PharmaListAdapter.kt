package com.surya.findmedicines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.surya.findmedicines.ui.pharma_requests.HomeFragment

class PharmaListAdapter(private val requestlist: List<Requests_Data_Class>) : RecyclerView.Adapter<RequestsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestsViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.pharma_list_item, parent, false)
        return RequestsViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return requestlist.size
    }

    override fun onBindViewHolder(holder: RequestsViewHolder, position: Int) {
        val currentMedicine = requestlist[position]
        holder.MedicineName.text = currentMedicine.MedicineName
        holder.Quantity.text = currentMedicine.Quantity.toString()
    }

}