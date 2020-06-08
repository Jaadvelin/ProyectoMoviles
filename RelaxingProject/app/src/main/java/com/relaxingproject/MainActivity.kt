package com.relaxingproject

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity() {



    @RequiresApi(Build.VERSION_CODES.O)
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

        this.settingsBtn.setOnClickListener {
            startActivity(Intent(this,SettingsActivity::class.java))
        }

        this.currentDate()




    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun currentDate() {
        val datePattern = "dd/MM/yyyy"
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern(datePattern)
        val formatted = current.format(formatter)
        dateLbl.text = formatted
    }


}
