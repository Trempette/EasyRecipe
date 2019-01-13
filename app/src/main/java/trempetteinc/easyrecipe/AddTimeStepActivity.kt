package trempetteinc.easyrecipe

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_time_step.*
import kotlinx.android.synthetic.main.activity_calcul_base.*

class AddTimeStepActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_time_step)

        toolbar_step_teim.title = "Temps de step ?"
        toolbar_step_teim.setTitleTextColor(this.resources.getColor(R.color.white))
        toolbar_step_teim.navigationIcon = resources.getDrawable(R.drawable.back_icon)
        toolbar_step_teim.setNavigationOnClickListener {
            super.onBackPressed()
        }


        var nouvelleRecette = intent.getParcelableExtra("RecetteEnCours") as RecipeModel
         if(nouvelleRecette.nom.isNullOrEmpty()){
             Toast.makeText(this, "Bro, y'a eu une erreur", Toast.LENGTH_LONG).show()
             val intent = Intent(this, CreateARecipeActivity::class.java)

         }

        day_step_in_number.text = "0"
        day_step_out_number.text = "0"

        step_in_moins.setOnClickListener {
            if(day_step_in_number.text.toString().toInt()>0)
            day_step_in_number.text = (day_step_in_number.text.toString().toInt()-1).toString()
        }
        step_in_plus.setOnClickListener {
            day_step_in_number.text = (day_step_in_number.text.toString().toInt()+1).toString()
            if(day_step_in_number.text.toString().toInt()>day_step_out_number.text.toString().toInt())
                day_step_out_number.text = day_step_in_number.text
        }
        step_out_moins.setOnClickListener {
            if(day_step_out_number.text.toString().toInt()>0)
                day_step_out_number.text = (day_step_out_number.text.toString().toInt()-1).toString()

            if(day_step_in_number.text.toString().toInt()>day_step_out_number.text.toString().toInt())
                day_step_in_number.text = day_step_out_number.text

        }
        step_out_plus.setOnClickListener {
            day_step_out_number.text = (day_step_out_number.text.toString().toInt()+1).toString()

        }

        validate_button_final.setOnClickListener {
            nouvelleRecette.stepIn = day_step_in_number.text.toString().toInt()
            nouvelleRecette.stepOut = day_step_out_number.text.toString().toInt()
            RecetteSingleton.addARecipe(nouvelleRecette)
            val intent = Intent(this, MyRecipeActivity::class.java)
            startActivity(intent)
        }

    }
}
