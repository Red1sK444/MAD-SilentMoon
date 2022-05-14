package com.r3d1r4ph.silentmoon.menu

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import by.kirich1409.viewbindingdelegate.viewBinding
import com.r3d1r4ph.silentmoon.R
import com.r3d1r4ph.silentmoon.databinding.ActivityMenuBinding
import com.r3d1r4ph.silentmoon.fragments.home.HomeFragment
import com.r3d1r4ph.silentmoon.fragments.meditatev2.MeditateV2Fragment
import com.r3d1r4ph.silentmoon.fragments.sleep.SleepFragment
import com.r3d1r4ph.silentmoon.fragments.sleepmusic.SleepMusicFragment
import com.r3d1r4ph.silentmoon.music.MusicActivity
import com.r3d1r4ph.silentmoon.musicv2.MusicV2Activity
import com.r3d1r4ph.silentmoon.signup.SignUpActivity
import com.r3d1r4ph.silentmoon.utility.FragmentNavigator
import com.r3d1r4ph.silentmoon.welcomesleep.WelcomeSleepActivity

class MenuActivity : AppCompatActivity(), FragmentNavigator {
    private val viewBinding by viewBinding(ActivityMenuBinding::bind, R.id.rootLayout)
    private lateinit var currentTAG: String
    private var selectedItem: MenuItem? = null
    private var goToSleep = false
    private var isNight = false
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        username = intent.getStringExtra(SignUpActivity.USERNAMEKEY).toString()

        configureBottomNavigationView()
    }

    private fun configureBottomNavigationView() {
        with(viewBinding.bottomNavigationView) {
            itemIconTintList = null
            menu.findItem(R.id.userItem).title = username
            setOnItemSelectedListener { item ->
                performBottomNavigation(item)
            }
            selectedItemId = R.id.homeItem
            setOnItemReselectedListener { item ->
                if (item.itemId == R.id.sleepItem && currentTAG == SleepMusicFragment.TAG) {
                    navigateToFragment(SleepFragment.TAG, SleepFragment.newInstance(), false)
                }
            }
        }
    }

    private fun performBottomNavigation(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.homeItem -> {
                val bundle = Bundle()
                bundle.putString(SignUpActivity.USERNAMEKEY, username)
                navigateToFragment(HomeFragment.TAG, HomeFragment.newInstance(), bundle = bundle)
                selectedItem = item
                setLightBottomNavigation()
            }
            R.id.sleepItem -> {
                if (currentTAG != SleepFragment.TAG && currentTAG != SleepMusicFragment.TAG) {
                    val intent = Intent(this, WelcomeSleepActivity::class.java)
                    startActivity(intent)
                    selectedItem = item
                    goToSleep = true
                }
            }
            R.id.meditateItem -> {
                navigateToFragment(
                    MeditateV2Fragment.TAG,
                    MeditateV2Fragment.newInstance()
                )
                selectedItem = item
                setLightBottomNavigation()
            }
            R.id.musicItem -> {

                val intent = when (isNight) {
                    true -> Intent(this, MusicActivity::class.java)
                    false -> Intent(this, MusicV2Activity::class.java)
                }
                startActivity(intent)
            }
            R.id.userItem -> {
            }
        }
        return true
    }

    private fun setNightBottomNavigation() {
        window.decorView.systemUiVisibility = 0

        viewBinding.bottomNavigationView.itemTextColor = ContextCompat.getColorStateList(
            applicationContext,
            R.color.bottom_nav_night_item_text_selector
        )
        viewBinding.bottomNavigationView.setBackgroundColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.night_black
            )
        )
        isNight = true
    }

    private fun setLightBottomNavigation() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        viewBinding.bottomNavigationView.itemTextColor = ContextCompat.getColorStateList(
            applicationContext,
            R.color.bottom_nav_light_item_text_selector
        )
        viewBinding.bottomNavigationView.setBackgroundColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.white
            )
        )
        isNight = false
    }

    override fun onResume() {
        super.onResume()
        if (goToSleep) {
            navigateToFragment(
                SleepFragment.TAG,
                SleepFragment.newInstance()
            )
            setNightBottomNavigation()
            goToSleep = false
        } else {
            selectedItem?.let {
                if (it.itemId != viewBinding.bottomNavigationView.selectedItemId) {
                    viewBinding.bottomNavigationView.selectedItemId = it.itemId
                }
            }
        }
    }

    override fun navigateToFragment(
        tag: String,
        fragment: Fragment,
        addToBackStack: Boolean,
        bundle: Bundle?
    ) {
        supportFragmentManager.commit {
            replace(R.id.fragmentContainer, fragment, tag)
            if (addToBackStack) {
                addToBackStack(tag)
            }
            if (bundle != null) {
                fragment.arguments = bundle
            }
        }
        currentTAG = tag
    }
}