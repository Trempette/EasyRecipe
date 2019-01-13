package trempetteinc.easyrecipe

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_faire_une_recette.*
import java.util.*

class FaireUneRecetteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faire_une_recette)

        toolbar_faire_creation.title = getString(R.string.create_new_creation)
        toolbar_faire_creation.setTitleTextColor(this.resources.getColor(R.color.white))
        toolbar_faire_creation.navigationIcon = resources.getDrawable(R.drawable.back_icon)
        toolbar_faire_creation.setNavigationOnClickListener {
            super.onBackPressed()
        }

        var cRecette: RecipeModel = intent.getParcelableExtra<RecipeModel>("RecetteSelectionnee")
        recette_actuelle.text = getString(R.string.new_creation_today, cRecette.nom)

        ingredient_creation_adapter.layoutManager = LinearLayoutManager(this)
        ingredient_creation_adapter.adapter = CreationIngredientAdapter(cRecette.ingredients, this, 0)

        quantity_creation.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.toString().isNullOrEmpty()) {
                    ingredient_creation_adapter.adapter =
                            CreationIngredientAdapter(cRecette.ingredients, applicationContext, s.toString().toInt())
                } else {
                    ingredient_creation_adapter.adapter =
                            CreationIngredientAdapter(cRecette.ingredients, applicationContext, 0)
                }
            }
        })

        recette_faite_button.setOnClickListener {
            val creation: CreationsModel = CreationsModel()
            creation.recette = cRecette
            var calendar = Calendar.getInstance().timeInMillis
            creation.dateCreation = calendar
            creation.dateOk = creation.dateCreation + cRecette.stepIn*24*3600*1000
            creation.dateOptimale = creation.dateCreation + cRecette.stepOut*24*3600*1000

            if (!quantity_creation.text.toString().isNullOrEmpty() && quantity_creation.text.toString().toInt() != 0) {
                val intent = Intent(this, ConfirmCreationActivity::class.java)
                intent.putExtra("CreationRecette", creation)
                startActivity(intent)
            } else {
                Toast.makeText(this, getString(R.string.missing_quantity), Toast.LENGTH_LONG).show()
            }

        }

    }
}
