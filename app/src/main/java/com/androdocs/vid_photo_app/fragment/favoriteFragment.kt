package com.androdocs.vid_photo_app.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.androdocs.vid_photo_app.R
import com.androdocs.vid_photo_app.adapter.favoriteAdapter
import com.androdocs.vid_photo_app.databinding.FragmentFavoriteBinding
import com.androdocs.vid_photo_app.roomdb.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class favoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModal
    private lateinit var favoriteRecyclerViewAdapter: favoriteAdapter
    private lateinit var favrv: RecyclerView

    private var _binding : FragmentFavoriteBinding? =null
    private var favourites = ArrayList<Favorite>()
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val dao = FavoriteDatabase.getInstance(this.requireContext()).getFavoritesDao
        val repository = FavoriteRepository(dao)
        val factory = FavoriteViewModalFactory(repository)
        favoriteViewModel = ViewModelProvider(this,factory).get(FavoriteViewModal::class.java)

        setRecyclerView()



//        binding.removeFavourite.setOnClickListener {
//            MaterialAlertDialogBuilder(requireContext())
//                .setTitle("Are you sure want to remove all the favourites?")
//                .setNegativeButton("NO") { _, _ ->
//                    // Respond to negative button press
//                }
//                .setPositiveButton("YES") { _, _ ->
//                    favoriteViewModel.clearAllFavourites()
//                }
//                .show()
//
//        }

        return binding.root

    }


    private fun setRecyclerView() {
        favoriteViewModel.allfavorites.observe(viewLifecycleOwner,{
            if (it.isNotEmpty()){
                favourites = it as ArrayList<Favorite>
                binding.noData.visibility = View.GONE
                favoriteRecyclerViewAdapter = favoriteAdapter(it)
                binding.favrv.adapter = favoriteRecyclerViewAdapter
                Log.d("data", "$favourites")
            }else{
                binding.noData.visibility = View.VISIBLE
                Log.d("data", "empty")
            }
        })
    }


}