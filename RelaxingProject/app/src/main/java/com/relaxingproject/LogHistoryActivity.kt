package com.relaxingproject

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.relaxingproject.LogHistoryActivity.ModelAssistant.setImageViewWithByteArray
import com.relaxingproject.classes.DatabaseHelper
import com.relaxingproject.classes.Log

class LogHistoryActivity: AppCompatActivity() {
    private val dbHelper = DatabaseHelper(this)

    object ModelAssistant {
        fun setImageViewWithByteArray(view: ImageView, data: ByteArray) {
            val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
            view.setImageBitmap(bitmap)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var dataLogs: List<Log> = dbHelper.viewLog()
        val showLogs = mutableListOf<Log>()
        setContentView(R.layout.loghistory_screen)
        val calendarView = findViewById<CalendarView>(R.id.logCalendarView)
        val logText = findViewById<TextView>(R.id.selectedLog)
        val listView = findViewById<ListView>(R.id.listView)
        val imageView = findViewById<ImageView>(R.id.selectedImage)
        val ratingText =  findViewById<TextView>(R.id.selectedRating)
        calendarView?.setOnDateChangeListener{view, year, month, day ->
            val searchedDate = "" + day + "/" + (month + 1) + "/" + year
            showLogs.clear()
            dataLogs.forEach{
                if(it.date == searchedDate){
                    showLogs.add(it)
                }
            }
            listView.adapter = MyCustomAdapter(this, showLogs)
            listView.setOnItemClickListener { parent, view, position, id ->
                //Display items when click on listView
                val detailLog: Log = listView.adapter.getItem(position) as Log
                logText.text = detailLog.text
                ratingText.text = detailLog.rating
                setImageViewWithByteArray(imageView,detailLog.image)
            }
            val log: Log? = dataLogs.find { it.date == searchedDate }
            if (log == null){
                logText.text = "No Log found"
                ratingText.text = "0"
                setImageViewWithByteArray(imageView, byteArrayOf(0,0))
            }else{
                logText.text = log.text
                ratingText.text = log.rating
                setImageViewWithByteArray(imageView,log.image)
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

        override fun getItem(position: Int): Log {
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
