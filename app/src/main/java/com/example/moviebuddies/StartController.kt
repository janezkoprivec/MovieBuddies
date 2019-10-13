package com.example.moviebuddies

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import org.json.JSONObject

class StartController( SA: StartActivity) {
    private var socket: Socket = IO.socket("http://192.168.141.123:25565/")

    private val onNewMessage = Emitter.Listener { args ->
        SA.runOnUiThread(Runnable {
            Log.e("json1", args[0].toString())
            val data = args[0] as JSONObject
            val command = data.getString("command")
            Log.e("data1", data.toString() + command.toString())

            when(command){
                "gen_room" -> {
                    val result = data.getString("result")
                    if(result == "success"){
                        SA.startWaitingActivity()
                    }else{
                        Toast.makeText(SA, result, Toast.LENGTH_LONG).show()
                    }
                }
                "join_room" -> {
                    val result = data.getString("result")
                    if(result == "success"){
                        SA.startWaitingActivity()
                    }else{
                        Toast.makeText(SA, result, Toast.LENGTH_LONG).show()
                    }
                }

            }

        })
    }

    fun setup(){
        socket.on("message", onNewMessage)
        socket.connect()
    }

    fun generate(name: String, id: String){
        val json = JSONObject()
        json.put("u_name", name)
        json.put("id", id)
        socket.send("gen_room", json)

    }

    fun join(name: String, id: String){
        val json = JSONObject()
        json.put("u_name", name)
        json.put("id", id)
        socket.send("join_room", json)
        socket.connect()
        Log.e("connected", socket.connected().toString())
    }
}