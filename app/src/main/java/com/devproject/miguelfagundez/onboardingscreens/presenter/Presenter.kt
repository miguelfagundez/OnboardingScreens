package com.devproject.miguelfagundez.onboardingscreens.presenter

import android.view.Display
import com.devproject.miguelfagundez.onboardingscreens.MVPInterface
import com.devproject.miguelfagundez.onboardingscreens.model.Model
import com.devproject.miguelfagundez.onboardingscreens.model.OnboardingData

/************************************
 * Class name: Presenter
 * Presenter class
 * @author Miguel Fagundez
 * @version 1.0
 * @since April 2020
 * ***********************************/
class Presenter(var view : MVPInterface.view) : MVPInterface.presenter{

    private var model : MVPInterface.model

    init {
        model = Model(this)
    }


    override fun getListData(): ArrayList<OnboardingData> {
        return model.ListDataRequested()
    }
}