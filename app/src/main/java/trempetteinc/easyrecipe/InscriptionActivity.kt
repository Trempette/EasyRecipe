package trempetteinc.easyrecipe

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InscriptionActivity : AppCompatActivity() {
    //UI elements
    private var etFirstName: EditText? = null
    private var etConfirmPassword: EditText? = null
    private var etEmail: EditText? = null
    private var etPassword: EditText? = null
    private var btnCreateAccount: Button? = null
    private var mProgressBar: ProgressDialog? = null

    //Firebase references
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    private val TAG = "CreateAccountActivity"
    //global variables
    private var firstName: String? = null
    private var confirmPassword: String? = null
    private var email: String? = null
    private var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscription)
        title = "Inscription"

        initialise()


    }

    private fun initialise() {
        etFirstName = findViewById<View>(R.id.pseudo_inscription_input) as TextInputEditText
        etConfirmPassword = findViewById<View>(R.id.confirm_password_inscription_input) as TextInputEditText
        etEmail = findViewById<View>(R.id.mail_inscription_input) as TextInputEditText
        etPassword = findViewById<View>(R.id.password_inscription_input) as TextInputEditText
        btnCreateAccount = findViewById<View>(R.id.validate_button_inscription) as Button
        mProgressBar = ProgressDialog(this)
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("users")
        mAuth = FirebaseAuth.getInstance()
        btnCreateAccount!!.setOnClickListener { createNewAccount() }
    }

    private fun createNewAccount() {
        firstName = etFirstName?.text.toString()
        confirmPassword = etConfirmPassword?.text.toString()
        email = etEmail?.text.toString()
        password = etPassword?.text.toString()
        if (!firstName.isNullOrEmpty() && !confirmPassword.isNullOrEmpty() && !email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            if (samePassword(password, confirmPassword)) {
                mProgressBar!!.setMessage(getString(R.string.enregistrement_user))
                mProgressBar!!.show()
                mAuth!!
                    .createUserWithEmailAndPassword(email!!, password!!)
                    .addOnCompleteListener(this) { task ->
                        mProgressBar!!.hide()
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val userId = mAuth!!.currentUser!!.uid
                            //update user profile information
                            val currentUserDb = mDatabaseReference!!.child(userId)
                            currentUserDb.child("pseudo").setValue(firstName)
                            updateUserInfoAndUI()
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                this@InscriptionActivity, getString(R.string.authentication_failed),
                                Toast.LENGTH_SHORT
                            ).show()
                        }


                    }
            } else {
                Toast.makeText(this, getString(R.string.wrong_confirm_password), Toast.LENGTH_SHORT).show()

            }

        } else {
            Toast.makeText(this, getString(R.string.form_incomplete), Toast.LENGTH_SHORT).show()

        }

    }

    private fun updateUserInfoAndUI() {
        //start next activity
        val intent = Intent(this@InscriptionActivity, splashActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("depuisConnexion", true)
        startActivity(intent)
    }

    private fun samePassword(p0: String? = "", p1: String? = ""): Boolean {
        return (p0.equals(p1))
    }


}
