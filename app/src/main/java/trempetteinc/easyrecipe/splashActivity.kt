package trempetteinc.easyrecipe

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_splash.*

class splashActivity : AppCompatActivity(), SingletonListener{



    var test: Boolean = false
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val depuisAccueil = intent.getBooleanExtra("depuisConnexion", false)
        val animation = AnimationUtils.loadAnimation(this@splashActivity, R.anim.shake)
        splash_img.startAnimation(animation)
        mAuth = FirebaseAuth.getInstance()
        val userId : String = mAuth!!.currentUser!!.uid
        var singletonListener: SingletonListener = SingletonListener {
            if(it && depuisAccueil){
                startActivity(Intent(this, MyRecipeActivity::class.java).putExtra("depuisConnexion", false))
            }
        }
        RecetteSingleton.initialisation(userId, singletonListener)
    }
    override fun onResponse(success: Boolean) {
    }



}

