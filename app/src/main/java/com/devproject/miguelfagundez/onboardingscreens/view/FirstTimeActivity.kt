package com.devproject.miguelfagundez.onboardingscreens.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.view.isVisible
import com.devproject.miguelfagundez.onboardingscreens.MVPInterface
import com.devproject.miguelfagundez.onboardingscreens.R
import com.devproject.miguelfagundez.onboardingscreens.Utils
import com.devproject.miguelfagundez.onboardingscreens.adapter.FirstTimePagerAdapter
import com.devproject.miguelfagundez.onboardingscreens.model.OnboardingData
import com.devproject.miguelfagundez.onboardingscreens.presenter.Presenter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_first_time.*

/*************************************************
 * Activity: FirstTimeActivity
 * I handle initial variables/components
 * I show important features of app
 *   1: First time -> Open Onboarding Screens
 * ************************************************/
class FirstTimeActivity : AppCompatActivity(), MVPInterface.view {

    // Controling navegation between OnBoarding screens
    private var position : Int = 0
    // ViewPager Adapter
    private lateinit var adapter : FirstTimePagerAdapter
    // Animation
    private lateinit var btnAnimation : Animation
    // Presenter layer
    private lateinit var presenter : MVPInterface.presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_time)

        // Presenter initialization
        presenter = Presenter(this)

        // Set up Views
        setupViews()
        // Set up the view pager
        setupViewPager()
        // Set up view visibility
        setupViewVisibility()
        // setup buttons listeners
        setupListeners()

    }

    private fun setupViews() {
        btnAnimation = AnimationUtils.loadAnimation(this, R.anim.btn_animation)
    }

    private fun setupViewVisibility() {
        // Button Back visibility
        btnBack.isVisible = position != 0

        // Button next and Go to Register visibility
        if (position == Utils.NUM_ONBOARDING_SCREENS-1) {
            btnNext.visibility = View.INVISIBLE
            btnGoToRegister.visibility = View.VISIBLE
            btnGoToRegister.animation = btnAnimation
        }else{
            btnNext.visibility = View.VISIBLE
            btnGoToRegister.visibility = View.INVISIBLE
        }
    }

    private fun setupOnboardingData() : ArrayList<OnboardingData> {
        //*********************************************
        // OnBoarding Data
        // Connecting with model throughout presenter
        //*********************************************
        return presenter.getListData()
    }

    private fun setupViewPager() {
        // Init adapter
        adapter = FirstTimePagerAdapter(this, setupOnboardingData())
        vpIntro.adapter = adapter
        // Set up TabLayout with this viewpager
        tabPagerIndicator.setupWithViewPager(vpIntro)
    }

    private fun setupListeners() {

        // NEXT btn
        btnNext.setOnClickListener {
            position = vpIntro.currentItem
            if (position < Utils.NUM_ONBOARDING_SCREENS){
                position += 1
                vpIntro.setCurrentItem(position)
                setupViewVisibility()
            }
        }
        // BACK btn
        btnBack.setOnClickListener {
            position = vpIntro.currentItem
            if (position > 0){
                position -= 1
                vpIntro.setCurrentItem(position)
                setupViewVisibility()
            }
        }
        // Tab View Pager
        tabPagerIndicator.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                position = p0?.position?:0
                if (position == Utils.NUM_ONBOARDING_SCREENS - 1) setupViewVisibility()
                if (position >= 0) setupViewVisibility()
            }

        })
        //*******************************
        // Go to Main Activity
        //*******************************
        btnGoToRegister.setOnClickListener {
            val intent = Intent(this@FirstTimeActivity, MainActivity::class.java )
            startActivity(intent)
        }
    }

    //***********************************
    // Interface implementation
    // **********************************
    override fun showMessage(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT)
    }

}
