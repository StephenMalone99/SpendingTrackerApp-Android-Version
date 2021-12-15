package org.wit.spendingtracker_androidapp_asgn2.main

import android.app.Application
import org.wit.spendingtracker_androidapp_asgn2.models.PurchaseJSONStore
import org.wit.spendingtracker_androidapp_asgn2.models.PurchaseMemStore
import org.wit.spendingtracker_androidapp_asgn2.models.PurchaseModel
import org.wit.spendingtracker_androidapp_asgn2.models.PurchaseStore
import timber.log.Timber
import timber.log.Timber.i


class MainApp : Application() {

    lateinit var purchases: PurchaseStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        purchases = PurchaseJSONStore(applicationContext)
        i("Spending Tracker App started")
    }
}