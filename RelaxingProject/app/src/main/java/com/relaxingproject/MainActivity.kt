package com.relaxingproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.meditateBtn.setOnClickListener {
            startActivity(Intent(this,MeditateActivity::class.java))
        }

        this.logBtn.setOnClickListener {
            startActivity(Intent(this,LogActivity::class.java))
        }

        this.logHistBtn.setOnClickListener {
            startActivity(Intent(this,LogHistoryActivity::class.java))
        }

        this.medHistBtn.setOnClickListener {
            startActivity(Intent(this,MedHistoryActivity::class.java))
        }

    }
}
