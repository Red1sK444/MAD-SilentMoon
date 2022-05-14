package com.r3d1r4ph.silentmoon.coursedetails

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ImageSpan
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.appbar.AppBarLayout
import com.r3d1r4ph.silentmoon.R
import com.r3d1r4ph.silentmoon.databinding.ActivityCourseDetailsBinding
import com.r3d1r4ph.silentmoon.musicv2.MusicV2Activity
import com.r3d1r4ph.silentmoon.utility.OnItemClickListener

class CourseDetailsActivity : AppCompatActivity(), OnItemClickListener {
    private val viewBinding by viewBinding(ActivityCourseDetailsBinding::bind, R.id.rootLayout)
    private val musicListDataSet by lazy {
        arrayListOf(
            MusicRecyclerItem(
                R.drawable.ic_course_details_selected_music,
                resources.getString(R.string.course_details_focus_attention),
                resources.getString(R.string.course_details_focus_attention_duration)
            ),
            MusicRecyclerItem(
                R.drawable.ic_course_details_music,
                resources.getString(R.string.course_details_body_scan),
                resources.getString(R.string.course_details_body_scan_duration)
            ),
            MusicRecyclerItem(
                R.drawable.ic_course_details_music,
                resources.getString(R.string.course_details_making_happiness),
                resources.getString(R.string.course_details_making_happiness_duration)
            ),
            MusicRecyclerItem(
                R.drawable.ic_course_details_music,
                resources.getString(R.string.course_details_focus_attention),
                resources.getString(R.string.course_details_focus_attention_duration)
            ),
            MusicRecyclerItem(
                R.drawable.ic_course_details_music,
                resources.getString(R.string.course_details_focus_attention),
                resources.getString(R.string.course_details_focus_attention_duration)
            ),
            MusicRecyclerItem(
                R.drawable.ic_course_details_music,
                resources.getString(R.string.course_details_focus_attention),
                resources.getString(R.string.course_details_focus_attention_duration)
            ),
            MusicRecyclerItem(
                R.drawable.ic_course_details_music,
                resources.getString(R.string.course_details_focus_attention),
                resources.getString(R.string.course_details_focus_attention_duration)
            ),
            MusicRecyclerItem(
                R.drawable.ic_course_details_music,
                resources.getString(R.string.course_details_focus_attention),
                resources.getString(R.string.course_details_focus_attention_duration)
            ),
            MusicRecyclerItem(
                R.drawable.ic_course_details_music,
                resources.getString(R.string.course_details_focus_attention),
                resources.getString(R.string.course_details_focus_attention_duration)
            ),
            MusicRecyclerItem(
                R.drawable.ic_course_details_music,
                resources.getString(R.string.course_details_focus_attention),
                resources.getString(R.string.course_details_focus_attention_duration)
            ),
            MusicRecyclerItem(
                R.drawable.ic_course_details_music,
                resources.getString(R.string.course_details_focus_attention),
                resources.getString(R.string.course_details_focus_attention_duration)
            ),
            MusicRecyclerItem(
                R.drawable.ic_course_details_music,
                resources.getString(R.string.course_details_focus_attention),
                resources.getString(R.string.course_details_focus_attention_duration)
            ),
            MusicRecyclerItem(
                R.drawable.ic_course_details_music,
                resources.getString(R.string.course_details_focus_attention),
                resources.getString(R.string.course_details_focus_attention_duration)
            ),
            MusicRecyclerItem(
                R.drawable.ic_course_details_music,
                resources.getString(R.string.course_details_focus_attention),
                resources.getString(R.string.course_details_focus_attention_duration)
            ),
            MusicRecyclerItem(
                R.drawable.ic_course_details_music,
                resources.getString(R.string.course_details_focus_attention),
                resources.getString(R.string.course_details_focus_attention_duration)
            ),
            MusicRecyclerItem(
                R.drawable.ic_course_details_music,
                resources.getString(R.string.course_details_focus_attention),
                resources.getString(R.string.course_details_focus_attention_duration)
            )
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_details)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        fabStatusBarMargin(viewBinding.courseDetailsDownloadFAB)
        fabStatusBarMargin(viewBinding.courseDetailsLikeFAB)

        statusBarMarginConstraint(viewBinding.courseDetailsTitleTextView)
        statusBarMarginAppBar(viewBinding.courseDetailsTabsTitleTextView)

        setSpannableText()
        setMusicList()
    }

    private fun fabStatusBarMargin(view: View) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val params = v.layoutParams as CoordinatorLayout.LayoutParams
            params.setMargins(
                params.leftMargin,
                15 + insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                params.rightMargin,
                params.bottomMargin
            )
            view.layoutParams = params
            WindowInsetsCompat.CONSUMED
        }
    }

    private fun statusBarMarginConstraint(view: View) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val params = v.layoutParams as ConstraintLayout.LayoutParams
            params.setMargins(
                params.leftMargin,
                16 + insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                params.rightMargin,
                params.bottomMargin
            )
            view.layoutParams = params
            WindowInsetsCompat.CONSUMED
        }
    }

    private fun statusBarMarginAppBar(view: View) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val params = v.layoutParams as AppBarLayout.LayoutParams
            params.setMargins(
                params.leftMargin,
                30 + insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                params.rightMargin,
                params.bottomMargin
            )
            view.layoutParams = params
            WindowInsetsCompat.CONSUMED
        }
    }

    private fun setSpannableText() {
        setSpannableFavorits()
        setSpannableLestening()
    }

    private fun setSpannableFavorits() {
        val spannableLiked = SpannableString(resources.getString(R.string.course_details_favorits))
        val icon = ImageSpan(applicationContext, R.drawable.ic_course_details_like)

        spannableLiked.setSpan(icon, 0, 1, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        viewBinding.courseDetailsFavoritsTextView.setText(
            spannableLiked,
            TextView.BufferType.SPANNABLE
        )
    }

    private fun setSpannableLestening() {
        val spannableLestening =
            SpannableString(resources.getString(R.string.course_details_lestening))
        val icon = ImageSpan(applicationContext, R.drawable.ic_course_details_headphones)

        spannableLestening.setSpan(icon, 0, 1, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        viewBinding.courseDetailsLesteningTextView.setText(
            spannableLestening,
            TextView.BufferType.SPANNABLE
        )
    }

    private fun setMusicList() {
        val recyclerView = viewBinding.courseDetailsMusicRecyclerView
        val linearLayoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        val customAdapter = CustomMusicAdapter(musicListDataSet, this)
        recyclerView.adapter = customAdapter
        recyclerView.addItemDecoration(MusicItemDecoration(20))
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onItemClicked() {
        val intent = Intent(applicationContext, MusicV2Activity::class.java)
        startActivity(intent)
    }
}