package com.surya.findmedicines.ui.pharma_profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.surya.findmedicines.FirebaseLogin
import com.surya.findmedicines.databinding.FragmentPharmaProfileBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentPharmaProfileBinding? = null

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

        _binding = FragmentPharmaProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val customername: TextView = binding.pharmaName
        customername.setText(Firebase.auth.currentUser?.displayName)

        val signout: Button = binding.pharmaSignout
        signout.setOnClickListener {
            AuthUI.getInstance().signOut(activity?.applicationContext!!)
            this.startActivity(Intent(activity?.applicationContext, FirebaseLogin::class.java))
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}