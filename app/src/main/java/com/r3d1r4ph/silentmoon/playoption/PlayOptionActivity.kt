package com.r3d1r4ph.silentmoon.playoption

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ImageSpan
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginTop
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.appbar.AppBarLayout
import com.r3d1r4ph.silentmoon.R
import com.r3d1r4ph.silentmoon.choosetopic.TopicsItemDecoration
import com.r3d1r4ph.silentmoon.databinding.ActivityPlayOptionBinding
import com.r3d1r4ph.silentmoon.fragments.home.CustomRecommendationAdapter
import com.r3d1r4ph.silentmoon.fragments.home.RecommendationRecyclerItem
import com.r3d1r4ph.silentmoon.music.MusicActivity

class PlayOptionActivity : AppCompatActivity() {
    private val viewBinding by viewBinding(ActivityPlayOptionBinding::bind, R.id.rootLayout)
    private val relatedMusicDataSet by lazy {
        arrayListOf(
            RecommendationRecyclerItem(
                R.drawable.img_sleep_moon_clouds,
                resources.getString(R.string.sleep_moon_clouds),
                resources.getString(R.string.sleep_music_type),
                resources.getString(R.string.sleep_music_duration),
                ContextCompat.getColor(applicationContext, R.color.night_white),
                ContextCompat.getColor(applicationContext, R.color.night_grey),
                R.drawable.img_night_media_dot
            ),
            RecommendationRecyclerItem(
                R.drawable.img_sleep_sweet_sleep,
                resources.getString(R.string.sleep_sweet_sleep),
                resources.getString(R.string.sleep_music_type),
                resources.getString(R.string.sleep_music_duration),
                ContextCompat.getColor(applicationContext, R.color.night_white),
                ContextCompat.getColor(applicationContext, R.color.night_grey),
                R.drawable.img_night_media_dot
            ),
            RecommendationRecyclerItem(
                R.drawable.img_sleep_night_island,
                resources.getString(R.string.sleep_night_island),
                resources.getString(R.string.sleep_music_type),
                resources.getString(R.string.sleep_music_duration),
                ContextCompat.getColor(applicationContext, R.color.night_white),
                ContextCompat.getColor(applicationContext, R.color.night_grey),
                R.drawable.img_night_media_dot
            ),
            RecommendationRecyclerItem(
                R.drawable.img_sleep_good_night,
                resources.getString(R.string.sleep_good_night),
                resources.getString(R.string.sleep_music_type),
                resources.getString(R.string.sleep_music_duration),
                ContextCompat.getColor(applicationContext, R.color.night_white),
                ContextCompat.getColor(applicationContext, R.color.night_grey),
                R.drawable.img_night_media_dot
            ),
            RecommendationRecyclerItem(
                R.drawable.img_sleep_moon_clouds,
                resources.getString(R.string.sleep_moon_clouds),
                resources.getString(R.string.sleep_music_type),
                resources.getString(R.string.sleep_music_duration),
                ContextCompat.getColor(applicationContext, R.color.night_white),
                ContextCompat.getColor(applicationContext, R.color.night_grey),
                R.drawable.img_night_media_dot
            ),
            RecommendationRecyclerItem(
                R.drawable.img_sleep_sweet_sleep,
                resources.getString(R.string.sleep_sweet_sleep),
                resources.getString(R.string.sleep_music_type),
                resources.getString(R.string.sleep_music_duration),
                ContextCompat.getColor(applicationContext, R.color.night_white),
                ContextCompat.getColor(applicationContext, R.color.night_grey),
                R.drawable.img_night_media_dot
            ),
            RecommendationRecyclerItem(
                R.drawable.img_sleep_sweet_sleep,
                resources.getString(R.string.sleep_sweet_sleep),
                resources.getString(R.string.sleep_music_type),
                resources.getString(R.string.sleep_music_duration),
                ContextCompat.getColor(applicationContext, R.color.night_white),
                ContextCompat.getColor(applicationContext, R.color.night_grey),
                R.drawable.img_night_media_dot
            ),
            RecommendationRecyclerItem(
                R.drawable.img_sleep_night_island,
                resources.getString(R.string.sleep_night_island),
                resources.getString(R.string.sleep_music_type),
                resources.getString(R.string.sleep_music_duration),
                ContextCompat.getColor(applicationContext, R.color.night_white),
                ContextCompat.getColor(applicationContext, R.color.night_grey),
                R.drawable.img_night_media_dot
            ), RecommendationRecyclerItem(
                R.drawable.img_sleep_good_night,
                resources.getString(R.string.sleep_good_night),
                resources.getString(R.string.sleep_music_type),
                resources.getString(R.string.sleep_music_duration),
                ContextCompat.getColor(applicationContext, R.color.night_white),
                ContextCompat.getColor(applicationContext, R.color.night_grey),
                R.drawable.img_night_media_dot
            ),
            RecommendationRecyclerItem(
                R.drawable.img_sleep_moon_clouds,
                resources.getString(R.string.sleep_moon_clouds),
                resources.getString(R.string.sleep_music_type),
                resources.getString(R.string.sleep_music_duration),
                ContextCompat.getColor(applicationContext, R.color.night_white),
                ContextCompat.getColor(applicationContext, R.color.night_grey),
                R.drawable.img_night_media_dot
            ),
            RecommendationRecyclerItem(
                R.drawable.img_sleep_sweet_sleep,
                resources.getString(R.string.sleep_sweet_sleep),
                resources.getString(R.string.sleep_music_type),
                resources.getString(R.string.sleep_music_duration),
                ContextCompat.getColor(applicationContext, R.color.night_white),
                ContextCompat.getColor(applicationContext, R.color.night_grey),
                R.drawable.img_night_media_dot
            ),
            RecommendationRecyclerItem(
                R.drawable.img_sleep_sweet_sleep,
                resources.getString(R.string.sleep_sweet_sleep),
                resources.getString(R.string.sleep_music_type),
                resources.getString(R.string.sleep_music_duration),
                ContextCompat.getColor(applicationContext, R.color.night_white),
                ContextCompat.getColor(applicationContext, R.color.night_grey),
                R.drawable.img_night_media_dot
            )
        )
    }

    companion object {
        fun View.setTopStatusBarMargin() {
            val topMargin = this.marginTop
            ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
                val params = v.layoutParams as ViewGroup.MarginLayoutParams
                params.setMargins(
                    params.leftMargin,
                    topMargin + insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                    params.rightMargin,
                    params.bottomMargin
                )
                this.layoutParams = params
                WindowInsetsCompat.CONSUMED
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_option)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        window.decorView.systemUiVisibility = 0

        //fabStatusBarMargin(viewBinding.playOptionDownloadFAB)
        //fabStatusBarMargin(viewBinding.playOptionLikeFAB)

        statusBarMarginConstraint(viewBinding.playOptionTitleTextView)
        statusBarMarginAppBar(viewBinding.playOptionRelated)

        setSpannableText()
        setRelatedMusic()

        viewBinding.playOptionButton.setOnClickListener {
            val intent = Intent(applicationContext, MusicActivity::class.java)
            startActivity(intent)
        }

        viewBinding.playOptionDownloadFAB.setTopStatusBarMargin()
    }

    private fun fabStatusBarMargin(view: View) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val params = v.layoutParams as ViewGroup.MarginLayoutParams
            view.layoutParams = ViewGroup.MarginLayoutParams(0,0)
            params.setMargins(
                params.leftMargin,
                view.marginTop + insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
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
                30 + insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
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
                25 + insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
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
        val spannableLiked = SpannableString(resources.getString(R.string.play_option_favorits))
        val icon = ImageSpan(applicationContext, R.drawable.ic_play_option_like)

        spannableLiked.setSpan(icon, 0, 1, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        viewBinding.playOptionFavoritsTextView.setText(
            spannableLiked,
            TextView.BufferType.SPANNABLE
        )
    }

    private fun setSpannableLestening() {
        val spannableLestening =
            SpannableString(resources.getString(R.string.play_option_lestening))
        val icon = ImageSpan(applicationContext, R.drawable.ic_play_option_headphones)

        spannableLestening.setSpan(icon, 0, 1, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        viewBinding.playOptionLesteningTextView.setText(
            spannableLestening,
            TextView.BufferType.SPANNABLE
        )
    }

    private fun setRelatedMusic() {
        val recyclerView = viewBinding.relatedMusicRecyclerView
        val gridLayoutManager =
            GridLayoutManager(applicationContext, 2, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = gridLayoutManager
        val customAdapter = CustomRecommendationAdapter(relatedMusicDataSet)
        recyclerView.adapter = customAdapter
        recyclerView.addItemDecoration(TopicsItemDecoration(10))
    }
}