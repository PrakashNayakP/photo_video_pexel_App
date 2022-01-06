package com.androdocs.vid_photo_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.androdocs.vid_photo_app.databinding.ActivityMainBinding
import com.androdocs.vid_photo_app.databinding.FragmentPhotoBinding
import com.androdocs.vid_photo_app.fragment.favoriteFragment
import com.androdocs.vid_photo_app.fragment.photoFragment
import com.androdocs.vid_photo_app.fragment.videoFragment
import com.androdocs.vid_photo_app.roomdb.*
import com.google.android.material.tabs.TabLayoutMediator



private val mfragmentList= arrayOf("Photos","Videos","Favorites")
class MainActivity : AppCompatActivity(){

    //creating variable for binding
    lateinit var query:String
    lateinit var viewModel: FavoriteViewModal
    private lateinit var binding:ActivityMainBinding
    private lateinit var rvbinding: FragmentPhotoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setTheme(R.style.Theme_Vid_photo_app)
        //inflating binding
        binding= ActivityMainBinding.inflate(layoutInflater)
        rvbinding= FragmentPhotoBinding.inflate(layoutInflater)




        //setting the contentview to binding.root
        setContentView(binding.root)

        //hiding toolbar
        getSupportActionBar()?.hide()



        fun setQuery(str:String){
            if(str.isNotEmpty()) {
                query = str
            }

            val viewPager = binding.viewpager
            val tabLayout = binding.tablayout
            val vadapter = ViewPagerAdapter(supportFragmentManager, lifecycle)


            viewPager.adapter = vadapter

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = mfragmentList[position]

            }.attach()
        }


        for (i in 0 until binding.tablayout.tabCount) {
            val tab = (binding.tablayout.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as ViewGroup.MarginLayoutParams
            if (i == 1) {
            p.setMargins(0, 0, 220, 0)
            } else {
            p.setMargins(0, 0, 10, 0)
            }
        }




       setQuery("popular");


        binding.searchView.queryHint = "Search photos, videos, artists";

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(str: String): Boolean {
                binding.searchView.clearFocus();
                setQuery(str);


                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })



    }


     internal inner class ViewPagerAdapter(manager:FragmentManager,lifecycle: Lifecycle):
        FragmentStateAdapter(manager,lifecycle){



         override fun getItemCount(): Int {
             return mfragmentList.size
         }

         override fun createFragment(position: Int): Fragment {
             when(position){
                 0->return  photoFragment(query)
                 1 ->return videoFragment()
                 2 ->return favoriteFragment()
             }
             return photoFragment(query)
         }


     }




}