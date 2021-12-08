package org.wit.spendingtracker_androidapp_asgn2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.wit.spendingtracker_androidapp_asgn2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.greetingButton.setOnClickListener {
            val greetingText = getString(R.string.notice_text)
            Toast.makeText(applicationContext, greetingText, Toast.LENGTH_LONG).show()
        }
    }
}