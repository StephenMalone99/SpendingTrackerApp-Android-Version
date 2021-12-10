package org.wit.spendingtracker_androidapp_asgn2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.wit.spendingtracker_androidapp_asgn2.databinding.ActivitySpendingBinding
import org.wit.spendingtracker_androidapp_asgn2.models.PurchaseModel
import timber.log.Timber
import timber.log.Timber.i

class SpendingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpendingBinding
    var purchase = PurchaseModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySpendingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())

        i("Spending Tracker App started...")

        binding.btnAdd.setOnClickListener() {
            purchase.purchaseName = binding.purchaseName.text.toString()
            if (purchase.purchaseName.isNotEmpty()) {
                i("Name of Item added: ${purchase.purchaseName}")
            } else {
                Snackbar
                    .make(it, "Please Enter what you have Purchased", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }}
