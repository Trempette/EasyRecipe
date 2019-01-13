package trempetteinc.easyrecipe

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_connexion.*

class ConnexionActivity : AppCompatActivity() {

    private val TAG = "LoginActivity"
    //global variables
    private var email: String? = null
    private var password: String? = null

    //UI elements
    private var etEmail: EditText? = null
    private var etPassword: EditText? = null
    private var btnLogin: Button? = null
    private var mProgressBar: ProgressDialog? = null

    //Firebase references
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connexion)
        title = "Connexion"

        initialise()


    }

    private fun initialise() {
        etEmail = findViewById<View>(R.id.mail_connexion_input) as TextInputEditText
        etPassword = findViewById<View>(R.id.password_connexion_input) as TextInputEditText
        btnLogin = findViewById<View>(R.id.bt_connexion) as Button
        mProgressBar = ProgressDialog(this)
        mAuth = FirebaseAuth.getInstance()
        pas_inscris_encore.setOnClickListener {
            startActivity(Intent(this, InscriptionActivity::class.java))
        }

        if(mAuth!!.currentUser != (null)){
            updateUI()
            }
        else{
            btnLogin!!.setOnClickListener { loginUser() }
        }
    }

    private fun loginUser() {
        email = etEmail?.text.toString()
        password = etPassword?.text.toString()
        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            mProgressBar!!.setMessage(getString(R.string.connexion))
            mProgressBar!!.show()
            mAuth!!.signInWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener(this) { task ->
                    mProgressBar!!.hide()
                    if (task.isSuccessful) {
                        updateUI()
                    } else {
                        Toast.makeText(this@ConnexionActivity, getString(R.string.authentication_failed),
                            Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this, getString(R.string.form_incomplete), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI() {
        val intent = Intent(this@ConnexionActivity, splashActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("depuisConnexion", true)
        startActivity(intent)
    }
}
