package com.example.moviebuddies

import android.util.Log
import android.widget.Toast
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import org.json.JSONObject

class WaitingController(WA: WaitingActivity) {
    private var socket: Socket = IO.socket("http://192.168.141.123:25565/")

    private val onNewMessage = Emitter.Listener { args ->
        WA.runOnUiThread(Runnable {
            val data = args[0] as JSONObject
            val command = data.getString("command")
            Log.e("command", data.toString())
            when(command){
                "get_users" -> {
                    val usersJSON = data.getJSONArray("users")
                    val list = List(usersJSON.length()){
                        usersJSON.getString(it)
                    }
                    WA.populateLV(list)
                }
                "refresh_users" -> {
                    val usersJSON = data.getJSONArray("users")
                    val list = List(usersJSON.length()){
                        usersJSON.getString(it)
                    }
                    WA.populateLV(list)
                }

            }
        })
    }

    fun setup(){
        socket.on("message", onNewMessage)
    }

    fun getUsers(userModel: UserModel){
        val json = JSONObject()
        json.put("u_name", userModel.name)
        json.put("id", userModel.roomId)
        socket.send("get_users", json)
    }
}