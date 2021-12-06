package hu.wellcooked.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import hu.wellcooked.LoginActivity
import hu.wellcooked.R
import hu.wellcooked.databinding.FragmentLoadingBinding
import hu.wellcooked.databinding.FragmentLogoutBinding

class LogoutFragment : Fragment() {
    private lateinit var binding: FragmentLogoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogoutBinding.inflate(inflater, container, false)
        signOut()
        startActivity(Intent(context, LoginActivity::class.java))
        return binding.root
    }

    private fun signOut() {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        GoogleSignIn.getClient(requireActivity(), gso).signOut()
        Firebase.auth.signOut()
    }
}