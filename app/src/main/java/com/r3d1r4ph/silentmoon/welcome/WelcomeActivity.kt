package com.r3d1r4ph.silentmoon.welcome

import android.content.Intent
import android.graphics.Typeface.BOLD
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import android.text.style.StyleSpan
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.r3d1r4ph.silentmoon.R
import com.r3d1r4ph.silentmoon.choosetopic.ChooseTopicActivity
import com.r3d1r4ph.silentmoon.databinding.ActivityWelcomeBinding
import com.r3d1r4ph.silentmoon.signup.SignUpActivity

class WelcomeActivity : AppCompatActivity() {

    private val viewBinding by viewBinding(ActivityWelcomeBinding::bind, R.id.rootLayout)
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        //WindowCompat.setDecorFitsSystemWindows(window, false)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        username = if (intent.getStringExtra(SignUpActivity.USERNAMEKEY) != null) {
            intent.getStringExtra(SignUpActivity.USERNAMEKEY).toString()
        } else {
            resources.getString(R.string.default_username)
        }

        setSpannableText()
        viewBinding.welcomeGetStartedButton.setOnClickListener {
            val intent = Intent(applicationContext, ChooseTopicActivity::class.java)
            intent.putExtra(SignUpActivity.USERNAMEKEY, username)
            startActivity(intent)
        }
    }

    private fun setSpannableText() {
        setSpannableTitle()
        setSpannableGreeting()
    }

    private fun setSpannableTitle() {
        val spannableTitle = SpannableString(resources.getString(R.string.app_name_for_spannable))
        val iconSpan = ImageSpan(applicationContext, R.drawable.ic_welcome)

        spannableTitle.setSpan(
            iconSpan,
            7, 10, Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )

        viewBinding.welcomeTitleTextView.setText(spannableTitle, TextView.BufferType.SPANNABLE)
    }

    private fun setSpannableGreeting() {
        var spannableGreeting =
            SpannableString(resources.getString(R.string.welcome_hi_to_silent_moon))
        val spannableStringBuilder = SpannableStringBuilder()

        spannableStringBuilder.append(spannableGreeting, 0, spannableGreeting.length)
        spannableStringBuilder.replace(3, 8, username)

        spannableGreeting = SpannableString.valueOf(spannableStringBuilder)
        spannableGreeting.setSpan(StyleSpan(BOLD), 0, 13+username.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        viewBinding.hiWelcomeTextView.setText(spannableGreeting, TextView.BufferType.SPANNABLE)
    }
}