package com.logcat.almarker.splash

import android.os.Bundle
import com.logcat.almarker.HolidayModel
import com.logcat.almarker.R
import com.logcat.almarker.base.BaseActivity
import com.logcat.almarker.network_module.FuelNetworkUtil
import com.logcat.almarker.network_module.FuelRequestModel
import com.logcat.almarker.network_module.RequestMethod

class SplashScreenActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getHolidayList()
    }

    private fun getHolidayList() {
        FuelNetworkUtil().request<List<HolidayModel>>(
                FuelRequestModel(
                        RequestMethod.GET,
                        "holiday.php",
                        successCallback = { statusCode, response ->
                            //TODO: save holidays into sqlite
                            SplashScreenRouter(this).routeToSignInActivity()
                        },
                        failCallback = { error ->
                            //TODO: add handling
                        }
                )
        )
    }

    override fun getLayoutId(): Int = R.layout.activity_splash
}