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
import org.wit.spendingtracker_androidapp_asgn2.databinding.ActivitySpendingListBinding
import org.wit.spendingtracker_androidapp_asgn2.databinding.CardPlacemarkBinding
import org.wit.spendingtracker_androidapp_asgn2.main.MainApp
import org.wit.spendingtracker_androidapp_asgn2.models.PurchaseModel

class SpendingListActivity : AppCompatActivity() {

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
        binding.recyclerView.adapter = PurchaseAdapter(app.purchases)
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
}

class PurchaseAdapter constructor(private var purchases: List<PurchaseModel>) :
    RecyclerView.Adapter<PurchaseAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardPlacemarkBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val purchase = purchases[holder.adapterPosition]
        holder.bind(purchase)
    }

    override fun getItemCount(): Int = purchases.size

    class MainHolder(private val binding : CardPlacemarkBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(purchase: PurchaseModel) {
            binding.purchaseName.text = purchase.purchaseName
            binding.purchaseDescription.text = purchase.description
            binding.cost.text = purchase.cost.toString()
        }
    }
}

