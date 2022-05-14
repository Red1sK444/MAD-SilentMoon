package com.r3d1r4ph.silentmoon.fragments.sleep

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.r3d1r4ph.silentmoon.R
import com.r3d1r4ph.silentmoon.choosetopic.TopicsItemDecoration
import com.r3d1r4ph.silentmoon.databinding.FragmentSleepBinding
import com.r3d1r4ph.silentmoon.fragments.home.CustomRecommendationAdapter
import com.r3d1r4ph.silentmoon.fragments.home.RecommendationRecyclerItem
import com.r3d1r4ph.silentmoon.fragments.home.RecommendationsItemDecoration
import com.r3d1r4ph.silentmoon.fragments.meditatev2.CustomMeditateAdapter
import com.r3d1r4ph.silentmoon.fragments.meditatev2.MeditateRecyclerItem
import com.r3d1r4ph.silentmoon.fragments.sleepmusic.SleepMusicFragment
import com.r3d1r4ph.silentmoon.playoption.PlayOptionActivity
import com.r3d1r4ph.silentmoon.utility.FragmentNavigator
import com.r3d1r4ph.silentmoon.utility.OnItemClickListener

class SleepFragment : Fragment(R.layout.fragment_sleep), OnItemClickListener {
    private val viewBinding by viewBinding(FragmentSleepBinding::bind)
    private val sleepTabsDataSet by lazy {
        arrayListOf(
            MeditateRecyclerItem(
                R.drawable.ic_sleep_all_selector,
                resources.getString(R.string.tab_all),
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.radio_button_white_text_selector
                )
            ),
            MeditateRecyclerItem(
                R.drawable.ic_sleep_my_selector,
                resources.getString(R.string.tab_my),
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.radio_button_white_text_selector
                )
            ),
            MeditateRecyclerItem(
                R.drawable.ic_sleep_anxious_selector,
                resources.getString(R.string.tab_anxious),
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.radio_button_white_text_selector
                )
            ),
            MeditateRecyclerItem(
                R.drawable.ic_sleep_sleep_selector,
                resources.getString(R.string.tab_sleep),
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.radio_button_white_text_selector
                )
            ),
            MeditateRecyclerItem(
                R.drawable.ic_sleep_kids_selector,
                resources.getString(R.string.tab_kids),
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.radio_button_white_text_selector
                )
            )
        )
    }
    private val sleepMusicDataSet by lazy {
        arrayListOf(
            RecommendationRecyclerItem(
                R.drawable.img_sleep_night_island,
                resources.getString(R.string.sleep_night_island),
                resources.getString(R.string.sleep_music_type),
                resources.getString(R.string.sleep_music_duration),
                ContextCompat.getColor(requireContext(), R.color.night_white),
                ContextCompat.getColor(requireContext(), R.color.night_grey),
                R.drawable.img_night_media_dot
            ),
            RecommendationRecyclerItem(
                R.drawable.img_sleep_sweet_sleep,
                resources.getString(R.string.sleep_sweet_sleep),
                resources.getString(R.string.sleep_music_type),
                resources.getString(R.string.sleep_music_duration),
                ContextCompat.getColor(requireContext(), R.color.night_white),
                ContextCompat.getColor(requireContext(), R.color.night_grey),
                R.drawable.img_night_media_dot
            ),
            RecommendationRecyclerItem(
                R.drawable.img_sleep_good_night,
                resources.getString(R.string.sleep_good_night),
                resources.getString(R.string.sleep_music_type),
                resources.getString(R.string.sleep_music_duration),
                ContextCompat.getColor(requireContext(), R.color.night_white),
                ContextCompat.getColor(requireContext(), R.color.night_grey),
                R.drawable.img_night_media_dot
            ),
            RecommendationRecyclerItem(
                R.drawable.img_sleep_moon_clouds,
                resources.getString(R.string.sleep_moon_clouds),
                resources.getString(R.string.sleep_music_type),
                resources.getString(R.string.sleep_music_duration),
                ContextCompat.getColor(requireContext(), R.color.night_white),
                ContextCompat.getColor(requireContext(), R.color.night_grey),
                R.drawable.img_night_media_dot
            )
        )
    }

    companion object {
        val TAG: String = SleepFragment::class.java.simpleName
        fun newInstance() = SleepFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSleepTabs()
        setSleepMusic()
    }

    private fun setSleepTabs() {
        val recyclerView = viewBinding.sleepTabsRecyclerView
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = linearLayoutManager
        val customAdapter = CustomMeditateAdapter(sleepTabsDataSet)
        recyclerView.adapter = customAdapter
        recyclerView.addItemDecoration(RecommendationsItemDecoration(10, sleepTabsDataSet.size))
    }

    private fun setSleepMusic() {
        val recyclerView = viewBinding.sleepMusicRecyclerView
        val gridLayoutManager =
            GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) {
                    2
                } else {
                    1
                }
            }
        }
        recyclerView.layoutManager = gridLayoutManager
        val customAdapter = CustomRecommendationAdapter(
            sleepMusicDataSet,
            this,
            true,
            object : OnItemClickListener {
                override fun onItemClicked() {
                    (activity as FragmentNavigator).navigateToFragment(
                        SleepMusicFragment.TAG,
                        SleepMusicFragment.newInstance(),
                        true
                    )
                }
            })
        recyclerView.adapter = customAdapter
        recyclerView.addItemDecoration(TopicsItemDecoration(10))
    }

    override fun onItemClicked() {
        val intent = Intent(requireContext(), PlayOptionActivity::class.java)
        startActivity(intent)
    }
}