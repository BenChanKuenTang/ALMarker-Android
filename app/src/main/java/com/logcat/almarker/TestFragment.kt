package com.logcat.almarker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.logcat.almarker.base.BaseFragment
import com.logcat.almarker.network_module.FuelNetworkUtil
import com.logcat.almarker.network_module.FuelRequestModel
import com.logcat.almarker.network_module.RequestMethod
import kotlinx.android.synthetic.main.fragment_test.*

class TestFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        call()
        btnCall.setOnClickListener {
            call()
        }
    }

    private fun call() {
        FuelNetworkUtil().request<List<HolidayModel>>(
                FuelRequestModel(
                        RequestMethod.GET,
                        "holiday.php",
                        successCallback = { statusCode, response ->

                        },
                        failCallback = { error ->

                        }
                )
        )
    }
}