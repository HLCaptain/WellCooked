package hu.wellcooked

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GoogleAuthProvider
import hu.wellcooked.databinding.ActivityLoginBinding
import hu.wellcooked.fragment.LoadingFragment

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var googleSignInAccount: GoogleSignInAccount
    private lateinit var progressFragment: LoadingFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iniGoogleSignIn()

        binding.googleSignInBtn.setOnClickListener {
            enableProgress()
            googleSignIn()
        }

        progressFragment = LoadingFragment()
    }

    override fun onDestroy() {
        // todo remove signout after testing
        auth.signOut()
        googleSignInClient.signOut()
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()
        updateUi()
    }

    private fun updateUi() {
        if (user != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        disableProgress()
    }

    private fun enableProgress() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.progressFragment, progressFragment)
            .commit()
    }

    private fun disableProgress() {
        supportFragmentManager
            .beginTransaction()
            .remove(progressFragment)
            .commit()
    }

    private fun iniGoogleSignIn() {
        // Configure Google Sign In
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun googleSignIn() {
        startActivityForResult(googleSignInClient.signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            googleSignInAccount = completedTask.getResult(ApiException::class.java)
            googleSignInAccount.idToken?.let { firebaseAuthWithGoogle(it) }
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            val message = "signInResult:failed code=" + e.statusCode
            Log.w(TAG, message)
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
            updateUi()
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    Toast.makeText(applicationContext, "Welcome " + user?.displayName, Toast.LENGTH_SHORT).show()
                    updateUi()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                }
            }
    }
}