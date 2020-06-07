package com.relaxingproject

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.relaxingproject.classes.DatabaseHelper
import com.relaxingproject.classes.Log
import kotlinx.android.synthetic.main.logging_screen.*
import java.io.ByteArrayOutputStream

class LogActivity: AppCompatActivity() {
    private val dbHelper = DatabaseHelper(this)
    // Gets the data repository in write mode
	//.
    fun getBytes(bitmap: Bitmap):ByteArray{
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream)
        return stream.toByteArray()
    }

    fun getImage(image:ByteArray):Bitmap{
        return BitmapFactory.decodeByteArray(image, 0, image.size)
    }

    private fun imageToBitmap(image: ImageView): ByteArray {
        val bitmap = (image.drawable as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)

        return stream.toByteArray()
    }
    var logs: MutableList<Log> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logging_screen)
        val imageView = findViewById<ImageView>(R.id.imageView)
        logSaveBtn.setOnClickListener {
            var newLog: Log = Log()
            newLog.text = logText.text.toString()
            newLog.title = logTitle.text.toString()
            newLog.date = dateField.text.toString()
            newLog.rating = ratingField.text.toString()
            logs.add(newLog)
            val image = imageToBitmap(imageView)
            dbHelper.addLog(newLog, image)
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}