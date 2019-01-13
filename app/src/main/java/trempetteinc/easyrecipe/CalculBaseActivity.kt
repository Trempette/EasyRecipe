package trempetteinc.easyrecipe

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.activity_calcul_base.*

class CalculBaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calcul_base)

        toolbar_calcul_base.title = resources.getString(R.string.calcul_base_title)
        toolbar_calcul_base.setTitleTextColor(this.resources.getColor(R.color.white))
        toolbar_calcul_base.navigationIcon = resources.getDrawable(R.drawable.back_icon)
        toolbar_calcul_base.setNavigationOnClickListener {
            super.onBackPressed()
        }

        qte_base_neutre_result.visibility = View.GONE
        nb_booster_results.visibility = View.GONE
        booster_inf_result.visibility = View.GONE
        booster_sup_result.visibility = View.GONE

        qte_base_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculDeBase()
            }
        })
        ctr_voulu_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculDeBase()
            }
        })
        vlm_booster_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculDeBase()

            }
        })
        ctr_booster_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculDeBase()

            }
        })
    }

    fun calculDeBase() {
        var ctrVoulue = 0.0
        var qteBase = 0
        var vlBooster = 0
        var ctrBooster = 0.0
        if (!ctr_booster_et.text.toString().isNullOrEmpty()) ctrBooster = ctr_booster_et.text.toString().toDouble()
        if (!ctr_voulu_et.text.toString().isNullOrEmpty()) ctrVoulue = ctr_voulu_et.text.toString().toDouble()
        if (!vlm_booster_et.text.toString().isNullOrEmpty()) vlBooster = vlm_booster_et.text.toString().toInt()
        if (!qte_base_et.text.toString().isNullOrEmpty()) qteBase = qte_base_et.text.toString().toInt()


        if (ctrBooster != 0.0 && vlBooster != 0) {
            qte_base_neutre_result.visibility = View.VISIBLE
            nb_booster_results.visibility = View.VISIBLE
            booster_inf_result.visibility = View.VISIBLE
            booster_sup_result.visibility = View.VISIBLE

            val mgNicotineTot: Double = qteBase * ctrVoulue
            val mlBoosterVol: Double = mgNicotineTot / ctrBooster
            val nbBooster: Double = mlBoosterVol / vlBooster.toDouble()
            val qteBaseNeutre: Double = qteBase - vlBooster * nbBooster

            qte_base_neutre_result.text = resources.getString(R.string.result_qte_base_neutre, qteBaseNeutre.toString())
            nb_booster_results.text = resources.getString(R.string.result_nb_booster, nbBooster.toString())

            val nbBoosterInf: Int = Math.floor(nbBooster).toInt()
            val nbBoosterSup: Int = Math.ceil(nbBooster).toInt()

            val resultInf: Double = (nbBoosterInf * vlBooster * ctrBooster / qteBase)
            val resultSup: Double = (nbBoosterSup * vlBooster * ctrBooster / qteBase)

            booster_inf_result.text = resources.getString(R.string.result_plus_ou_moins_booster, nbBoosterInf, resultInf.toString())
            booster_sup_result.text = resources.getString(R.string.result_plus_ou_moins_booster, nbBoosterSup, resultSup.toString())
        }
    }
}
