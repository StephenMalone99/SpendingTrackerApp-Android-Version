package org.wit.spendingtracker_androidapp_asgn2.models

interface PurchaseStore {
    fun findAll(): List<PurchaseModel>
    fun create(purchase: PurchaseModel)
    fun update(purchase: PurchaseModel)
}