package com.surya.findmedicines.ui.Query

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.surya.findmedicines.R
import com.surya.findmedicines.databinding.FragmentQueryBinding
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentQueryBinding? = null

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

        _binding = FragmentQueryBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        display()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun display(){
        val mname = binding.MedicineNameTV
        val quantity = binding.QuantityTV
        val miles = binding.MilesTV
        val search_button = binding.Search
        search_button.setOnClickListener {
            val database = Firebase.database
            val myRef = database.getReference("Customers")
            myRef.child(Firebase.auth.currentUser?.uid.toString()).setValue(Firebase.auth.currentUser?.displayName.toString())
            val queries_ref = myRef.child("Queries").child(Calendar.getInstance().time.toString())
            queries_ref.child("MedicineName").setValue(mname.text.toString())
            queries_ref.child("Quantity").setValue(quantity.text.toString())
            queries_ref.child("Miles").setValue(miles.text.toString())
        }
    }
}