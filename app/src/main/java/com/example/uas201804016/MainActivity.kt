package com.example.uas201804016

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtLogout: TextView = findViewById(R.id.txtlogout)
        val txtUser: TextView = findViewById(R.id.txtuser)
        val txtMouse: TextView = findViewById(R.id.txtmouse)
        val txtKeyboard: TextView = findViewById(R.id.txtkeyboard)


        val savedLogin = getSharedPreferences("Login", MODE_PRIVATE)
        val editSavedLogin = savedLogin.edit()
        txtLogout.setOnClickListener {
            editSavedLogin.putString("Email", null)
            editSavedLogin.putString("Password", null)
            editSavedLogin.putString("Status", "Off")
            editSavedLogin.commit()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        txtUser.setOnClickListener{
            val intent = Intent(this, RvDataUser::class.java)
            startActivity(intent)
        }

        txtMouse.setOnClickListener{
            val intent = Intent(this, MainMouse::class.java)
            startActivity(intent)
        }

        txtKeyboard.setOnClickListener{
            val intent = Intent(this, MainKeyboard::class.java)
            startActivity(intent)
        }
    }

}