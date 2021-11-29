package hu.wellcooked

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

open class BaseActivity : AppCompatActivity() {
    companion object {
        const val RC_SIGN_IN = 1
    }

    protected val auth: FirebaseAuth
        get() = Firebase.auth

    protected val user: FirebaseUser?
        get() = Firebase.auth.currentUser
}