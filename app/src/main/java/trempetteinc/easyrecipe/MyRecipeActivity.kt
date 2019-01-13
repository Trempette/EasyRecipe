package trempetteinc.easyrecipe

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_my_recipe.*
import trempetteinc.easyrecipe.R.id.mes_recettes_menu

class MyRecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_recipe)

        //toolbar et drawer menu
        toolbar.title = resources.getString(R.string.mes_recettes)
        toolbar.setTitleTextColor(this.resources.getColor(R.color.white))
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_menu)
        toolbar.setNavigationOnClickListener {
            drawer_layout.openDrawer(Gravity.START)
        }

        drawer_title.text = resources.getString(R.string.drawer_title, RecetteSingleton.user.userName)

        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        ){
            override fun onDrawerClosed(view: View){
                super.onDrawerClosed(view)
            }

            override fun onDrawerOpened(drawerView: View){
                super.onDrawerOpened(drawerView)
            }
        }
        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        navigation_view.setNavigationItemSelectedListener{
            when (it.itemId){
                R.id.mes_recettes_menu -> {
                }
                R.id.base_calcul_menu -> {
                    startActivity(Intent(applicationContext, CalculBaseActivity::class.java))

                }
                R.id.se_deconnecter -> {
                    FirebaseAuth.getInstance().signOut()
                    startActivity(Intent(applicationContext, ConnexionActivity::class.java))
                }
                R.id.mes_recettes_current_menu -> {
                    startActivity(Intent(this, MesCreationsActivity::class.java))
                }

            }
            // Close the drawer
            drawer_layout.closeDrawer(Gravity.START)
            true
        }
        //fin toolbar


        val mesRecettes = RecetteSingleton.listeDeRecette


        mes_recettes_adapter.layoutManager = LinearLayoutManager(this)
        mes_recettes_adapter.adapter = RecetteAdapter(mesRecettes, this)

        bt_add_a_recipe.setOnClickListener {
            val intent = Intent(this, CreateARecipeActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(Gravity.START)) {
            drawer_layout.closeDrawer(Gravity.START)
        } else {
            super.onBackPressed()
        }
    }
}
