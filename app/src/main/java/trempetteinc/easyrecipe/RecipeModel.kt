package trempetteinc.easyrecipe

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class RecipeModel (
    var nom: String = "", var ingredients: ArrayList<IngredientModel> = ArrayList(),
    var stepIn: Int = 0, var stepOut: Int = 0, var key : String = "") : Parcelable {

    fun getNumberofIngredient() : Int{
        return this.ingredients.size
    }


}
