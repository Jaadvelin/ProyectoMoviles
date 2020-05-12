package com.relaxingproject

import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.relaxingproject.classes.DatabaseHelper
import com.relaxingproject.classes.Log

class LogHistoryActivity: AppCompatActivity() {
    private val dbHelper = DatabaseHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loghistory_screen)
        var logs: List<Log> = dbHelper.viewLog()
        val calendarView = findViewById<CalendarView>(R.id.logCalendarView)
        val logText = findViewById<TextView>(R.id.selectedLog)
        calendarView?.setOnDateChangeListener{view, year, month, day ->
            val searchedDate = "" + day + "/" + (month + 1) + "/" + year
            val log: Log? = logs.find { it.date == searchedDate }
            if (log == null){
                logText.text = "No Log found"
            }else{
                logText.text = log.text
            }
        }

    }
}
