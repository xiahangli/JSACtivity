//package com.example.jsactivity.pluginimpl;
//
//import android.content.res.Configuration;
//import android.os.Handler;
//import android.util.ArrayMap;
//import android.util.Log;
//import android.view.View;
//
//import androidx.fragment.app.Fragment;
//
//import com.example.jsactivity.pluginimpl.qspanel.QSFragment;
//import com.example.jsactivity.pluginimpl.qspanel.dagger.FragmentCreator;
//
//import java.lang.reflect.Method;
//import java.lang.reflect.Modifier;
//
//import dagger.Subcomponent;
//
//public class FragmentService {
//
//    private static final String TAG = "FragmentService";
//
//    private final ArrayMap<View, FragmentHostState> mHosts = new ArrayMap<>();
//    /**
//     * A map with the means to create fragments via Dagger injection.
//     *
//     * key: the fragment class name.
//     * value: see {@link FragmentInstantiationInfo}.
//     */
//    private final ArrayMap<String, FragmentInstantiationInfo> mInjectionMap = new ArrayMap<>();
//    private final Handler mHandler = new Handler();
//
////    private ConfigurationController.ConfigurationListener mConfigurationListener =
////            new ConfigurationController.ConfigurationListener() {
////                @Override
////                public void onConfigChanged(Configuration newConfig) {
////                    for (FragmentHostState state : mHosts.values()) {
////                        state.sendConfigurationChange(newConfig);
////                    }
////                }
////            };
//
//    public FragmentService(
//            FragmentCreator.Factory fragmentCreatorFactory) {
//        addFragmentInstantiationProvider(fragmentCreatorFactory.build());
//    }
//
//    ArrayMap<String, FragmentInstantiationInfo> getInjectionMap() {
//        return mInjectionMap;
//    }
//
//    /**
//     * Adds a new Dagger component object that provides method(s) to create fragments via injection.
//     */
//    public void addFragmentInstantiationProvider(Object daggerComponent) {
//        for (Method method : daggerComponent.getClass().getDeclaredMethods()) {
//            if (Fragment.class.isAssignableFrom(method.getReturnType())
//                    && (method.getModifiers() & Modifier.PUBLIC) != 0) {
//                String fragmentName = method.getReturnType().getName();
//                if (mInjectionMap.containsKey(fragmentName)) {
//                    Log.w(TAG, "Fragment " + fragmentName + " is already provided by different"
//                            + " Dagger component; Not adding method");
//                    continue;
//                }
//                mInjectionMap.put(
//                        fragmentName, new FragmentInstantiationInfo(method, daggerComponent));
//            }
//        }
//    }
//
//    public FragmentHostManager getFragmentHostManager(View view) {
//        View root = view.getRootView();
//        FragmentHostState state = mHosts.get(root);
//        if (state == null) {
//            state = new FragmentHostState(root);
//            mHosts.put(root, state);
//        }
//        return state.getFragmentHostManager();
//    }
//
//    public void removeAndDestroy(View view) {
//        final FragmentHostState state = mHosts.remove(view.getRootView());
//        if (state != null) {
//            state.mFragmentHostManager.destroy();
//        }
//    }
//
//    public void destroyAll() {
//        for (FragmentHostState state : mHosts.values()) {
//            state.mFragmentHostManager.destroy();
//        }
//    }
//
//    /**
//     * The subcomponent of dagger that holds all fragments that need injection.
//     */
////    @Subcomponent
////    public interface FragmentCreator {
////        /** Factory for creating a FragmentCreator. */
////        @Subcomponent.Factory
////        interface Factory {
////            FragmentCreator build();
////        }
////        /**
////         * Inject a QSFragment.
////         */
////        QSFragment createQSFragment();
////    }
//
//    private class FragmentHostState {
//        private final View mView;
//
//        private FragmentHostManager mFragmentHostManager;
//
//        public FragmentHostState(View view) {
//            mView = view;
//            mFragmentHostManager = new FragmentHostManager(FragmentService.this, mView);
//        }
//
//        public void sendConfigurationChange(Configuration newConfig) {
//            mHandler.post(() -> handleSendConfigurationChange(newConfig));
//        }
//
//        public FragmentHostManager getFragmentHostManager() {
//            return mFragmentHostManager;
//        }
//
//        private void handleSendConfigurationChange(Configuration newConfig) {
//            mFragmentHostManager.onConfigurationChanged(newConfig);
//        }
//    }
//
//    /** An object containing the information needed to instantiate a fragment. */
//    static class FragmentInstantiationInfo {
//        /** The method that returns a newly-created fragment of the given class. */
//        final Method mMethod;
//        /** The Dagger component that the method should be invoked on. */
//        final Object mDaggerComponent;
//        FragmentInstantiationInfo(Method method, Object daggerComponent) {
//            this.mMethod = method;
//            this.mDaggerComponent = daggerComponent;
//        }
//    }
//}
