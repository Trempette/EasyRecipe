package trempetteinc.easyrecipe

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_ingredient.view.*
import kotlinx.android.synthetic.main.item_ingredient_creation.view.*
import kotlinx.android.synthetic.main.item_recettes.view.*

class CreationIngredientAdapter(val items : ArrayList<IngredientModel>, val context: Context, var quantity: Int) : RecyclerView.Adapter<CreationIngredientViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CreationIngredientViewHolder {
        return CreationIngredientViewHolder(LayoutInflater.from(context).inflate(R.layout.item_ingredient_creation, p0, false), context)
    }

    override fun onBindViewHolder(p0: CreationIngredientViewHolder, position: Int) {
        p0.tvingredientNom.setText(items.get(position).nom)
        if(!quantity.toString().isNullOrEmpty()) p0.tvIngredientProportion.setText((items[position].proportion!!*quantity/100).toDouble().toString())
        else p0.tvIngredientProportion.setText("0")
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }


}


class CreationIngredientViewHolder (view: View, context: Context) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each ingrédient to
    val tvingredientNom = view.ingredient_nom_item_creation
    val tvIngredientProportion = view.proportion_item_creation

}
