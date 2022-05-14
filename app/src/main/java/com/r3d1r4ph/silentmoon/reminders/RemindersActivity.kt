package com.r3d1r4ph.silentmoon.reminders

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.r3d1r4ph.silentmoon.R
import com.r3d1r4ph.silentmoon.databinding.ActivityRemindersBinding
import com.r3d1r4ph.silentmoon.menu.MenuActivity
import com.r3d1r4ph.silentmoon.signup.SignUpActivity

class RemindersActivity : AppCompatActivity() {
    val viewBinding by viewBinding(ActivityRemindersBinding::bind, R.id.rootLayout)
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminders)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        username = intent.getStringExtra(SignUpActivity.USERNAMEKEY).toString()

        setButtonOnClickListeners()
    }

    private fun setButtonOnClickListeners() {
        viewBinding.dateSaveButton.setOnClickListener {
            navigateToHome()
        }
        viewBinding.noThanksButton.setOnClickListener {
            navigateToHome()
        }
    }

    private fun navigateToHome() {
        val intent = Intent(applicationContext, MenuActivity::class.java)
        intent.putExtra(SignUpActivity.USERNAMEKEY, username)
        startActivity(intent)
    }
}