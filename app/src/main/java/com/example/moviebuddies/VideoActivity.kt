package com.example.moviebuddies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.MediaController
import android.widget.VideoView
import kotlinx.android.synthetic.main.activity_video.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class VideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        val videoView: CustomVideoView = findViewById(R.id.videoview)
        videoView.setVideoPath("https://www.android-examples.com/wp-content/uploads/2016/01/sample_video.3gp")
        videoView.setPlayPauseListener(object : CustomVideoView.PlayPauseListener {

            override fun onPlay() {
                Log.e("video","Play!")
            }

            override fun onPause() {
                Log.e("video","Pause!")
            }

            override fun onSeek() {
                Log.e("video", "Seek!")
            }
        })


        val mediaController: MediaController = MediaController(this)
        mediaController.setMediaPlayer(videoView)
        videoView.setMediaController(mediaController)


        videoView.start()
    }



}
