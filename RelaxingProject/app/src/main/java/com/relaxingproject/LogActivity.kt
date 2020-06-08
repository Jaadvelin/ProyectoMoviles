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
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hsalf.smileyrating.SmileyRating
import com.relaxingproject.classes.DatabaseHelper
import com.relaxingproject.classes.Log
import kotlinx.android.synthetic.main.logging_screen.*
import java.io.ByteArrayOutputStream

class LogActivity: AppCompatActivity() {
    private val dbHelper = DatabaseHelper(this)
    private var imageFlag = 0
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

    var logs: MutableList<Log> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logging_screen)
        val imageView = findViewById<ImageView>(R.id.imageView)
        imageView.setImageURI(null)
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
        val rating = ""

        smileyRating.setSmileySelectedListener(SmileyRating.OnSmileySelectedListener { type -> // You can compare it with rating Type
            //https://github.com/sujithkanna/SmileyRating
            //TODO
            // log rating to database
            // You can get the user rating too
            // rating will between 1 to 5
            val rating = type.rating.toString()
        })
        
        logSaveBtn.setOnClickListener {
            var newLog: Log = Log()
            var image:ByteArray
            newLog.text = logText.text.toString()
            newLog.title = logTitle.text.toString()
            newLog.date = dateField.text.toString()
            newLog.rating = rating//ratingField.text.toString()
            logs.add(newLog)
            if(imageFlag == 0){
                //Revisar que se esta guardando
                image = byteArrayOf(0,0)
            }else{
                image = imageToBitmap(imageView)
            }
            dbHelper.addLog(newLog, image)
            startActivity(Intent(this,MainActivity::class.java))
        }




    }


}