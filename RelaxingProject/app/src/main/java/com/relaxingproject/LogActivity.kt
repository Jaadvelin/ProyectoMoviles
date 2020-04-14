package com.relaxingproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.relaxingproject.classes.Log
import kotlinx.android.synthetic.main.logging_screen.*
import java.util.Date

class LogActivity: AppCompatActivity() {
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
        }
    }
}