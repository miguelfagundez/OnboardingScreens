package com.devproject.miguelfagundez.onboardingscreens

import com.devproject.miguelfagundez.onboardingscreens.model.OnboardingData

/********************************************
 * Interface - MVP
 * This is the interface that needs to be
 * implemented in order to have MVP architecture
 * @author: Miguel Fagundez
 * @date: April 06th, 2020
 * @version: 1.0
 * *******************************************/
interface MVPInterface {

    interface view{
        fun showMessage(message:String)
    }
    interface presenter{
        fun getListData():ArrayList<OnboardingData>
    }
    interface model{
        fun ListDataRequested() : ArrayList<OnboardingData>
        fun takeListData():ArrayList<OnboardingData>
    }
}