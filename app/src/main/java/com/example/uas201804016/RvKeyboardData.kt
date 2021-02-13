package com.example.uas201804016

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RvKeyboardData : AppCompatActivity() {
    private lateinit var rv_view: RecyclerView
    lateinit var keyboardDBHelper: DBHelper
    private  var list: ArrayList<DataModelKeyboard> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rv_keyboarddata)
        rv_view = findViewById(R.id.rv_views)
        rv_view.setHasFixedSize(true)
        keyboardDBHelper = DBHelper(this)
        list.addAll(keyboardDBHelper.fullDataKeyboard())
        rv_view.layoutManager = LinearLayoutManager(this)
        var cardData = RvKeyboardDataAdapter(list)
        rv_view.adapter = cardData

        val txtDashboard: TextView = findViewById(R.id.txtdashboard)
        txtDashboard.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}