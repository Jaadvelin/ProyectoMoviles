package com.relaxingproject

import android.content.ContentValues
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.relaxingproject.classes.DatabaseHelper
import com.relaxingproject.classes.Log
import com.relaxingproject.classes.LogTable
import kotlinx.android.synthetic.main.logging_screen.*

class LogActivity: AppCompatActivity() {
    private val dbHelper = DatabaseHelper(this)
    // Gets the data repository in write mode


    var logs: MutableList<Log> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logging_screen)
        logSaveBtn.setOnClickListener {
            var newLog: Log = Log()
            newLog.text = logText.text.toString()
            newLog.title = logTitle.text.toString()
            /*var date: Date = Date(dateField.text)
            newLog.date =  date*/
            newLog.date = dateField.text.toString()
            logs.add(newLog)
            dbHelper.addLog(newLog)


        }
    }
}