package com.r3d1r4ph.silentmoon.signup

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.r3d1r4ph.silentmoon.R
import com.r3d1r4ph.silentmoon.databinding.ActivitySignUpBinding
import com.r3d1r4ph.silentmoon.welcome.WelcomeActivity

class SignUpActivity : AppCompatActivity() {

    companion object {
        const val USERNAMEKEY = "username"
    }

    private val viewBinding by viewBinding(ActivitySignUpBinding::bind, R.id.rootLayout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        setSpannableText()
        viewBinding.getStartedButton.setOnClickListener {
            val intent = Intent(applicationContext, WelcomeActivity::class.java)
            if (viewBinding.usernameEditText.text.isNotBlank()) {
                intent.putExtra(USERNAMEKEY, viewBinding.usernameEditText.text.toString())
            }
            startActivity(intent)
        }
    }

    private fun setSpannableText() {
        val spannablePrivacyPolicy =
            SpannableString(resources.getString(R.string.sign_up_the_privacy_policy))

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(
                    applicationContext,
                    resources.getString(R.string.sign_up_privacy_policy_text),
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun updateDrawState(textPaint: TextPaint) {
                super.updateDrawState(textPaint)
                textPaint.isUnderlineText = false
                textPaint.color = ContextCompat.getColor(applicationContext, R.color.sky_blue)
            }
        }

        spannablePrivacyPolicy.setSpan(
            clickableSpan,
            16,
            spannablePrivacyPolicy.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        viewBinding.privacyPolicyTextView.setText(
            spannablePrivacyPolicy,
            TextView.BufferType.SPANNABLE
        )

        viewBinding.privacyPolicyTextView.movementMethod = LinkMovementMethod.getInstance()
    }
}