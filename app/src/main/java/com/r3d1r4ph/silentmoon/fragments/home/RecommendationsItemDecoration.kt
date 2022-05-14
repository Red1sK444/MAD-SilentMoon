package com.r3d1r4ph.silentmoon.fragments.home

import android.content.res.Resources
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecommendationsItemDecoration(private val margin: Int, private val itemQuantity: Int) : RecyclerView.ItemDecoration() {

    private val Number.toPx get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val marginInPx = margin.toPx.toInt()
        outRect.set(marginInPx, marginInPx, marginInPx, marginInPx)
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.left += marginInPx
        }
        if (parent.getChildLayoutPosition(view) == itemQuantity - 1) {
            outRect.right += marginInPx
        }
    }
}