package org.wit.spendingtracker_androidapp_asgn2.main

import android.app.Application
import org.wit.spendingtracker_androidapp_asgn2.models.PurchaseModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val purchases = ArrayList<PurchaseModel>()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Spending Tracker App started")
    }
}