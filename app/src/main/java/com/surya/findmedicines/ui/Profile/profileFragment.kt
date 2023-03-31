package com.surya.findmedicines.ui.Profile
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.toolbox.ImageLoader
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.surya.findmedicines.FirebaseLogin
import com.surya.findmedicines.R
import com.surya.findmedicines.databinding.FragmentProfileBinding

class profileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(profileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val customername: TextView = binding.customerName
        customername.setText(Firebase.auth.currentUser?.displayName)

        val signout: Button = binding.signout
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