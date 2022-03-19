package com.example.jsactivity.pluginimpl;

import com.example.jsactivity.pluginimpl.qspanel.dagger.QSFragmentComponent;
import com.example.jsactivity.pluginimpl.qspanel.dagger.SubModule;
import com.example.jsactivity.SubModuleElement;
import com.example.jsactivity.pluginimpl.qspanel.dagger.FragmentCreator;

import dagger.Component;

/**
 * @author Henry
 * @Date 2022/3/20  4:54 AM
 * @Email 2427417167@qq.com
 */
@Component(modules = SubModule.class)
public interface ActivityComponent {

    SubModuleElement getSubModuleElement();

    void inject(MainActivity1 activity1);

    FragmentCreator.Builder getFragmentCreator();

    /**
     * exported QSFragmentComponent.Factory
     * @return
     */
    QSFragmentComponent.Factory getQSFragmentComponent();
}
