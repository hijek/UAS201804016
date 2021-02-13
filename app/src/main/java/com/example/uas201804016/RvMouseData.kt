package com.example.uas201804016

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RvMouseData : AppCompatActivity() {
    private lateinit var rv_view: RecyclerView
    lateinit var mouseDBHelper: DBHelper
    private  var list: ArrayList<DataModelMouse> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rv_datamouse)
        rv_view = findViewById(R.id.rv_views)
        rv_view.setHasFixedSize(true)
        mouseDBHelper = DBHelper(this)
        list.addAll(mouseDBHelper.fullDataMouse())
        rv_view.layoutManager = LinearLayoutManager(this)
        var cardData = RvMouseDataAdapter(list)
        rv_view.adapter = cardData

        val txtDashboard: TextView = findViewById(R.id.txtdashboard)
        txtDashboard.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}