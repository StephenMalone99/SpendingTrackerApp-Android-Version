package org.wit.spendingtracker_androidapp_asgn2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.wit.spendingtracker_androidapp_asgn2.databinding.ActivitySpendingBinding
import org.wit.spendingtracker_androidapp_asgn2.main.MainApp
import org.wit.spendingtracker_androidapp_asgn2.models.PurchaseModel
import timber.log.Timber
import timber.log.Timber.i
import java.lang.Exception
import java.lang.NumberFormatException

class SpendingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpendingBinding
    var purchase = PurchaseModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpendingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp
        i("Spending Tracker App started...")

        binding.btnAdd.setOnClickListener() {
            purchase.purchaseName = binding.purchaseName.text.toString()
            purchase.description = binding.description.text.toString()
            try {
                purchase.cost = binding.cost.text.toString().toInt()
            }
            catch (e: NumberFormatException) {
                i("Please enter a number")
            }
            if (purchase.purchaseName.isNotEmpty() && purchase.description.isNotEmpty() && purchase.cost != null) {
                app.purchases.add(purchase.copy())
                i("Item added to spending tracker: ${purchase}")
                for (i in app!!.purchases.indices)
                { i("Purchase[$i]:${this.app.purchases[i]}") }
                setResult(RESULT_OK)
                finish()
            }
             else {
                Snackbar
                    .make(it, "Please ensure you fill in all fields", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}
