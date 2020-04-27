package com.relaxingproject

import android.content.ContentValues
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.relaxingproject.classes.DatabaseHelper
import com.relaxingproject.classes.Log
import com.relaxingproject.classes.LogTable
import kotlinx.android.synthetic.main.logging_screen.*
import java.util.Date

class LogActivity: AppCompatActivity() {
    private val dbHelper = DatabaseHelper(applicationContext)
    // Gets the data repository in write mode
    val db = dbHelper.writableDatabase


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
            // Create a new map of values, where column names are the keys
            val values = ContentValues().apply {
                put(LogTable.LogEntry.COLUMN_NAME_TITLE, newLog.title)
                put(LogTable.LogEntry.COLUMN_NAME_TEXT, newLog.text)
                put(LogTable.LogEntry.COLUMN_NAME_DATE, newLog.date)
            }
            // Insert the new row, returning the primary key value of the new row
            val newRowId = db?.insert(LogTable.LogEntry.TABLE_NAME, null, values)


        }
    }
}