package org.wit.spendingtracker_androidapp_asgn2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.wit.spendingtracker_androidapp_asgn2.databinding.ActivitySpendingBinding
import timber.log.Timber
import timber.log.Timber.i


class SpendingActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_spending)

            Timber.plant(Timber.DebugTree())

            i("Placemark Activity started..")
        }
    }