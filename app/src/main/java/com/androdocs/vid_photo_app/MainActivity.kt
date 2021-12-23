package com.androdocs.vid_photo_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.androdocs.vid_photo_app.databinding.ActivityMainBinding
import com.androdocs.vid_photo_app.fragment.favoriteFragment
import com.androdocs.vid_photo_app.fragment.photoFragment
import com.androdocs.vid_photo_app.fragment.videoFragment
import java.lang.reflect.Array.newInstance
import java.net.URLClassLoader.newInstance

class MainActivity : AppCompatActivity() {

    //creating variable for binding
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //inflating binding
        binding= ActivityMainBinding.inflate(layoutInflater)

        //setting the contentview to binding.root
        setContentView(binding.root)

        //hiding toolbar
        getSupportActionBar()?.hide()


        val photofragment = photoFragment()
        val videofragment= videoFragment()
        val favoritefragment=favoriteFragment()
         supportFragmentManager.beginTransaction().apply {
             replace(R.id.framelayout1,photofragment)
             commit()
         }

        binding.phototv.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.framelayout1,photofragment)
                commit()
            }
        }

        binding.videotv.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.framelayout1, videofragment)
                commit()
            }
        }

            binding.favtv.setOnClickListener {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.framelayout1, favoritefragment)
                    commit()
                }

            }






    }
}