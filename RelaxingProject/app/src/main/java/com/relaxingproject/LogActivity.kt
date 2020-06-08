package com.relaxingproject

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.hsalf.smileyrating.SmileyRating
import com.relaxingproject.classes.DatabaseHelper
import com.relaxingproject.classes.Log
import kotlinx.android.synthetic.main.logging_screen.*
import java.io.ByteArrayOutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LogActivity: AppCompatActivity() {
    private val dbHelper = DatabaseHelper(this)
    private var imageFlag = 0
    var logs: MutableList<Log> = mutableListOf()
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

    private fun pickFromGallery(){
        imageFlag = 1
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    pickFromGallery()
                }else{
                    Toast.makeText(this, "Permiso Denegado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            imageView.setImageURI(data?.data)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object{
        private val IMAGE_PICK_CODE = 1000
        private val PERMISSION_CODE = 1001
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun currentDate() {
        val datePattern = "dd/MM/yyyy"
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern(datePattern)
        val formatted = current.format(formatter)
        dateField.text = Editable.Factory.getInstance().newEditable(formatted)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logging_screen)
        this.currentDate()
        val imageView = findViewById<ImageView>(R.id.imageView)
        imageView.setImageURI(null)
        var rating = ""
        btnImage.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE)
                }else{
                    pickFromGallery()
                }
            }else{
                pickFromGallery()
            }
        }

        smileyRating.setSmileySelectedListener(SmileyRating.OnSmileySelectedListener { type -> // You can compare it with rating Type
            //https://github.com/sujithkanna/SmileyRating
            // log rating to database
            // You can get the user rating too
            // rating will between 1 to 5
            rating = type.rating.toString()
        })
        
        logSaveBtn.setOnClickListener {
            var newLog: Log = Log()
            var image:ByteArray
            newLog.text = logText.text.toString()
            newLog.title = logTitle.text.toString()
            newLog.date = dateField.text.toString()
            newLog.dateFormat()
            newLog.rating = rating//ratingField.text.toString()
            logs.add(newLog)
            if(imageFlag == 0){
                image = byteArrayOf(0,0)
            }else{
                image = imageToBitmap(imageView)
            }
            if(newLog.date == "invalid"){
                Toast.makeText(this, "Formato de fecha inválido", Toast.LENGTH_SHORT).show()
            }else if(newLog.title == "" || newLog.text == ""){
                Toast.makeText(this, "Título o Contenido vacío", Toast.LENGTH_SHORT).show()
            }else if(newLog.rating == ""){
                Toast.makeText(this, "Falta evaluar tu día", Toast.LENGTH_SHORT).show()
            }else{
                dbHelper.addLog(newLog, image)
                startActivity(Intent(this, MainActivity::class.java))
            }
        }




    }


}