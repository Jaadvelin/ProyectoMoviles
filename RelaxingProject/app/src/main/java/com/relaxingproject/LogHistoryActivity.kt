package com.relaxingproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CalendarView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.relaxingproject.classes.DatabaseHelper
import com.relaxingproject.classes.Log
import java.security.AccessControlContext

class LogHistoryActivity: AppCompatActivity() {
    private val dbHelper = DatabaseHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var dataLogs: List<Log> = dbHelper.viewLog()
        val showLogs = mutableListOf<Log>()
        setContentView(R.layout.loghistory_screen)
        val calendarView = findViewById<CalendarView>(R.id.logCalendarView)
        val logText = findViewById<TextView>(R.id.selectedLog)
        val listView = findViewById<ListView>(R.id.listView)

        calendarView?.setOnDateChangeListener{view, year, month, day ->
            val searchedDate = "" + day + "/" + (month + 1) + "/" + year
            showLogs.clear()
            dataLogs.forEach{
                if(it.date == searchedDate){
                    showLogs.add(it)
                }
            }
            listView.adapter = MyCustomAdapter(this, showLogs)
            val log: Log? = dataLogs.find { it.date == searchedDate }
            if (log == null){
                logText.text = "No Log found"
            }else{
                logText.text = log.text
            }
        }

    }
    private class MyCustomAdapter(context: Context,logs :MutableList<Log>): BaseAdapter(){
        private val mLog: MutableList<Log> = logs
        private val mContext: Context = context
        override fun getView(position: Int, convertView: View?, groupView: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val rowMain = layoutInflater.inflate(R.layout.row_main, groupView, false)
            val textView = rowMain.findViewById<TextView>(R.id.textView)
            textView.text = mLog[position].title
            return rowMain
        }

        override fun getItem(position: Int): Any {
            return mLog[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return mLog.size
        }

    }
}
