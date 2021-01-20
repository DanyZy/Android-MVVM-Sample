package com.aim.leantechtest.ui.details.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.aim.leantechtest.R
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {

    private lateinit var itemIcon: ImageView
    private lateinit var itemName: TextView
    private lateinit var exit: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        itemIcon = findViewById(R.id.item_icon)
        itemName = findViewById(R.id.item_name)
        exit = findViewById(R.id.exit)

        val intent: Intent = intent
        if (intent.hasExtra("USER_NAME")) {
            Glide.with(itemIcon.context)
                .load(intent.getStringExtra("USER_ICON"))
                .into(itemIcon)

            itemName.text = intent.getStringExtra("USER_NAME")
        }

        exit.setOnClickListener {
            finish()
        }
    }
}