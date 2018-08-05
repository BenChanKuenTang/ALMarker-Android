package com.logcat.almarker.util

import android.support.annotation.AnimRes
import android.support.v4.app.FragmentManager
import com.logcat.almarker.R
import com.logcat.almarker.base.BaseFragment

data class FragmentAnimation(
        @AnimRes val enter: Int,
        @AnimRes val exit: Int,
        @AnimRes val popEnter: Int? = null,
        @AnimRes val popExit: Int? = null
)

class FragmentHelper {
    companion object Builder {
        private var fragmentManager: FragmentManager? = null
        private var fragment: BaseFragment? = null
        private var containerViewId: Int = R.id.mainContainer
        private var fragmentAnimation: FragmentAnimation? = null

        fun fragmentManager(fragmentManager: FragmentManager): Builder {
            this.fragmentManager = fragmentManager
            return this
        }

        fun fragment(fragment: BaseFragment): Builder {
            this.fragment = fragment
            return this
        }

        fun containerViewId(containerViewId: Int): Builder {
            this.containerViewId = containerViewId
            return this
        }

        fun transitionAnimation(fragmentAnimation: FragmentAnimation): Builder {
            this.fragmentAnimation = fragmentAnimation
            return this
        }

        fun replace(isAddToBackStack: Boolean = true): Builder {
            fragmentManager?.let { fragmentManager ->
                val transaction = fragmentManager.beginTransaction()
                transaction.apply {
                    if (isAddToBackStack) {
                        addToBackStack(fragment.toString())
                    }
                    fragmentAnimation?.apply {
                        if (popEnter == null || popExit == null) {
                            setCustomAnimations(enter, exit)
                        } else {
                            setCustomAnimations(enter, exit, popEnter, popExit)
                        }
                    }

                    replace(containerViewId, fragment, fragment.toString())
                    commit()
                }
            }

            return this
        }

        fun add(): Builder {
            fragmentManager?.let { fragmentManager ->
                val transaction = fragmentManager.beginTransaction()
                transaction.apply {
                    addToBackStack(fragment.toString())
                    fragmentAnimation?.apply {
                        if (popEnter == null || popExit == null) {
                            setCustomAnimations(enter, exit)
                        } else {
                            setCustomAnimations(enter, exit, popEnter, popExit)
                        }
                    }

                    add(containerViewId, fragment, fragment.toString())
                    commit()
                }
            }

            return this
        }
    }
}