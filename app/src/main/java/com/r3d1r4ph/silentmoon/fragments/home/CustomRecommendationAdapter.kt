package com.r3d1r4ph.silentmoon.fragments.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.r3d1r4ph.silentmoon.R
import com.r3d1r4ph.silentmoon.databinding.RecyclerViewRowRecommendationItemBinding
import com.r3d1r4ph.silentmoon.utility.OnItemClickListener

class CustomRecommendationAdapter(
    private val recyclerItems: ArrayList<RecommendationRecyclerItem>,
    private val itemClickListener: OnItemClickListener? = null,
    private val singleItemExist: Boolean = false,
    private val singleItemClickListener: OnItemClickListener? = null
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolderDefault(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding by viewBinding(RecyclerViewRowRecommendationItemBinding::bind)
        private var clickListener: OnItemClickListener? = null

        init {
            itemView.setOnClickListener {
                clickListener?.onItemClicked()
            }
        }

        fun bind(
            recyclerItem: RecommendationRecyclerItem,
            itemClickListener: OnItemClickListener?
        ) {
            with(viewBinding) {
                itemPreviewImageView.setImageResource(recyclerItem.image)
                itemTitleTextView.text = recyclerItem.title
                itemTitleTextView.setTextColor(recyclerItem.primary)
                itemTypeTextView.text = recyclerItem.type
                itemTypeTextView.setTextColor(recyclerItem.secondary)
                itemDurationTextView.text = recyclerItem.duration
                itemDurationTextView.setTextColor(recyclerItem.secondary)
                itemMediaDotImageView.setImageResource(recyclerItem.dot)
            }
            clickListener = itemClickListener
        }
    }

    class ViewHolderSingle(view: View) : RecyclerView.ViewHolder(view) {
        private var singleClickListener: OnItemClickListener? = null

        init {
            itemView.setOnClickListener {
                singleClickListener?.onItemClicked()
            }
        }

        fun bind(singleItemClickListener: OnItemClickListener?) {
            singleClickListener = singleItemClickListener
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (singleItemExist && position == 0) {
            1
        } else {
            0
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_row_recommendation_item, parent, false)
            ViewHolderDefault(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_the_ocean_moon, parent, false)
            ViewHolderSingle(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (singleItemExist) {
            if (holder.itemViewType == 0) {
                (holder as ViewHolderDefault).bind(recyclerItems[position-1], itemClickListener)
            } else {
                (holder as ViewHolderSingle).bind(singleItemClickListener)
            }
        } else {
            (holder as ViewHolderDefault).bind(recyclerItems[position], itemClickListener)
        }
    }

    override fun getItemCount(): Int {
        return if (singleItemExist) {
            recyclerItems.size + 1
        } else {
            recyclerItems.size
        }
    }
}