package org.wit.spendingtracker_androidapp_asgn2.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.wit.spendingtracker_androidapp_asgn2.R
import org.wit.spendingtracker_androidapp_asgn2.adapters.PurchaseAdapter
import org.wit.spendingtracker_androidapp_asgn2.adapters.PurchaseListener
import org.wit.spendingtracker_androidapp_asgn2.databinding.ActivitySpendingListBinding
import org.wit.spendingtracker_androidapp_asgn2.databinding.CardPlacemarkBinding
import org.wit.spendingtracker_androidapp_asgn2.main.MainApp
import org.wit.spendingtracker_androidapp_asgn2.models.PurchaseModel

class SpendingListActivity : AppCompatActivity(), PurchaseListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivitySpendingListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpendingListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = PurchaseAdapter(app.purchases.findAll(),this)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, SpendingActivity::class.java)
                startActivityForResult(launcherIntent,0)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPurchaseClick(purchase: PurchaseModel) {
        val launcherIntent = Intent(this, SpendingActivity::class.java)
        startActivityForResult(launcherIntent,0)
    }

}


