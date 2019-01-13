package trempetteinc.easyrecipe

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_mes_creation.view.*
import java.text.SimpleDateFormat

class MesCreationsAdapter(val items: ArrayList<CreationsModel>, val context: Context) :
    RecyclerView.Adapter<CreationsViewHolder>() {

    override fun onBindViewHolder(holder: CreationsViewHolder, position: Int) {
        holder.tvRecetteNomCreation.text = (items.get(position).recette.nom)
        val formatter = SimpleDateFormat("EEEE, d MMMM yyyy, HH:mm")
        holder.tvDateCreation.text = "Créée le : "+formatter.format(items[position].dateCreation)
        holder.tvPretALaConso.text = "Prêt à consommer le : "+formatter.format(items[position].dateOk)

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CreationsViewHolder {
        return CreationsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_mes_creation, p0, false), context)
    }


    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }


}

class CreationsViewHolder(view: View, context: Context) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each recipe to
    val tvRecetteNomCreation = view.nom_recette_creation
    val tvDateCreation = view.cree_le_tv
    val tvPretALaConso = view.pret_a_la_conso

}