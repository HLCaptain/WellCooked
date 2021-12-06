package hu.wellcooked.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import hu.wellcooked.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        binding.apply {
            val user = Firebase.auth.currentUser
            val userInfo = StringBuilder()
            userInfo.appendLine("Email: " + user?.email)
            userInfo.appendLine("Name: " + user?.displayName)
            userInfo.appendLine("Phone: " + user?.phoneNumber)
            userInfo.appendLine("User ID: " + user?.uid)
            userInfo.appendLine("Metadata: " + user?.metadata)
            accountInfo.text = userInfo
        }
        return binding.root
    }
}