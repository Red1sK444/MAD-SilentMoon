package com.r3d1r4ph.silentmoon.fragments.sleepmusic

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
import com.r3d1r4ph.silentmoon.databinding.FragmentSleepMusicBinding
import com.r3d1r4ph.silentmoon.fragments.home.CustomRecommendationAdapter
import com.r3d1r4ph.silentmoon.fragments.home.RecommendationRecyclerItem
import com.r3d1r4ph.silentmoon.playoption.PlayOptionActivity
import com.r3d1r4ph.silentmoon.utility.OnItemClickListener

class SleepMusicFragment : Fragment(R.layout.fragment_sleep_music), OnItemClickListener {
    companion object {
        val TAG = SleepMusicFragment::class.java.simpleName
        fun newInstance() = SleepMusicFragment()
    }

    private val viewBinding by viewBinding(FragmentSleepMusicBinding::bind)
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
                R.drawable.img_sleep_night_island,
                resources.getString(R.string.sleep_night_island),
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
            ),
            RecommendationRecyclerItem(
                R.drawable.img_sleep_good_night,
                resources.getString(R.string.sleep_good_night),
                resources.getString(R.string.sleep_music_type),
                resources.getString(R.string.sleep_music_duration),
                ContextCompat.getColor(requireContext(), R.color.night_white),
                ContextCompat.getColor(requireContext(), R.color.night_grey),
                R.drawable.img_night_media_dot
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMusicList()
    }

    private fun setMusicList() {
        val recyclerView = viewBinding.sleepMusicListRecyclerView
        val gridLayoutManager =
            GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = gridLayoutManager
        val customAdapter = CustomRecommendationAdapter(sleepMusicDataSet, this)
        recyclerView.adapter = customAdapter
        recyclerView.addItemDecoration(TopicsItemDecoration(10))
    }

    override fun onItemClicked() {
        val intent = Intent(requireContext(), PlayOptionActivity::class.java)
        startActivity(intent)
    }
}