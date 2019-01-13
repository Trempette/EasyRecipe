package trempetteinc.easyrecipe

import com.google.firebase.database.*

object RecetteSingleton {
    //Firebase references
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    var listeDeRecette: ArrayList<RecipeModel> = ArrayList()
    var user: UserModel = UserModel("","")
    var listeDeCreation: ArrayList<CreationsModel> = ArrayList()

    fun initialisation(uId: String, singletonListener: SingletonListener){

        RecetteSingleton.user.uId = uId
        val userRef : DatabaseReference = FirebaseDatabase.getInstance()!!.reference!!.child("users").child(uId)
        val userListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                RecetteSingleton.user.userName = dataSnapshot.child("pseudo").getValue<String>(String::class.java) as String
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }
        val creationListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                RecetteSingleton.listeDeCreation.clear()
                dataSnapshot.children.mapNotNullTo(
                    RecetteSingleton.listeDeCreation
                ){
                    it.getValue<CreationsModel>(CreationsModel::class.java) as CreationsModel
                }
                singletonListener.onResponse(true)

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }

        val recetteListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                RecetteSingleton.listeDeRecette.clear()
                dataSnapshot.children.mapNotNullTo(
                    RecetteSingleton.listeDeRecette
                ){
                    it.getValue<RecipeModel>(RecipeModel::class.java) as RecipeModel
                }
                singletonListener.onResponse(true)

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }
        userRef.addListenerForSingleValueEvent(userListener)
        userRef.child("creations").addValueEventListener(creationListener)
        userRef.child("recettes").addValueEventListener(recetteListener)
    }
    fun addACreation(cCreation : CreationsModel){
        val recetteRef : DatabaseReference = FirebaseDatabase.getInstance()!!.reference!!.child("users").child(user.uId).child("creations")
        cCreation.id = recetteRef.push().key as String
        recetteRef.child(cCreation.id).setValue(cCreation)
    }
    fun addARecipe(cRecipe : RecipeModel){
        val recetteRef : DatabaseReference = FirebaseDatabase.getInstance()!!.reference!!.child("users").child(user.uId).child("recettes")
        cRecipe.key = recetteRef.push().key as String
        recetteRef.child(cRecipe.key).setValue(cRecipe)
    }
}