package com.example.moviebuddies

import android.app.PendingIntent.getActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import com.github.nkzawa.emitter.Emitter
import org.json.JSONException
import org.json.JSONObject
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.EditText


class StartActivity : AppCompatActivity() {
    val controller = StartController(this)
    var user: UserModel = UserModel("", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        controller.setup()

        val buttonGen = findViewById<Button>(R.id.generateBtn)
        buttonGen.setOnClickListener{
            val nameET = findViewById<EditText>(R.id.nameET)
            val idET = findViewById<EditText>(R.id.idET)
            val name = nameET.text.toString()
            val roomId = idET.text.toString()
            user = UserModel(name, roomId)
            controller.generate(nameET.text.toString(), idET.text.toString())
        }
        val buttonJoin = findViewById<Button>(R.id.joinBtn)
        buttonJoin.setOnClickListener{
            val nameET = findViewById<EditText>(R.id.nameET)
            val idET = findViewById<EditText>(R.id.idET)
            val name = nameET.text.toString()
            val roomId = idET.text.toString()
            if(name != "" && roomId != ""){
                user = UserModel(name, roomId)
                controller.join(name, roomId)
            }

        }


    }

    fun startWaitingActivity(){
        val intent = Intent(this, WaitingActivity::class.java)
        intent.putExtra("name", user.name)
        intent.putExtra("roomId", user.roomId)
        startActivity(intent)
    }
}
