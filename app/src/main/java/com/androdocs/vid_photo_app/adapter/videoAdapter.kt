package com.androdocs.vid_photo_app.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androdocs.vid_photo_app.R
import com.androdocs.vid_photo_app.databinding.VideoListBinding
import com.androdocs.vid_photo_app.models.Video
import com.androdocs.vid_photo_app.models.VideoFile
import com.androdocs.vid_photo_app.ui.detailsVideo
import com.squareup.picasso.Picasso

class videoAdapter(private  val videolist:List<Video>,val listner:onclickicon):RecyclerView.Adapter<videoAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var binding = VideoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(videolist[position],listner)

    }

    override fun getItemCount(): Int {
        return videolist.size
    }

    class ViewHolder(binding: VideoListBinding) :RecyclerView.ViewHolder(binding.root)
    {
        val photourl=binding.photo
        val photographer=binding.photographer
        val photographername=binding.photographername
        val click=binding.root
        val unfav=binding.unfav
        val fav=binding.faviv


        fun bind(video: Video,listner:onclickicon) {
            Picasso.get().load(video.image).into(photourl);
            Picasso.get().load(video.image).into(photographer);
            photographername.text=video.user.name

            //while loading
            if(listner.isInDatabase(video.video_files[1].link)){
                fav.visibility=View.VISIBLE
                unfav.visibility=View.GONE
            }else{
                fav.visibility=View.GONE
                unfav.visibility=View.VISIBLE
            }

            //when user adds to the favorite list
            var isFav: Boolean
            unfav.setOnClickListener {
                isFav=false
                listner.onItemClick(video,isFav)

                unfav.visibility= View.GONE
                fav.visibility= View.VISIBLE
            }

            //when he unchecks the heart
            fav.setOnClickListener{
                isFav=true
                listner.onItemClick(video,isFav)
                fav.visibility= View.GONE
                unfav.visibility= View.VISIBLE
            }



            val intent = Intent(click.context, detailsVideo::class.java)
            click.setOnClickListener {
                val arrayList: ArrayList<String> = ArrayList()
                arrayList.add(video.image)
                arrayList.add(video.user.name)
                arrayList.add(video.video_files[1].link)
                if(listner.isInDatabase(video.video_files[1].link)){
                    val isTrue="true"
                    arrayList.add(isTrue)
                }else{
                    arrayList.add("false")
                }
                intent.putExtra("array",arrayList)
                click.context.startActivity(intent)
            }




        }


    }


    interface onclickicon{
        fun onItemClick(video: Video,isFav:Boolean)
        fun isInDatabase(url:String):Boolean
    }


}