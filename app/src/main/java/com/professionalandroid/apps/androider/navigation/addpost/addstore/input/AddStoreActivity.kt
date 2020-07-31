package com.professionalandroid.apps.androider.navigation.addpost.addstore.input

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.professionalandroid.apps.androider.R
import kotlinx.android.synthetic.main.activity_addstore.*

class AddStoreActivity : AppCompatActivity() {
    private val CATEGORY_REQUEST_CODE = 5001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addstore)

        layout_addstore_category.setOnClickListener {
            startActivityForResult(
                Intent(this, ChooseCategoryActivity::class.java),
                CATEGORY_REQUEST_CODE
            )
        }

        btn_addstore_back.setOnClickListener {
            onBackPressed()
        }

        textview_addstore_category.text = "??"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CATEGORY_REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    val category = data?.getStringExtra("category")
                    textview_addstore_category.text = category
                }
                else -> Toast.makeText(this, "fail", Toast.LENGTH_LONG).show()
            }
        }
    }
}