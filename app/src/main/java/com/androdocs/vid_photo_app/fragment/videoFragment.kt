package com.androdocs.vid_photo_app.fragment

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.androdocs.vid_photo_app.adapter.videoAdapter
import com.androdocs.vid_photo_app.api.retrofitClient
import com.androdocs.vid_photo_app.databinding.FragmentVideoBinding
import com.androdocs.vid_photo_app.models.Video
import com.androdocs.vid_photo_app.ui.detailsVideo
import com.androdocs.vid_photo_app.models.videoresponse
import com.androdocs.vid_photo_app.roomdb.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class videoFragment : Fragment(),videoAdapter.onclickicon {

    private var isVideoInRoom = arrayListOf<String>()
    lateinit var viewModel: FavoriteViewModal
    private var _binding: FragmentVideoBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        val view= binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val call= retrofitClient.instance.getPexelsVideo()
        call.enqueue(object : Callback<videoresponse> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<videoresponse>, response: Response<videoresponse>) {
                if (response.code() == 200) {
                    val videoresponse: videoresponse = response.body()!!
                    Log.d("value",videoresponse.toString())
                    binding.recyclerView2.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(activity,1)
                        adapter = videoAdapter(videoresponse.videos,this@videoFragment)
                        binding.emptyView.visibility=View.GONE

                    }
                }
                else{
//                    val videoresponse: videoresponse = rsesponse.body()!!
                    Log.d("value","error")
                }
            }

            override fun onFailure(call: Call<videoresponse>, t: Throwable) {
                // textView.setText(t.message)
                Toast.makeText(activity,"Something wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onItemClick(video: Video,isFav:Boolean) {
        val dao = FavoriteDatabase.getInstance(this.requireContext()).getFavoritesDao
        val repository = FavoriteRepository(dao)
        val factory = FavoriteViewModalFactory(repository)
        viewModel = ViewModelProvider(this,factory).get(FavoriteViewModal::class.java)
        val link:String=video.video_files[1].link
        val name:String=video.user.name
        val image:String=video.image
        val desc:String=video.user.name
        val favorites= Favorite(link,name,false,image,desc)
        if(!isFav) {
            viewModel.addFavorite(favorites)
            isVideoInRoom.add(link)
            saveArray()
            Log.d("Success", "added successfully$isVideoInRoom")
        }
        else{
            viewModel.deleteFavorite(favorites)
            isVideoInRoom.remove(link)
            saveArray()
            Log.d("Success", "deleted successfully$isVideoInRoom")

        }
    }

    override fun isInDatabase(url: String): Boolean {
        isVideoInRoom=getArray()
        Log.d("checking", "$url is in array ${isVideoInRoom!!.contains(url)}")
        return isVideoInRoom!!.contains(url)
    }


    fun saveArray(): Boolean {
        val sp = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
//        val sp: SharedPreferences =this.activity.getSharedPreferences("SHARED_PREFS_NAME", Activity.MODE_PRIVATE)
        val mEdit1 = sp.edit()
        val set: MutableSet<String> = HashSet()
        set.addAll(isVideoInRoom)
        mEdit1.putStringSet("list", set)
        return mEdit1.commit()
    }

    private fun getArray(): ArrayList<String> {
        val sp = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
        //NOTE: if shared preference is null, the method return empty Hashset and not null
        val set = sp.getStringSet("list", HashSet())
        return ArrayList(set)
    }




}