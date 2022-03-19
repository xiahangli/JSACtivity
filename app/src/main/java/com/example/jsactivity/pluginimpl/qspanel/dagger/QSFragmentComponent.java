package com.example.jsactivity.pluginimpl.qspanel.dagger;

import com.example.jsactivity.pluginimpl.qspanel.customizer.QSCustomizerController;

import dagger.Subcomponent;

/**
 * @author Henry
 * @Date 2022/3/20  3:59 AM
 * @Email 2427417167@qq.com
 */
@Subcomponent
public interface QSFragmentComponent {

    QSCustomizerController getQSCustomizerController();


    @Subcomponent.Factory
    interface Factory {

      QSFragmentComponent create();
    }
}
