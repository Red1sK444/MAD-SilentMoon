package com.r3d1r4ph.silentmoon.fragments.meditatev2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.internal.findRootView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.r3d1r4ph.silentmoon.R
import com.r3d1r4ph.silentmoon.choosetopic.CustomTopicAdapter
import com.r3d1r4ph.silentmoon.choosetopic.TopicRecyclerItem
import com.r3d1r4ph.silentmoon.choosetopic.TopicsItemDecoration
import com.r3d1r4ph.silentmoon.coursedetails.CourseDetailsActivity
import com.r3d1r4ph.silentmoon.databinding.FragmentMeditateV2Binding
import com.r3d1r4ph.silentmoon.fragments.home.RecommendationsItemDecoration

class MeditateV2Fragment : Fragment(R.layout.fragment_meditate_v2) {

    companion object {
        val TAG: String = MeditateV2Fragment::class.java.simpleName
        fun newInstance() = MeditateV2Fragment()
    }

    private val viewBinding by viewBinding(FragmentMeditateV2Binding::bind)
    private val meditateReleasesDataSet by lazy {
        arrayListOf(
            TopicRecyclerItem(
                R.drawable.img_7_days_of_calm,
                resources.getString(R.string.meditate_7_days_of_calm),
                ContextCompat.getColor(requireContext(), R.color.almost_white)
            ),
            TopicRecyclerItem(
                R.drawable.img_anxiet_release,
                resources.getString(R.string.meditate_anxiet_release),
                ContextCompat.getColor(requireContext(), R.color.almost_white)
            ),
            TopicRecyclerItem(
                R.drawable.img_summer_day,
                resources.getString(R.string.meditate_summer_day),
                ContextCompat.getColor(requireContext(), R.color.almost_white)
            ),
            TopicRecyclerItem(
                R.drawable.img_how_to_meditate,
                resources.getString(R.string.meditate_how_to),
                ContextCompat.getColor(requireContext(), R.color.almost_white)
            )
        )
    }
    private val meditateTabsDataSet by lazy {
        arrayListOf(
            MeditateRecyclerItem(
                R.drawable.ic_meditate_all_selector,
                resources.getString(R.string.tab_all),
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.radio_button_black_text_selector
                )
            ),
            MeditateRecyclerItem(
                R.drawable.ic_meditate_my_selector,
                resources.getString(R.string.tab_my),
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.radio_button_black_text_selector
                )
            ),
            MeditateRecyclerItem(
                R.drawable.ic_meditate_anxious_selector,
                resources.getString(R.string.tab_anxious),
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.radio_button_black_text_selector
                )
            ),
            MeditateRecyclerItem(
                R.drawable.ic_meditate_sleep_selector,
                resources.getString(R.string.tab_sleep),
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.radio_button_black_text_selector
                )
            ),
            MeditateRecyclerItem(
                R.drawable.ic_meditate_kids_selector,
                resources.getString(R.string.tab_kids),
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.radio_button_black_text_selector
                )
            )
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMeditateTabs()
        setMeditateReleases()

        viewBinding.dailyCalmPreviewImageView.setOnClickListener {
            val intent = Intent(requireContext(), CourseDetailsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setMeditateTabs() {
        val recyclerView = viewBinding.tabsRecyclerView
        val gridLayoutManager =
            GridLayoutManager(requireContext(), 1, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = gridLayoutManager
        val customAdapter = CustomMeditateAdapter(meditateTabsDataSet)
        recyclerView.adapter = customAdapter
        recyclerView.addItemDecoration(RecommendationsItemDecoration(10, meditateTabsDataSet.size))
    }

    private fun setMeditateReleases() {
        val recyclerView = viewBinding.meditateReleasesRecyclerView
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recyclerView.layoutManager = staggeredGridLayoutManager
        val customAdapter = CustomTopicAdapter(meditateReleasesDataSet)
        recyclerView.adapter = customAdapter
        recyclerView.addItemDecoration(TopicsItemDecoration(10))
    }
}