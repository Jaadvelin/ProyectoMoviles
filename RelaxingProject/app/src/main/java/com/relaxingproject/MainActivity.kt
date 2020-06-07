package com.relaxingproject

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.hsalf.smileyrating.SmileyRating
import com.hsalf.smileyrating.SmileyRating.OnSmileySelectedListener
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


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

        this.currentDate()

        smileyRating.setSmileySelectedListener(OnSmileySelectedListener { type -> // You can compare it with rating Type
            //https://github.com/sujithkanna/SmileyRating
            //TODO
            // log rating to database

            // You can get the user rating too
            // rating will between 1 to 5
            val rating = type.rating
        })

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun currentDate() {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
        val formatted = current.format(formatter)
        dateLbl.text = formatted
    }


}
