package org.wit.spendingtracker_androidapp_asgn2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.wit.spendingtracker_androidapp_asgn2.databinding.CardPlacemarkBinding
import org.wit.spendingtracker_androidapp_asgn2.models.PurchaseModel

interface PurchaseListener {
    fun onPurchaseClick(purchase: PurchaseModel)
}

class PurchaseAdapter constructor(private var purchases: List<PurchaseModel>,
                                   private val listener: PurchaseListener) :
    RecyclerView.Adapter<PurchaseAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardPlacemarkBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val purchase = purchases[holder.adapterPosition]
        holder.bind(purchase, listener)
    }

    override fun getItemCount(): Int = purchases.size

    class MainHolder(private val binding : CardPlacemarkBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(purchase: PurchaseModel, listener: PurchaseListener) {
            binding.purchaseName.text = purchase.purchaseName
            binding.purchaseDescription.text = purchase.description
            binding.cost.text = purchase.cost.toString()
            Picasso.get().load(purchase.image).resize(200,200).into(binding.imageIcon)
            binding.root.setOnClickListener { listener.onPurchaseClick(purchase) }
            }
    }


}
