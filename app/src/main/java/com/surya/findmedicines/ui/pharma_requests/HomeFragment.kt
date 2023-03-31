package com.surya.findmedicines.ui.pharma_requests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.surya.findmedicines.PharmaListAdapter
import com.surya.findmedicines.Pharma_bottom_navigation
import com.surya.findmedicines.R
import com.surya.findmedicines.Requests_Data_Class
import com.surya.findmedicines.databinding.FragmentPharmaRequestsBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentPharmaRequestsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentPharmaRequestsBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.demo
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        Requests()
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun Requests(){
        var not_responded = true
        var responded = false
        val recyclerview_requests_pharma = binding.PharmaRequestsRecyclerview
        recyclerview_requests_pharma.layoutManager = LinearLayoutManager(activity?.applicationContext)
        val recycler_list = mutableListOf<Requests_Data_Class>()
        val database = Firebase.database
        val myRef = database.getReference("Customers").child("Queries")
        recyclerview_requests_pharma.adapter = PharmaListAdapter(recycler_list)
        recyclerview_requests_pharma.adapter?.notifyDataSetChanged()
        myRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children){
                    //Toast.makeText(applicationContext, i.child("MedicineName").value.toString(),Toast.LENGTH_LONG).show()
                    val temp = Requests_Data_Class(i.child("MedicineName").value as String,
                        (i.child("Quantity").value).toString()
                    )
                    recycler_list.add(temp)
                }
                recyclerview_requests_pharma.adapter = PharmaListAdapter(recycler_list)
                recyclerview_requests_pharma.adapter?.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}