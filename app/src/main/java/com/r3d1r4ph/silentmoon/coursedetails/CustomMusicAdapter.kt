package com.r3d1r4ph.silentmoon.coursedetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.r3d1r4ph.silentmoon.R
import com.r3d1r4ph.silentmoon.databinding.RecyclerViewMusicItemBinding
import com.r3d1r4ph.silentmoon.utility.OnItemClickListener
import java.util.*

class CustomMusicAdapter(
    private val recyclerItems: ArrayList<MusicRecyclerItem>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CustomMusicAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding by viewBinding(RecyclerViewMusicItemBinding::bind)
        private lateinit var clickListener: OnItemClickListener

        init {
            itemView.setOnClickListener {
                clickListener.onItemClicked()
            }
        }

        fun bind(recyclerItem: MusicRecyclerItem, itemClickListener: OnItemClickListener) {
            viewBinding.itemMusicIconImageView.setImageResource(recyclerItem.icon)
            viewBinding.itemMusicNameTextView.text = recyclerItem.song
            viewBinding.itemMusicDurationTextView.text = recyclerItem.duration
            clickListener = itemClickListener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_music_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recyclerItems[position], itemClickListener)
    }

    override fun getItemCount(): Int {
        return recyclerItems.size
    }
}