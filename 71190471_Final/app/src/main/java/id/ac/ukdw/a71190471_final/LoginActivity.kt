package id.ac.ukdw.a71190471_final

import android.bluetooth.BluetoothDevice.EXTRA_NAME
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import id.ac.ukdw.a71190471_final.databinding.ActivityLoginBinding

class LoginActivity: AppCompatActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient
    lateinit var binding: ActivityLoginBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.tvToRegister.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
        }
        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            if (email.isEmpty()) {
                binding.edtEmail.error = "Email Harus Diisi"
                binding.edtPassword.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.edtEmail.error = "Email Tidak Valid"
                binding.edtPassword.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.edtPassword.error = "Password Harus Diisi"
                binding.edtPassword.requestFocus()
                return@setOnClickListener
            }

            LoginFirebase(email, password)

        }
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("448859778642-j949mafpecko51qalkniqvs6l48rieet.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.btnLoginGoogle.setOnClickListener {
            signIn()
        }

    }
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }

    private fun LoginFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "Selamat Datang $email", Toast.LENGTH_SHORT).show()
                    Intent(this@LoginActivity, MainActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                }else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra(EXTRA_NAME, user.displayName)
            startActivity(intent)
        }
    }
    companion object {
        const val RC_SIGN_IN = 1001
        const val EXTRA_NAME = "EXTRA_NAME"
    }
}