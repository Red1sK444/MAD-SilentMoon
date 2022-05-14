package com.r3d1r4ph.silentmoon.onboarding

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.r3d1r4ph.silentmoon.R
import com.r3d1r4ph.silentmoon.databinding.ActivityOnboardingBinding
import com.r3d1r4ph.silentmoon.signin.SignInActivity
import com.r3d1r4ph.silentmoon.signup.SignUpActivity
import android.os.Build
import android.view.WindowInsets
import androidx.core.view.ViewCompat
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.constraintlayout.widget.Guideline
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.r3d1r4ph.silentmoon.playoption.PlayOptionActivity.Companion.setTopStatusBarMargin


class OnboardingActivity : AppCompatActivity() {

    private val viewBinding by viewBinding(ActivityOnboardingBinding::bind, R.id.rootLayout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        WindowCompat.setDecorFitsSystemWindows(window, false)

//        ViewCompat.setOnApplyWindowInsetsListener(viewBinding.guideline2) { view, insets ->
//            (view as Guideline).setGuidelineBegin(insets.getInsets(WindowInsetsCompat.Type.systemBars()).top)
//            WindowInsetsCompat.CONSUMED
//        }
        viewBinding.guideline2.setTopStatusBarMargin()

        setSpannableText()
        viewBinding.signUpButton.setOnClickListener {
            val intent = Intent(applicationContext, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setSpannableText() {
        setSpannableTitle()
        setSpannableInfo()
    }

    private fun setSpannableTitle() {
        val spannableTitle = SpannableString(resources.getString(R.string.app_name_for_spannable))
        val iconSpan = ImageSpan(applicationContext, R.drawable.ic_onboarding)

        spannableTitle.setSpan(
            iconSpan,
            7, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        viewBinding.onboardingTitleTextView.setText(spannableTitle, TextView.BufferType.SPANNABLE)
    }

    private fun setSpannableInfo() {
        val spannableLogIn =
            SpannableString(resources.getString(R.string.onboarding_already_have_an_account_log_in))

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(applicationContext, SignInActivity::class.java)
                startActivity(intent)
            }

            override fun updateDrawState(textPaint: TextPaint) {
                super.updateDrawState(textPaint)
                textPaint.isUnderlineText = false
                textPaint.color = ContextCompat.getColor(applicationContext, R.color.sky_blue)
            }
        }

        spannableLogIn.setSpan(
            clickableSpan,
            25,
            spannableLogIn.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableLogIn.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(applicationContext, R.color.sky_blue)),
            25,
            spannableLogIn.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        viewBinding.buttonDescriptionTextView.setText(
            spannableLogIn,
            TextView.BufferType.SPANNABLE
        )

        viewBinding.buttonDescriptionTextView.movementMethod = LinkMovementMethod.getInstance()
    }
}