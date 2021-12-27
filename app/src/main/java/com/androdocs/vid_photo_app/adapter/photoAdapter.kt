package com.androdocs.vid_photo_app.adapter



import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androdocs.vid_photo_app.R
import com.androdocs.vid_photo_app.databinding.PhotoListBinding
import com.androdocs.vid_photo_app.models.Photo
import com.androdocs.vid_photo_app.models.Video
import com.androdocs.vid_photo_app.ui.detailsPhoto
import com.androdocs.vid_photo_app.ui.detailsVideo
import com.squareup.picasso.Picasso


class photoAdapter(private val photolist:List<Photo>,val listner: photoAdapter.onclickicon) : RecyclerView.Adapter<photoAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var binding = PhotoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(photolist[position],listner)

    }

    override fun getItemCount(): Int {
        return photolist.size
    }

    class ViewHolder(binding: PhotoListBinding) :RecyclerView.ViewHolder(binding.root)
    {
        val photourl=binding.photo
        val photographer=binding.photographer
        val photographername=binding.photographername
        val click=binding.root
        val fav=binding.faviv



        fun bind(photo: Photo,listner:onclickicon) {
            Picasso.get().load(photo.src.landscape).into(photourl);
            Picasso.get().load(photo.src.tiny).into(photographer);
            photographername.text=photo.photographer

            val arrayList: ArrayList<String> = ArrayList()
            arrayList.add(photo.src.large)
            arrayList.add(photo.alt)
            arrayList.add(photo.src.tiny)
            arrayList.add(photo.photographer)
            val intent = Intent(click.context, detailsPhoto::class.java)

            //when clicks on a particular photo
            click.setOnClickListener {
                intent.putExtra("array",arrayList)
                click.context.startActivity(intent)
            }

            //when user adds to the favorite list

            fav.setOnClickListener {
                listner.onItemClick(photo)
                fav.setBackgroundResource(R.drawable.favemoji2)
            }







//            click.setOnClickListener{listner(photo)}

        }


    }


    interface onclickicon{
        fun onItemClick(photo: Photo)
    }

}