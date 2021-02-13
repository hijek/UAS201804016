package com.example.uas201804016

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainKeyboard : AppCompatActivity() {
    lateinit var keyboardDBHelper: DBHelper
    lateinit var inputcode: EditText
    lateinit var inputbrand: EditText
    lateinit var inputname: EditText
    lateinit var inputprice: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_keyboard)
        inputcode = findViewById(R.id.input_code)
        inputbrand = findViewById(R.id.input_brand)
        inputname = findViewById(R.id.input_name)
        inputprice = findViewById(R.id.input_price)
        keyboardDBHelper = DBHelper(this)
    }
    fun addData(v: View){
        var codein = inputcode.text.toString()
        var brandin = inputbrand.text.toString()
        var namein = inputname.text.toString()
        var pricein = inputprice.text.toString()
        keyboardDBHelper.InsertKeyboard(codein, brandin, namein, pricein)
        inputcode.setText("")
        inputbrand.setText("")
        inputname.setText("")
        inputprice.setText("")

    }
    fun showAll(v: View){
        var pindah = Intent(this, RvKeyboardData ::class.java)
        startActivity(pindah)
    }
}