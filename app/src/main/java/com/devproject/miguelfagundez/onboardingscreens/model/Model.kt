package com.devproject.miguelfagundez.onboardingscreens.model


import com.devproject.miguelfagundez.onboardingscreens.MVPInterface
import com.devproject.miguelfagundez.onboardingscreens.R

/************************************
 * Class name: Model - OnBoarding example
 * Model class
 * @author Miguel Fagundez
 * @version 1.0
 * @since April 2020
 * ***********************************/
class Model(var presenter : MVPInterface.presenter):MVPInterface.model {


    override fun ListDataRequested(): ArrayList<OnboardingData> {
        return takeListData()
    }


    override fun takeListData(): ArrayList<OnboardingData> {
        var list = ArrayList<OnboardingData>()
        list.add(
            OnboardingData(
                "Secure your notes",
                "Only one time register. Protect your notes with an username and password.",
                R.drawable.data_protection_mobile_min
            )
        )
        list.add(
            OnboardingData(
                "Notes with picture",
                "Save your notes with a picture. From Gallery or Camera.",
                R.drawable.take_picture_min
            )
        )
        list.add(
            OnboardingData(
                "Save or share notes",
                "Send your notes to the internal phone memory or share with friends.",
                R.drawable.phone_memory_min
            )
        )
        return list
    }
}