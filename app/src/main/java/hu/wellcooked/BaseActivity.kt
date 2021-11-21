package hu.wellcooked

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

open class BaseActivity : AppCompatActivity() {
    companion object {
        const val RC_SIGN_IN = 1
    }

    protected val auth: FirebaseAuth
        get() = FirebaseAuth.getInstance()

    protected val user: FirebaseUser?
        get() = auth.currentUser
}