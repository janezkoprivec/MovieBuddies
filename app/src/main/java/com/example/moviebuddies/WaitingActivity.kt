package com.example.moviebuddies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class WaitingActivity : AppCompatActivity() {
    val controller = WaitingController(this)
    var user = UserModel("", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waiting)
        user = UserModel(intent.getStringExtra("name"), intent.getStringExtra("roomId"))

        val nameTV = findViewById<TextView>(R.id.nameTV)
        nameTV.text = user.roomId
        controller.setup()
        controller.getUsers(user)

        val play = findViewById<Button>(R.id.playBtn)
        play.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, VideoActivity::class.java)
            startActivity(intent)
        })
    }

    fun populateLV(people: List<String>){
        val listview = findViewById<ListView>(R.id.peopleLV)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, people)
        listview.adapter = adapter
    }
}
