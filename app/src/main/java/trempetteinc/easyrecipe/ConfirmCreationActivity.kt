package trempetteinc.easyrecipe

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_confirm_creation.*
import java.text.SimpleDateFormat

class ConfirmCreationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_creation)

        toolbar_confirm_creation.title = resources.getString(R.string.confirmer)
        toolbar_confirm_creation.setTitleTextColor(this.resources.getColor(R.color.white))
        toolbar_confirm_creation.navigationIcon = resources.getDrawable(R.drawable.back_icon)
        toolbar_confirm_creation.setNavigationOnClickListener {
            super.onBackPressed()
        }

        var creation: CreationsModel = intent.getParcelableExtra<CreationsModel>("CreationRecette")

        val formatter = SimpleDateFormat("EEEE, d MMMM yyyy, HH:mm")

        val dateOk = formatter.format(creation.dateOk)
        val dateOptimale = formatter.format(creation.dateOptimale)

        felicitation_tv.text = resources.getString(R.string.felicition_creation, creation.recette.nom)

        if (creation.recette.stepIn == creation.recette.stepOut) {
            entre_le.text = getString(R.string.le)
            tv_date_in.text = dateOk
            et_le.visibility = View.GONE
            tv_date_out.visibility = View.GONE
        } else {
            tv_date_in.text = dateOk
            tv_date_out.text = dateOptimale
        }
        oui_button_confirm_creation.setOnClickListener {
            NotificationUtils().setNotification(creation.dateOk, this, creation.recette.nom)
            RecetteSingleton.addACreation(creation)
        }

        non_cofirm_creation.setOnClickListener {
            startActivity(Intent(this, MyRecipeActivity::class.java))
        }


    }
}
