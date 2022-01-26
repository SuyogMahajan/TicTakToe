package com.example.mygame

import android.app.Instrumentation
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.mygame.databinding.ActivityResultsBinding

class results : AppCompatActivity() {
    private lateinit var binding: ActivityResultsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val matchresults = intent.getStringExtra(key)
        binding.tvres.text = matchresults
    }
}