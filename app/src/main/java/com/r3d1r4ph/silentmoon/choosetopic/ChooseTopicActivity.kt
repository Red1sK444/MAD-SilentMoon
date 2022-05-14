package com.r3d1r4ph.silentmoon.choosetopic

import android.content.Intent
import android.graphics.Typeface.BOLD
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.r3d1r4ph.silentmoon.R
import com.r3d1r4ph.silentmoon.databinding.ActivityChooseTopicBinding
import com.r3d1r4ph.silentmoon.reminders.RemindersActivity
import com.r3d1r4ph.silentmoon.signup.SignUpActivity
import com.r3d1r4ph.silentmoon.utility.OnItemClickListener

class ChooseTopicActivity : AppCompatActivity(), OnItemClickListener {

    private val viewBinding by viewBinding(ActivityChooseTopicBinding::bind, R.id.rootLayout)
    private lateinit var username: String
    private val recyclerItemsDataSet by lazy {
        arrayListOf(
            TopicRecyclerItem(
                R.drawable.img_recycler_item_1,
                resources.getString(R.string.recycler_view_item_1),
                ContextCompat.getColor(applicationContext, R.color.text_white)
            ),
            TopicRecyclerItem(
                R.drawable.img_recycler_item_2,
                resources.getString(R.string.recycler_view_item_2),
                ContextCompat.getColor(applicationContext, R.color.text_white)
            ),
            TopicRecyclerItem(
                R.drawable.img_recycler_item_4,
                resources.getString(R.string.recycler_view_item_4),
                ContextCompat.getColor(applicationContext, R.color.text_black)
            ),
            TopicRecyclerItem(
                R.drawable.img_recycler_item_3,
                resources.getString(R.string.recycler_view_item_3),
                ContextCompat.getColor(applicationContext, R.color.text_black)
            ),
            TopicRecyclerItem(
                R.drawable.img_recycler_item_5,
                resources.getString(R.string.recycler_view_item_5),
                ContextCompat.getColor(applicationContext, R.color.text_white)
            ),
            TopicRecyclerItem(
                R.drawable.img_recycler_item_6,
                resources.getString(R.string.recycler_view_item_6),
                ContextCompat.getColor(applicationContext, R.color.text_white)
            ),
            TopicRecyclerItem(
                R.drawable.img_recycler_item_8,
                resources.getString(R.string.recycler_view_item_6),
                ContextCompat.getColor(applicationContext, R.color.text_black)
            ),
            TopicRecyclerItem(
                R.drawable.img_recycler_item_7,
                resources.getString(R.string.recycler_view_item_6),
                ContextCompat.getColor(applicationContext, R.color.text_black)
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_topic)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        username = intent.getStringExtra(SignUpActivity.USERNAMEKEY).toString()

        setSpannableText()
        setTopicGrid()
    }

    override fun onItemClicked() {
        val intent = Intent(applicationContext, RemindersActivity::class.java)
        intent.putExtra(SignUpActivity.USERNAMEKEY, username)
        startActivity(intent)
    }

    private fun setSpannableText() {
        val spannableHeader = SpannableString(resources.getString(R.string.choose_topic_header))
        spannableHeader.setSpan(StyleSpan(BOLD), 0, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        viewBinding.topicHeaderTextView.setText(spannableHeader, TextView.BufferType.SPANNABLE)
    }

    private fun setTopicGrid() {
        val recyclerView = viewBinding.topicGridRecyclerView
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recyclerView.layoutManager = staggeredGridLayoutManager
        val customAdapter = CustomTopicAdapter(recyclerItemsDataSet, this)
        recyclerView.adapter = customAdapter
        recyclerView.addItemDecoration(TopicsItemDecoration(10))
    }
}