package com.androdocs.vid_photo_app.fragment

import android.app.Application
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

//    companion object{
//        const val videodet="detailed video"
//        const val videolink="link of video"
//    }


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





//                        {
//                            val intent = Intent (getActivity(), detailsVideo::class.java)
//                            intent.putExtra(videodet,it)
//                            getActivity()?.startActivity(intent)
//                        }
                    }
                }
                else{
                    val videoresponse: videoresponse = response.body()!!
                    Log.d("value","error")
                }
            }

            override fun onFailure(call: Call<videoresponse>, t: Throwable) {
                // textView.setText(t.message)
                Toast.makeText(activity,"Something wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onItemClick(video: Video) {


//        val favoriteRepository = FavoriteRepository(FavoriteDatabase(requireContext()))
//        val factory = FavoriteViewModalFactory(favoriteRepository)
//        viewModel = ViewModelProvider(this, factory).get(FavoriteViewModal::class.java)



        val dao = FavoriteDatabase.getInstance(this.requireContext()).getFavoritesDao
        val repository = FavoriteRepository(dao)
        val factory = FavoriteViewModalFactory(repository)
        viewModel = ViewModelProvider(this,factory).get(FavoriteViewModal::class.java)





        val image:String=video.image
        val name:String=video.user.name
        val link:String=video.video_files[1].link
        val desc:String=video.user.name
        val favorites= Favorite(2,link,name,true,image,desc)
        viewModel.addFavorite(favorites)
        Log.d("Sucess","added sucessfully"+favorites)

    }


}