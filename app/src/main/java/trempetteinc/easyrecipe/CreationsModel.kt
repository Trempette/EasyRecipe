package trempetteinc.easyrecipe

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
class CreationsModel (
    var recette: RecipeModel = RecipeModel(),
    var dateCreation: Long = 0,
    var dateOk: Long = 0,
    var dateOptimale : Long = 0,
    var id: String = "") : Parcelable
