package com.surya.findmedicines.ui.Responses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.surya.findmedicines.CustomerListAdapter
import com.surya.findmedicines.PharmaListAdapter
import com.surya.findmedicines.Requests_Data_Class
import com.surya.findmedicines.customer_responses_data_class
import com.surya.findmedicines.databinding.FragmentProfileBinding
import com.surya.findmedicines.databinding.FragmentResponsesBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentResponsesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentResponsesBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        displaylist()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun displaylist(){
        val recyclerview_requests_pharma = binding.CustomerRequestsRecyclerview
        recyclerview_requests_pharma.layoutManager = LinearLayoutManager(activity?.applicationContext)
        val recycler_list = mutableListOf<customer_responses_data_class>()
        val database = Firebase.database
        val myRef = database.getReference("Customers").child("Queries")

        myRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children){
                   // Toast.makeText(activity?.applicationContext, i.child("MedicineName").toString(),Toast.LENGTH_LONG).show()
                    val temp = customer_responses_data_class(i.child("MedicineName").value as String,
                        (i.child("Quantity").value).toString(), false
                    )
                    recycler_list.add(temp)
                }
                recyclerview_requests_pharma.adapter = CustomerListAdapter(recycler_list)
                recyclerview_requests_pharma.adapter?.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}