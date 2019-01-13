package trempetteinc.easyrecipe

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class IngredientModel(var nom: String = "", var proportion: Double? = null) : Parcelable {

    override fun toString(): String {
        return "IngredientModel(nom='$nom', proportion=$proportion)"
    }





}