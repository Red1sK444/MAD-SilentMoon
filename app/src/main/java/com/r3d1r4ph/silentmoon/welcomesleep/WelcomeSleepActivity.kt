package com.r3d1r4ph.silentmoon.welcomesleep

import android.app.Activity
import android.app.PendingIntent
import android.content.IntentSender
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.r3d1r4ph.silentmoon.R
import com.r3d1r4ph.silentmoon.databinding.ActivityWelcomeSleepBinding

class WelcomeSleepActivity : AppCompatActivity() {

    private val viewBinding by viewBinding(ActivityWelcomeSleepBinding::bind, R.id.rootLayout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_sleep)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.decorView.systemUiVisibility = 0

        viewBinding.welcomeSleepGetStartedButton.setOnClickListener {
            finish()
        }
    }
}