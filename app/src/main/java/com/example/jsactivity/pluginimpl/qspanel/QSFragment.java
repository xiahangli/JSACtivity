package com.example.jsactivity.pluginimpl.qspanel;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.demo.plugin.QS;
import com.example.jsactivity.R;
import com.example.jsactivity.pluginimpl.qspanel.LifecycleFragment;
import com.example.jsactivity.pluginimpl.qspanel.dagger.QSFragmentComponent;

import javax.inject.Inject;

/**
 * @author Henry
 * @Date 2022/3/20  2:24 AM
 * @Email 2427417167@qq.com
 */
public class QSFragment extends LifecycleFragment implements QS {

    private QSFragmentComponent.Factory mFactory;

    // because we expose the QSFragmentComponent.Factory in ActivityComponent
    // so we can use the QSFragmentComponent.Factory
    // 不能去掉@Inject，FragmentCreator中请求了QSFragment
    @Inject
    public QSFragment(QSFragmentComponent.Factory mFactory) {
        this.mFactory = mFactory;
        Log.e("xia1", "QSFragment: factory " + mFactory);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setPanelView(HeightListener notificationPanelView) {

    }

    private static final String TAG = "QSFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // attachToRoot can't be true, because the FragmentMangerImpl has added the returned view into container
        // already.
        Log.e("xia1", "onCreateView: container " + container);
        return inflater.inflate(R.layout.main_fragment, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        QSFragmentComponent qsFragmentComponent = mFactory.create(this);
        Log.e("xia1", "onViewCreated: qsFragmentComponent " + qsFragmentComponent);
    }
}
