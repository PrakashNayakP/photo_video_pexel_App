package com.androdocs.vid_photo_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.androdocs.vid_photo_app.databinding.ActivityMainBinding
import com.androdocs.vid_photo_app.databinding.FragmentPhotoBinding
import com.androdocs.vid_photo_app.fragment.favoriteFragment
import com.androdocs.vid_photo_app.fragment.photoFragment
import com.androdocs.vid_photo_app.fragment.videoFragment
import com.google.android.material.tabs.TabLayoutMediator


private val mfragmentList= arrayOf("Photos","Videos","Favorites")
class MainActivity : AppCompatActivity() {

    //creating variable for binding
    lateinit var query:String
    private lateinit var binding:ActivityMainBinding
    private lateinit var rvbinding: FragmentPhotoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
//        tabLayout.setupWithViewPager(viewPager)

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = mfragmentList[position]

            }.attach()
        }

       setQuery("popular");





//        val photofragment = photoFragment()
//        val videofragment= videoFragment()
//        val favoritefragment= favoriteFragment()
//         supportFragmentManager.beginTransaction().apply {
//             replace(R.id.framelayout1,photofragment)
//             commit()
//         }
//
//        binding.phototv.setOnClickListener{
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.framelayout1,photofragment)
//                commit()
//            }
//        }
//
//        binding.videotv.setOnClickListener {
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.framelayout1, videofragment)
//                commit()
//            }
//        }
//
//            binding.favtv.setOnClickListener {
//                supportFragmentManager.beginTransaction().apply {
//                    replace(R.id.framelayout1, favoritefragment)
//                    commit()
//                }
//
//            }

        binding.searchView.isSubmitButtonEnabled = true

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(str: String): Boolean {

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



        //     inner class ViewPagerAdapter(manager:FragmentManager):
//        FragmentPagerAdapter(manager){
//
//         private val fragments:ArrayList<Fragment>
//         private val titles:ArrayList<String>
//         init {
//             fragments=ArrayList<Fragment>()
//             titles=ArrayList<String>()
//         }
//         override fun getCount(): Int {
//            return fragments.size
//         }
//
//         override fun getItem(position: Int): Fragment {
//             return fragments[position]
//         }
//
//
//         fun addFragment(fragment:Fragment,title:String,index:Int){
//             if(index==3){
//                 fragments.set(0,fragment)
//             }
//             else {
//                 fragments.add(fragment)
//                 titles.add(title)
//             }
//         }
//
//         override fun getPageTitle(position: Int): CharSequence? {
//             return titles[position]
//         }
//
//
//
//     }




//    private fun search(query:String){
//        val intent = Intent(this, detailsPhoto::class.java)
//             val call = retrofitClient.instance.getSearchImage(query)
//             call.enqueue(object : Callback<photoresponse> {
//                 @RequiresApi(Build.VERSION_CODES.O)
//                 override fun onResponse(
//                     call: Call<photoresponse>,
//                     response: Response<photoresponse>
//                 ) {
//                     if (response.code() == 200) {
//                         val photoresponse: photoresponse = response.body()!!
//                         Log.d("value", photoresponse.toString())
//                         rvbinding.recyclerView1.apply {
//                             setHasFixedSize(true)
//                             layoutManager = GridLayoutManager(this@MainActivity, 1)
//                             adapter = photoAdapter(photoresponse.photos) {
//                                 intent.putExtra(photoFragment.photodet, it)
//                                 startActivity(intent)
//                             }
//                         }
//                     } else {
//                         val photoresponse: photoresponse = response.body()!!
//                         Log.d("value", "error")
//                     }
//                 }
//
//                 override fun onFailure(call: Call<photoresponse>, t: Throwable) {
//                     // textView.setText(t.message)
//                     Toast.makeText(this@MainActivity, "Something wrong", Toast.LENGTH_SHORT).show()
//                 }
//             })



//    }
}