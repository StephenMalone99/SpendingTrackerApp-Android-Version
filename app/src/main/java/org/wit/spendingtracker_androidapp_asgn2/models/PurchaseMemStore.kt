package org.wit.spendingtracker_androidapp_asgn2.models

import timber.log.Timber.i

class PurchaseMemStore : PurchaseStore {

    val purchases = ArrayList<PurchaseModel>()

    override fun findAll(): List<PurchaseModel> {
        return purchases
    }

    override fun create(purchase: PurchaseModel) {
        purchases.add(purchase)
        logAll()
    }

    fun logAll() {
        purchases.forEach{ i("Item has been added to spending tracker : ${it}") }
    }
}