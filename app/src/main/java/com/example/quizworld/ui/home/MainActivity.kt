package com.example.quizworld.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizworld.R

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //supportActionBar?.hide()
       // val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CategoryFragment.newInstance())
                .commitNow()
        }
    }



}