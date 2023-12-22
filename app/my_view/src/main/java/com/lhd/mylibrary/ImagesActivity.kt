package com.lhd.mylibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lhd.mylibrary.databinding.ActivityImagesBinding

class ImagesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImagesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}