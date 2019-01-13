package trempetteinc.easyrecipe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_create_arecipe.*
import kotlinx.android.synthetic.main.activity_faire_une_recette.*
import kotlinx.android.synthetic.main.activity_mes_creations.*

class MesCreationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mes_creations)

        toolbar_mes_creations.title = resources.getString(R.string.mes_liquides)
        toolbar_mes_creations.setTitleTextColor(this.resources.getColor(R.color.white))
        toolbar_mes_creations.navigationIcon = resources.getDrawable(R.drawable.back_icon)
        toolbar_mes_creations.setNavigationOnClickListener {
            super.onBackPressed()
        }
        mes_creations_recycler.layoutManager = LinearLayoutManager(this)
        mes_creations_recycler.adapter = MesCreationsAdapter(RecetteSingleton.listeDeCreation, this)
    }
}
