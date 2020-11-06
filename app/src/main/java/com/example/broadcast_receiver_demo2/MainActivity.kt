package com.example.broadcast_receiver_demo2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    var tvvalue : TextView? = null
    var btnincre : Button? = null
    var btnidecre : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvvalue = findViewById(R.id.tvnumber)
        btnincre = findViewById(R.id.btnincrement)
        btnidecre = findViewById(R.id.btndecrement)

        var id = 0

        LocalBroadcastManager.getInstance(this).registerReceiver(messageeceiver, IntentFilter("custom-action-local-broadcast"))
        btnincrement.setOnClickListener {
            var increment = ++id
            var intent = Intent("custom-action-local-broadcast")
            intent.putExtra("name","$increment")
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        }

        btndecrement.setOnClickListener {
            var decrement = --id
            var intent = Intent("custom-action-local-broadcast")
            intent.putExtra("name","$decrement")
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        }
    }

    private val messageeceiver: BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent) {
            val message = intent.getStringExtra("name")
            tvnumber.text = message

        }
    }
}





