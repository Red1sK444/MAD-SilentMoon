package com.r3d1r4ph.silentmoon.fragments.home

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.r3d1r4ph.silentmoon.R
import com.r3d1r4ph.silentmoon.coursedetails.CourseDetailsActivity
import com.r3d1r4ph.silentmoon.databinding.FragmentHomeBinding
import com.r3d1r4ph.silentmoon.signup.SignUpActivity
import com.r3d1r4ph.silentmoon.utility.OnItemClickListener

class HomeFragment : Fragment(R.layout.fragment_home), OnItemClickListener {

    companion object {
        val TAG: String = HomeFragment::class.java.simpleName
        fun newInstance() = HomeFragment()
    }

    private val viewBinding by viewBinding(FragmentHomeBinding::bind, R.id.rootLayout)
    private lateinit var username: String
    private val recyclerItemDataSet by lazy {
        arrayListOf(
            RecommendationRecyclerItem(
                R.drawable.img_focus,
                resources.getString(R.string.home_focus),
                resources.getString(R.string.home_focus_type),
                resources.getString(R.string.home_focus_duration),
                ContextCompat.getColor(requireContext(), R.color.text_black),
                ContextCompat.getColor(requireContext(), R.color.text_grey),
                R.drawable.img_light_media_dot
            ),
            RecommendationRecyclerItem(
                R.drawable.img_happiness,
                resources.getString(R.string.home_happiness),
                resources.getString(R.string.home_happiness_type),
                resources.getString(R.string.home_happiness_duration),
                ContextCompat.getColor(requireContext(), R.color.text_black),
                ContextCompat.getColor(requireContext(), R.color.text_grey),
                R.drawable.img_light_media_dot
            ),
            RecommendationRecyclerItem(
                R.drawable.img_focus,
                resources.getString(R.string.home_focus),
                resources.getString(R.string.home_focus_type),
                resources.getString(R.string.home_focus_duration),
                ContextCompat.getColor(requireContext(), R.color.text_black),
                ContextCompat.getColor(requireContext(), R.color.text_grey),
                R.drawable.img_light_media_dot
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        username = arguments?.getString(SignUpActivity.USERNAMEKEY).toString()
        setHeaderUsername()

        setSpannableTitle()
        setRecommendedList()
        setButtonClickListeners()
    }

    private fun setHeaderUsername() {
        val spannableStringBuilder = SpannableStringBuilder(resources.getString(R.string.home_good_morning))
        spannableStringBuilder.replace(14, 19, username)
        viewBinding.goodMorningTextView.text = spannableStringBuilder
    }

    private fun setSpannableTitle() {
        val spannableTitle = SpannableString(resources.getString(R.string.app_name_for_spannable))
        val iconSpan = context?.let { ImageSpan(it, R.drawable.ic_onboarding) }

        spannableTitle.setSpan(
            iconSpan,
            7, 10, Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )

        viewBinding.homeTitleTextView.setText(spannableTitle, TextView.BufferType.SPANNABLE)
    }

    private fun setRecommendedList() {
        val recyclerView = viewBinding.recommendedListRecyclerView
        val gridLayoutManager = GridLayoutManager(context, 1, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = gridLayoutManager
        val customAdapter = CustomRecommendationAdapter(recyclerItemDataSet, this)
        recyclerView.adapter = customAdapter
        recyclerView.addItemDecoration(RecommendationsItemDecoration(10, recyclerItemDataSet.size))
    }

    private fun setButtonClickListeners() {
        viewBinding.homeDailyThoughtImageView.setOnClickListener {
            onItemClicked()
        }
        viewBinding.homeBasicsImageView.setOnClickListener {
            onItemClicked()
        }
        viewBinding.homeRelaxationImageView.setOnClickListener {
            onItemClicked()
        }
    }

    override fun onItemClicked() {
        val intent = Intent(requireContext(), CourseDetailsActivity::class.java)
        startActivity(intent)
    }
}