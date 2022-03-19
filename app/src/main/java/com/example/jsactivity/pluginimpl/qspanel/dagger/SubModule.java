package com.example.jsactivity.pluginimpl.qspanel.dagger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.jsactivity.Outer;
import com.example.jsactivity.SubModuleElement;
import com.example.jsactivity.pluginimpl.qspanel.dagger.FragmentCreator;

import java.lang.reflect.Method;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import dalvik.system.DexClassLoader;

/**
 * @author Henry
 * @Date 2022/3/17  2:58 AM
 * @Email 2427417167@qq.com
 */
@Module(subcomponents = FragmentCreator.class)
public class SubModule {



    @Provides
    public SubModuleElement providesSubModuleElement() {
        return new SubModuleElement();
    }

    private static final String TAG = "SubModule";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void doFunc(Context context) {
        Class<Outer> outerClass = Outer.class;
        Log.e(TAG, "doFunc: " + outerClass.getClass());
        Log.e(TAG, "doFunc: " + outerClass);

        try {
            Class<?> aClass = outerClass.getClassLoader().loadClass("com.example.jsactivity.Outer");
            Object o = aClass.newInstance();
            Log.e(TAG, "doFunc: " + o);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {

            e.printStackTrace();
            Log.e(TAG, "doFunc: ", e.getCause());
        }


        /// load class
        Intent it = new Intent("com.example.secondapp.action", null);
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(it, 0);//查询activity
        List<ResolveInfo> intendedServiceQueried = packageManager.queryIntentServices(it, 0);
        Log.e(TAG, "doFunc: queryIntentActivities size " + queryIntentActivities.size());

        for (ResolveInfo info :
                intendedServiceQueried) {
            ComponentName name = new ComponentName(info.serviceInfo.packageName,
                    info.serviceInfo.name);


        }




        if (queryIntentActivities.size() == 0) {
            return;
        }
        for (ResolveInfo info :
                queryIntentActivities) {
            Log.e(TAG, "doFunc: ");
            String dexPath = info.activityInfo.applicationInfo.sourceDir;//data/app/com.example.secondapp-zDO-FO2L8lPQtDSZdbvPXg==/base.apk
            String optimizedDirectory = context.getApplicationInfo().dataDir;///data/user/0/com.example.jsactivity
            String librarySearchPath = info.activityInfo.applicationInfo.nativeLibraryDir;///data/app/com.example.secondapp-zDO-FO2L8lPQtDSZdbvPXg==/lib/x86
            ClassLoader classloader = context.getClassLoader();
            DexClassLoader dcl = new DexClassLoader(dexPath, optimizedDirectory, librarySearchPath, classloader);
            Log.e(TAG, " context.getClass " + context.getClass());

            try {
                Class<?> clazz;



                if (info.activityInfo.applicationInfo.packageName.equals("com.example.thirdapp")) {
                    clazz = dcl.loadClass("com.example.thirdapp.ThirdActivity");
                } else if (info.activityInfo.applicationInfo.packageName.equals("com.example.secondapp")) {
                    clazz = dcl.loadClass("com.example.secondapp.SecondApp");
                } else {
                    clazz = null;
                }
                Object obj = clazz.newInstance();
                Method method = clazz.getMethod("doFunc", String.class);
                //成功调用方法
                method.invoke(obj, new StringBuffer("SubModule").toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }
}
