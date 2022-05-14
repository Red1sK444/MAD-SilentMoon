package com.r3d1r4ph.silentmoon.signin

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
import com.r3d1r4ph.silentmoon.databinding.ActivitySignInBinding
import com.r3d1r4ph.silentmoon.signup.SignUpActivity
import com.r3d1r4ph.silentmoon.welcome.WelcomeActivity


class SignInActivity : AppCompatActivity() {

    private val viewBinding by viewBinding(ActivitySignInBinding::bind, R.id.rootLayout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        setSpannableText()
        viewBinding.logInButton.setOnClickListener {
            val intent = Intent(applicationContext, WelcomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setSpannableText() {
        val spannableSignUp =
            SpannableString(resources.getString(R.string.sign_in_already_have_an_account_sign_up))

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(applicationContext, SignUpActivity::class.java)
                startActivity(intent)
            }

            override fun updateDrawState(textPaint: TextPaint) {
                super.updateDrawState(textPaint)
                textPaint.isUnderlineText = false
                textPaint.color = ContextCompat.getColor(applicationContext, R.color.sky_blue)
            }
        }

        spannableSignUp.setSpan(
            clickableSpan,
            25,
            spannableSignUp.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        viewBinding.alreadyHaveAccountTextView.setText(
            spannableSignUp,
            TextView.BufferType.SPANNABLE
        )

        viewBinding.alreadyHaveAccountTextView.movementMethod = LinkMovementMethod.getInstance()
    }
}