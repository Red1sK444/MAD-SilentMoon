package com.r3d1r4ph.silentmoon.fragments.meditatev2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.r3d1r4ph.silentmoon.R
import com.r3d1r4ph.silentmoon.databinding.RecyclerViewRadioButtonItemBinding

class CustomMeditateAdapter(private val recyclerItems: ArrayList<MeditateRecyclerItem>) :
    RecyclerView.Adapter<CustomMeditateAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val vB by viewBinding(RecyclerViewRadioButtonItemBinding::bind)
    }

    private lateinit var lastChecked: RadioButton

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_radio_button_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vB.itemRadioButton.text = recyclerItems[position].text
        holder.vB.itemRadioButton.setTextColor(recyclerItems[position].textColorSelector)
        holder.vB.itemRadioButton.setCompoundDrawablesWithIntrinsicBounds(
            0,
            recyclerItems[position].icon,
            0,
            0
        )

        if (position == 0) {
            holder.vB.itemRadioButton.isChecked = true
            lastChecked = holder.vB.itemRadioButton
        }

        holder.vB.itemRadioButton.setOnClickListener { v ->
            val radioButton: RadioButton = v as RadioButton

            if (radioButton.isChecked && lastChecked != radioButton) {
                lastChecked.isChecked = false
                lastChecked = radioButton
            }
        }
    }

    override fun getItemCount(): Int {
        return recyclerItems.size
    }
}