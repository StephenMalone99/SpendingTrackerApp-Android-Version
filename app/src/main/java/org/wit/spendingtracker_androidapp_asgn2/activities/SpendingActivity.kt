package org.wit.spendingtracker_androidapp_asgn2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import org.wit.spendingtracker_androidapp_asgn2.R
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
        var edit = false
        binding = ActivitySpendingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp
        i("Spending Tracker App started...")

        if (intent.hasExtra("edit_purchase")) {
            edit = true
            purchase = intent.extras?.getParcelable("edit_purchase")!!
            binding.purchaseName.setText(purchase.purchaseName)
            binding.description.setText(purchase.description)
            binding.cost.setText(purchase.cost.toString())
            binding.btnAdd.setText(R.string.save_purchase)
        }

        binding.btnAdd.setOnClickListener() {
            purchase.purchaseName = binding.purchaseName.text.toString()
            purchase.description = binding.description.text.toString()
            try {
                purchase.cost = binding.cost.text.toString().toInt()
            }
            catch (e: NumberFormatException) {
                i("Please enter a number")
            }
            if (purchase.purchaseName.isEmpty() && purchase.description.isEmpty() && purchase.cost == 0) {
                Snackbar
                    .make(it,R.string.enter_purchase_title, Snackbar.LENGTH_LONG)
                    .show()
            }
            else {
                if (edit) {
                    app.purchases.update(purchase.copy())
                } else {
                    app.purchases.create(purchase.copy())
                }
            } }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_purchase, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
