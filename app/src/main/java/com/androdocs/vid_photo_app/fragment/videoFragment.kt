package com.androdocs.vid_photo_app.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.androdocs.vid_photo_app.R
import com.androdocs.vid_photo_app.adapter.videoAdapter
import com.androdocs.vid_photo_app.api.retrofitClient
import com.androdocs.vid_photo_app.databinding.FragmentVideoBinding
import com.androdocs.vid_photo_app.models.photoresponse
import com.androdocs.vid_photo_app.models.videoresponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class videoFragment : Fragment() {
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
                        adapter = videoAdapter(videoresponse.videos)
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


}