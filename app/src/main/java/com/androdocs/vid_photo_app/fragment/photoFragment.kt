package com.androdocs.vid_photo_app.fragment

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
import androidx.recyclerview.widget.GridLayoutManager
import com.androdocs.vid_photo_app.databinding.FragmentPhotoBinding
import com.androdocs.vid_photo_app.api.retrofitClient
import com.androdocs.vid_photo_app.R
import com.androdocs.vid_photo_app.adapter.photoAdapter
import com.androdocs.vid_photo_app.databinding.PhotoListBinding
import com.androdocs.vid_photo_app.detailsPhoto
import com.androdocs.vid_photo_app.models.photoresponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class photoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentPhotoBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        val view= binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val call= retrofitClient.instance.getPexelsImage()
        call.enqueue(object : Callback<photoresponse> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<photoresponse>, response: Response<photoresponse>) {
                if (response.code() == 200) {
                    val photoresponse: photoresponse = response.body()!!
                    Log.d("value",photoresponse.toString())
                    binding.recyclerView1.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(activity,1)
                        adapter = photoAdapter(photoresponse.photos){
                            val intent = Intent (getActivity(), detailsPhoto::class.java)
                            getActivity()?.startActivity(intent)
                        }
                    }
                }
                else{
                    val photoresponse: photoresponse = response.body()!!
                    Log.d("value","error")
                }
            }

            override fun onFailure(call: Call<photoresponse> , t: Throwable) {
                // textView.setText(t.message)
                Toast.makeText(activity,"Something wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
//