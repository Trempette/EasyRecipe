package trempetteinc.easyrecipe

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.animation.AnimationUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_arecipe.*

class CreateARecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_arecipe)

        toolbar_create_recipe.title = getString(R.string.creer_une_recette)
        toolbar_create_recipe.setTitleTextColor(this.resources.getColor(R.color.white))
        toolbar_create_recipe.navigationIcon = resources.getDrawable(R.drawable.back_icon)
        toolbar_create_recipe.setNavigationOnClickListener {
            super.onBackPressed()
        }

        var ingredientModel = IngredientModel()
        var listeIngredient = ArrayList<IngredientModel>(1)
        listeIngredient.add(ingredientModel)
        var nouvelleRecette = RecipeModel("", listeIngredient, 0, 0)

        list_ingredients.layoutManager = LinearLayoutManager(this)
        list_ingredients.adapter = IngredientAdapter(listeIngredient, this)

        add_ingredient.setOnClickListener {
            listeIngredient = (list_ingredients.adapter as IngredientAdapter).items
            listeIngredient.add(IngredientModel())
            list_ingredients.adapter = IngredientAdapter(listeIngredient, this)
        }

        delete_ingredient.setOnClickListener {
            listeIngredient = (list_ingredients.adapter as IngredientAdapter).items
            listeIngredient.remove(listeIngredient.get(listeIngredient.size - 1))
            list_ingredients.adapter = IngredientAdapter(listeIngredient, this)
        }

        validate_button.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this@CreateARecipeActivity, R.anim.shake)
            listeIngredient = (list_ingredients.adapter as IngredientAdapter).items
            var titreOk = true
            if (tv_titre_recette.text.isNullOrEmpty()) {
                tv_titre_recette.hint = resources.getString(R.string.titre_oublie)
                tv_titre_recette.startAnimation(animation)
                titreOk = false
            }
            var ingredientsOk = true
            list_ingredients.adapter = IngredientAdapter(listeIngredient, this)
            var listeIngredientTemp = ArrayList<IngredientModel>(1)

            for (ingredient in listeIngredient) {
                if (!ingredient.nom.isNullOrEmpty() || ingredient.proportion != null) {
                    listeIngredientTemp.add(ingredient)
                }
            }
            listeIngredient = listeIngredientTemp
            list_ingredients.adapter = IngredientAdapter(listeIngredient, this)

            for (ingredient in listeIngredient) {
                if (ingredient.nom.isNullOrEmpty() || ingredient.proportion == null) {
                    ingredientsOk = false
                }
            }
            if (ingredientsOk && titreOk) {
                nouvelleRecette.nom = tv_titre_recette.text.toString()
                nouvelleRecette.ingredients = listeIngredient
                val intent = Intent(this, AddTimeStepActivity::class.java)
                intent.putExtra("RecetteEnCours", nouvelleRecette)
                startActivity(intent)
            } else if (!ingredientsOk && titreOk) {
                Toast.makeText(this, resources.getString(R.string.element_missing), Toast.LENGTH_LONG).show()
            } else if (ingredientsOk && !titreOk) {
                Toast.makeText(this, resources.getString(R.string.titre_missing), Toast.LENGTH_LONG).show()
            }
        }

    }

}
