package com.example.jsactivity.pluginimpl.qspanel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.LifecycleRegistryOwner;

/**
 * @author Henry
 * @Date 2022/3/20  3:42 AM
 * @Email 2427417167@qq.com
 */
@Deprecated
public class LifecycleFragment extends Fragment implements LifecycleRegistryOwner {

    private LifecycleRegistry mLifecycle = new LifecycleRegistry(this);

    @NonNull
    @Override
    public LifecycleRegistry getLifecycle() {
        return mLifecycle;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
    }

    @Override
    public void onStart() {
        super.onStart();
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START);
    }

    @Override
    public void onStop() {
        super.onStop();
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
    }

    @Override
    public void onResume() {
        super.onResume();
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
    }


    @Override
    public void onPause() {
        super.onPause();
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);



    }
}
