package com.relaxingproject

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Log.e
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
//import com.relaxingproject.classes
import kotlinx.android.synthetic.main.meditation_screen.*
import java.util.Date
import android.widget.MediaController

import java.lang.Exception

class MeditateActivity: AppCompatActivity() {


    private var mediaControls: MediaController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.meditation_screen)
        if( mediaControls == null){
            mediaControls= MediaController( this@MeditateActivity)
        }

        try {
            videoView.setMediaController(mediaControls)
            videoView.setVideoURI(Uri.parse("android.resource://"+ packageName + "/" + R.raw.meditate))
        } catch (e: Exception){
            Log.e("Error", e.message)
        }

        videoView.requestFocus()
        videoView.setOnPreparedListener() {
            videoView.start()
            chronometer.start()
                it.setLooping(true)
        }
        this.startBtn.setOnClickListener {
            if(videoView.isPlaying){
                videoView.pause()
                chronometer.stop()
            } else {
                chronometer.start()
                videoView.resume()
            }
        }
    }
}

