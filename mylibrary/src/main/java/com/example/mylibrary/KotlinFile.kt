package com.example.mylibrary

import android.app.Activity
import android.util.Log
import android.widget.FrameLayout

/**
 * @author Henry
 *@Date 2022/3/17  1:53 AM
 *@Email 2427417167@qq.com
 */
class KotlinFile1 : Activity(), Contract {
    private val TAG = "KotlinFile"
    override fun callFromLib() {
        Log.d(TAG, "callFromLib: ")
    }

    companion object {
        private const val A = ""
    }

}