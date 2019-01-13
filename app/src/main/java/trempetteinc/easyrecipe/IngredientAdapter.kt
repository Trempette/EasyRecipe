package trempetteinc.easyrecipe

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.EditText
import kotlinx.android.synthetic.main.item_ingredient.view.*

class IngredientAdapter(val items : ArrayList<IngredientModel>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_ingredient, p0, false), context)
    }

    override fun onBindViewHolder(p0: ViewHolder, position: Int) {
        p0.tvingredientNom.setText(items.get(position).nom)
        p0.tvIngredientPosition.text = (position + 1).toString()
        if(items.get(position).proportion == null) {p0.tvIngredientProportion.setText("")}
        else {p0.tvIngredientProportion.setText(items.get(position).proportion.toString())}
        p0.tvingredientNom.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                items.get(position).nom = p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                items.get(position).nom = p0.toString()
            }
        })
        p0.tvIngredientProportion.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                items.get(position).proportion = p0.toString().toDouble()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                items.get(position).proportion = p0.toString().toDouble()
            }
        })
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }


}


class ViewHolder (view: View, context: Context) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each ingrédient to
    val tvingredientNom = view.ingredient_nom_item
    val tvIngredientPosition = view.number_item
    val tvIngredientProportion = view.proportion_item

}


