package com.logcat.almarker.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.logcat.almarker.R

class FragmentUtil {
    companion object {
        fun replace(fragmentManager: FragmentManager, fragment: Fragment, layoutRes: Int = R.id.mainContainer, isAddToBackStack: Boolean = false) {
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(layoutRes, fragment)
            if (isAddToBackStack) {
                transaction.addToBackStack(fragment.javaClass.simpleName)
            }
            transaction.commit()
        }

        fun add() {

        }
    }
}