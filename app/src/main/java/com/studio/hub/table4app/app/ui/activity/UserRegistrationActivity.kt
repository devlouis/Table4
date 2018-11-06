package com.studio.hub.table4app.app.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.studio.hub.table4app.R
import kotlinx.android.synthetic.main.activity_user_registration.*

class UserRegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_registration)

        imageView.setOnClickListener {
            this.startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
