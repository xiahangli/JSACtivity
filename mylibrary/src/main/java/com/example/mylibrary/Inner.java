package com.example.mylibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.util.function.BiConsumer;

/**
 * @author Henry
 * @Date 2022/3/17  1:14 AM
 * @Email 2427417167@qq.com
 */
public class Inner {
    Contract mContract;
    public void setListener(Contract contract) {
        mContract = contract;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setLambda(BiConsumer<String, Bitmap> convertBitmap) {
        convertBitmap.accept("a", Bitmap.createBitmap(9, 9, Bitmap.Config.ALPHA_8));
    }

    public void callOuter() {
        mContract.callFromLib();
    }


}
