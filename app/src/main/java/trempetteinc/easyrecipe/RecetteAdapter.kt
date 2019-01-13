package trempetteinc.easyrecipe

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_recettes.view.*

class RecetteAdapter(val items: ArrayList<RecipeModel>, val context: Context) :
    RecyclerView.Adapter<RecetteViewHolder>() {

    override fun onBindViewHolder(holder: RecetteViewHolder, position: Int) {
        holder.tvRecetteNom.text = (items.get(position).nom)
        var textStep: String = ""
        if (items[position].stepIn != items[position].stepOut) {
            textStep = context.getString(
                R.string.item_recette_temps_step,
                items.get(position).stepIn,
                items.get(position).stepOut
            )
        } else {
            textStep = context.getString(
                R.string.item_recette_temps_step_one,
                items.get(position).stepIn
            )

        }
        var textNumberIngredient : String = ""
        if (items[position].getNumberofIngredient() != 1) {
            textNumberIngredient =
                context.getString(R.string.item_recette_nombre_ingredients, items.get(position).getNumberofIngredient())
        }
        else { textNumberIngredient =
                context.getString(R.string.item_recette_nombre_ingredients_one, items.get(position).getNumberofIngredient())}
        holder.tvRecetteStep.text = (textStep)
        holder.tvRecetteIngredients.text = (textNumberIngredient)

        holder.cardView.setOnClickListener {
            var intent : Intent = Intent(context, FaireUneRecetteActivity::class.java)
            intent.putExtra("RecetteSelectionnee", items[position])
            startActivity(context, intent, null)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecetteViewHolder {
        return RecetteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recettes, p0, false), context)
    }


    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }


}

class RecetteViewHolder(view: View, context: Context) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each recipe to
    val tvRecetteNom = view.recette_item_titre
    val tvRecetteStep = view.step_item_recette
    val tvRecetteIngredients = view.nombre_dingredients_item_recette
    val cardView = view.cardview_item

}