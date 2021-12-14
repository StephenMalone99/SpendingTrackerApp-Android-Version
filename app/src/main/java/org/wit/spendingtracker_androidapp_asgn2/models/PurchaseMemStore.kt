package org.wit.spendingtracker_androidapp_asgn2.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class PurchaseMemStore : PurchaseStore {

    val purchases = ArrayList<PurchaseModel>()

    override fun findAll(): List<PurchaseModel> {
        return purchases
    }

    override fun create(purchase: PurchaseModel) {
        purchase.id = getId()
        purchases.add(purchase)
        logAll()
    }

    override fun update(purchase: PurchaseModel) {
        var foundPurchase: PurchaseModel? = purchases.find { p -> p.id == purchase.id }
        if (foundPurchase != null) {
            foundPurchase.purchaseName = purchase.purchaseName
            foundPurchase.description = purchase.description
            foundPurchase.cost = purchase.cost.toString().toInt()
            foundPurchase.image = purchase.image
            logAll()
        }
    }


    fun logAll() {
        purchases.forEach{ i("Item has been added to spending tracker : ${it}") }
    }
}