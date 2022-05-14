package com.r3d1r4ph.silentmoon.choosetopic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.r3d1r4ph.silentmoon.R
import com.r3d1r4ph.silentmoon.databinding.RecyclerViewVerticalItemBinding
import com.r3d1r4ph.silentmoon.utility.OnItemClickListener

class CustomTopicAdapter(
    private val recyclerItems: ArrayList<TopicRecyclerItem>,
    private val itemClickListener: OnItemClickListener? = null
) :
    RecyclerView.Adapter<CustomTopicAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val vB by viewBinding(RecyclerViewVerticalItemBinding::bind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_vertical_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vB.recyclerItemImageView.setImageResource(recyclerItems[position].image)
        holder.vB.recyclerItemTextView.text = recyclerItems[position].text
        holder.vB.recyclerItemTextView.setTextColor(recyclerItems[position].color)
        itemClickListener?.let { listener ->
            holder.itemView.setOnClickListener {
                listener.onItemClicked()
            }
        }
    }

    override fun getItemCount(): Int {
        return recyclerItems.size
    }
}