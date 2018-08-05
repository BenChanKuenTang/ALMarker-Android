package com.logcat.almarker.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.logcat.almarker.HolidayModel
import com.logcat.almarker.R
import com.logcat.almarker.base.BaseFragment
import com.logcat.almarker.network_module.FuelNetworkUtil
import com.logcat.almarker.network_module.FuelRequestModel
import com.logcat.almarker.network_module.RequestMethod
import com.logcat.ui_module.action_bar.ActionBar
import kotlinx.android.synthetic.main.fragment_test.*

class SignInFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSignIn.setOnClickListener {
            //TODO: sign in
        }
    }

    override fun getActionBarContainer(): ViewGroup? = null

    override fun getActionBarBuilder(): ActionBar.Builder? = null
}