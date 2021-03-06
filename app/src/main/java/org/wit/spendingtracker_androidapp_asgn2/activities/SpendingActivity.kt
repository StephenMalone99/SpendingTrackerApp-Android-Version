package org.wit.spendingtracker_androidapp_asgn2.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import org.wit.spendingtracker_androidapp_asgn2.R
import org.wit.spendingtracker_androidapp_asgn2.databinding.ActivitySpendingBinding
import org.wit.spendingtracker_androidapp_asgn2.helpers.showImagePicker
import org.wit.spendingtracker_androidapp_asgn2.main.MainApp
import org.wit.spendingtracker_androidapp_asgn2.models.Location
import org.wit.spendingtracker_androidapp_asgn2.models.PurchaseModel
import timber.log.Timber
import timber.log.Timber.i
import java.lang.Exception
import java.lang.NumberFormatException

class SpendingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpendingBinding
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    private lateinit var mapIntentLauncher : ActivityResultLauncher<Intent>
    var purchase = PurchaseModel()
    lateinit var app: MainApp
    val IMAGE_REQUEST = 1

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
            Picasso.get()
                .load(purchase.image)
                .into(binding.purchaseImage)
            if (purchase.image != Uri.EMPTY) {
                binding.chooseImage.setText(R.string.change_purchase_image)
            }

        }

        binding.btnAdd.setOnClickListener() {
            purchase.purchaseName = binding.purchaseName.text.toString()
            purchase.description = binding.description.text.toString()
            try {
                purchase.cost = binding.cost.text.toString().toInt()
            } catch (e: NumberFormatException) {
                i("Please enter a number")
            }
            if (purchase.purchaseName.isEmpty() or purchase.description.isEmpty() or purchase.cost.equals(
                    0
                )
            ) {
                Snackbar
                    .make(it, R.string.enter_purchase_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.purchases.update(purchase.copy())
                    Snackbar
                        .make(it, R.string.purchase_updated, Snackbar.LENGTH_LONG)
                        .show()
                    i("Item successfully updated: $purchase")
                    setResult(RESULT_OK)
                    finish()

                } else {
                    app.purchases.create(purchase.copy())
                    Snackbar
                        .make(it, R.string.purchase_created, Snackbar.LENGTH_LONG)
                        .show()
                    i("Item successfully added: $purchase")
                    setResult(RESULT_OK)
                    finish()
                }
            }

            }

            binding.chooseImage.setOnClickListener {
                showImagePicker(imageIntentLauncher)
            }

        binding.purchaseLocation.setOnClickListener {
            i ("Set Location Pressed")
        }

        binding.purchaseLocation.setOnClickListener {
            val location = Location(52.26131964443837, -7.111552140727241, 15f)
            if (purchase.zoom != 0f) {
                location.lat =  purchase.lat
                location.lng = purchase.lng
                location.zoom = purchase.zoom
            }
            val launcherIntent = Intent(this, MapActivity::class.java)
                .putExtra("location", location)
            mapIntentLauncher.launch(launcherIntent)
        }

        registerImagePickerCallback()
        registerMapCallback()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_purchase, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_delete -> {
                app.purchases.delete(purchase)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                            purchase.image = result.data!!.data!!
                            Picasso.get()
                                .load(purchase.image)
                                .into(binding.purchaseImage)
                            binding.chooseImage.setText(R.string.change_purchase_image)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }

    private fun registerMapCallback() {
        mapIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when (result.resultCode) {
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Location ${result.data.toString()}")
                            val location = result.data!!.extras?.getParcelable<Location>("location")!!
                            i("Location == $location")
                            purchase.lat = location.lat
                            purchase.lng = location.lng
                            purchase.zoom = location.zoom
                        }
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }
}
