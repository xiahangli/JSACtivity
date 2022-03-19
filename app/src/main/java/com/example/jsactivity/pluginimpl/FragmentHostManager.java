package com.example.jsactivity.pluginimpl;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentHostCallback;

import com.demo.plugin.Plugin;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class FragmentHostManager {

    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final Context mContext;
    private final HashMap<String, ArrayList<FragmentListener>> mListeners = new HashMap<>();
    private final View mRootView;
    private final InterestingConfigChanges mConfigChanges = new InterestingConfigChanges(
            ActivityInfo.CONFIG_FONT_SCALE | ActivityInfo.CONFIG_LOCALE
                | ActivityInfo.CONFIG_SCREEN_LAYOUT);
    private final FragmentService mManager;
    private final ExtensionFragmentManager mPlugins = new ExtensionFragmentManager();

//    private FragmentController mFragments;
//    private FragmentLifecycleCallbacks mLifecycleCallbacks;

    FragmentHostManager(FragmentService manager, View rootView) {
        mContext = rootView.getContext();
        mManager = manager;
        mRootView = rootView;
        mConfigChanges.applyNewConfig(mContext.getResources());
        createFragmentHost(null);
    }

    private void createFragmentHost(Parcelable savedState) {
//        mFragments = FragmentController.createController(new HostCallbacks());
//        mFragments.attachHost(null);
//        mLifecycleCallbacks = new FragmentLifecycleCallbacks() {
//            @Override
//            public void onFragmentViewCreated(FragmentManager fm, Fragment f, View v,
//                    Bundle savedInstanceState) {
//                FragmentHostManager.this.onFragmentViewCreated(f);
//            }
//
//            @Override
//            public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
//                FragmentHostManager.this.onFragmentViewDestroyed(f);
//            }
//
//            @Override
//            public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
//                Dependency.get(LeakDetector.class).trackGarbage(f);
//            }
//        };
//        mFragments.getFragmentManager().registerFragmentLifecycleCallbacks(mLifecycleCallbacks,
//                true);
//        if (savedState != null) {
//            mFragments.restoreAllState(savedState, (FragmentManagerNonConfig) null);
//        }
//        // For now just keep all fragments in the resumed state.
//        mFragments.dispatchCreate();
//        mFragments.dispatchStart();
//        mFragments.dispatchResume();
    }

//    private Parcelable destroyFragmentHost() {
//        mFragments.dispatchPause();
//        Parcelable p = mFragments.saveAllState();
//        mFragments.dispatchStop();
//        mFragments.dispatchDestroy();
//        mFragments.getFragmentManager().unregisterFragmentLifecycleCallbacks(mLifecycleCallbacks);
//        return p;
//    }

    public FragmentHostManager addTagListener(Activity context, String tag, FragmentListener listener) {
        ArrayList<FragmentListener> listeners = mListeners.get(tag);
        if (listeners == null) {
            listeners = new ArrayList<>();
            mListeners.put(tag, listeners);
        }
        listeners.add(listener);
//        Fragment current = context.getFragmentManager().findFragmentByTag(tag);
//        if (current != null && current.getView() != null) {
//            listener.onFragmentViewCreated(tag, current);
//        }
        return this;
    }

    // Shouldn't generally be needed, included for completeness sake.
    public void removeTagListener(String tag, FragmentListener listener) {
        ArrayList<FragmentListener> listeners = mListeners.get(tag);
        if (listeners != null && listeners.remove(listener) && listeners.size() == 0) {
            mListeners.remove(tag);
        }
    }

    private void onFragmentViewCreated(Fragment fragment) {
        String tag = fragment.getTag();

        ArrayList<FragmentListener> listeners = mListeners.get(tag);
        if (listeners != null) {
            listeners.forEach((listener) -> listener.onFragmentViewCreated(tag, fragment));
        }
    }

    private void onFragmentViewDestroyed(Fragment fragment) {
        String tag = fragment.getTag();

        ArrayList<FragmentListener> listeners = mListeners.get(tag);
        if (listeners != null) {
            listeners.forEach((listener) -> listener.onFragmentViewDestroyed(tag, fragment));
        }
    }

    /**
     * Called when the configuration changed, return true if the fragments
     * should be recreated.
     */
    protected void onConfigurationChanged(Configuration newConfig) {
        if (mConfigChanges.applyNewConfig(mContext.getResources())) {
            reloadFragments();
        } else {
//            mFragments.dispatchConfigurationChanged(newConfig);
        }
    }

    private void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        // TODO: Do something?
    }

    private <T extends View> T findViewById(int id) {
        return mRootView.findViewById(id);
    }

    /**
     * Note: Values from this shouldn't be cached as they can change after config changes.
     */
//    public FragmentManager getFragmentManager() {
//        return mFragments.getFragmentManager();
//    }

    ExtensionFragmentManager getExtensionManager() {
        return mPlugins;
    }

    void destroy() {
//        mFrag ments.dispatchDestroy();
    }

    /**
     * Creates a fragment that requires injection.
     */
    public <T> T create(Class<T> fragmentCls) {
        return (T) mPlugins.instantiate(mContext, fragmentCls.getName(), null);
    }

    public interface FragmentListener {
        void onFragmentViewCreated(String tag, Fragment fragment);

        // The facts of lifecycle
        // When a fragment is destroyed, you should not talk to it any longer.
        default void onFragmentViewDestroyed(String tag, Fragment fragment) {
        }
    }

//    public static FragmentHostManager get(View view) {
//        try {
//            return Dependency.get(FragmentService.class).getFragmentHostManager(view);
//        } catch (ClassCastException e) {
//            // TODO: Some auto handling here?
//            throw e;
//        }
//    }

    public static void removeAndDestroy(View view) {
//        Dependency.get(FragmentService.class).removeAndDestroy(view);
    }

    public void reloadFragments() {
        // Save the old state.
//        Parcelable p = destroyFragmentHost();
        // Generate a new fragment host and restore its state.
//        createFragmentHost(p);
    }

    class HostCallbacks extends FragmentHostCallback<FragmentHostManager> {
        public HostCallbacks() {
            super(mContext, FragmentHostManager.this.mHandler, 0);
        }

        @Override
        public FragmentHostManager onGetHost() {
            return FragmentHostManager.this;
        }

        @Override
        public void onDump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
            FragmentHostManager.this.dump(prefix, fd, writer, args);
        }

        @Override
        public Fragment instantiate(Context context, String className, Bundle arguments) {
            return mPlugins.instantiate(context, className, arguments);
        }

        @Override
        public boolean onShouldSaveFragmentState(Fragment fragment) {
            return true; // True for now.
        }

        @Override
        public LayoutInflater onGetLayoutInflater() {
            return LayoutInflater.from(mContext);
        }

//        @Override
//        public boolean onUseFragmentManagerInflaterFactory() {
//            return true;
//        }

        @Override
        public boolean onHasWindowAnimations() {
            return false;
        }

        @Override
        public int onGetWindowAnimations() {
            return 0;
        }


        @Override
        public boolean onHasView() {
            return true;
        }
    }


    //TODO xhl
    class ExtensionFragmentManager {
        private final ArrayMap<String, Context> mExtensionLookup = new ArrayMap<>();

        public void setCurrentExtension(int id, @NonNull String tag, @Nullable String oldClass,
                                        @NonNull String currentClass, @Nullable Context context) {
            if (oldClass != null) {
                mExtensionLookup.remove(oldClass);
            }
            mExtensionLookup.put(currentClass, context);
//            getFragmentManager()
//                    .replace(id, instantiate(context, currentClass, null), tag)
//                    .commit();
            reloadFragments();
        }

        Fragment instantiate(Context context, String className, Bundle arguments) {
            Context extensionContext = mExtensionLookup.get(className);
            if (extensionContext != null) {
                Fragment f = instantiateWithInjections(extensionContext, className, arguments);
                if (f instanceof Plugin) {
//                    ((Plugin) f).onCreate(mContext, extensionContext);
                }
                return f;
            }
            return instantiateWithInjections(context, className, arguments);
        }

        private Fragment instantiateWithInjections(
                Context context, String className, Bundle args) {
            FragmentService.FragmentInstantiationInfo fragmentInstantiationInfo =
                    mManager.getInjectionMap().get(className);
            if (fragmentInstantiationInfo != null) {
                try {
                    Fragment f = (Fragment) fragmentInstantiationInfo
                            .mMethod
                            .invoke(fragmentInstantiationInfo.mDaggerComponent);
                    // Setup the args, taken from Fragment#instantiate.
                    if (args != null) {
                        args.setClassLoader(f.getClass().getClassLoader());
                        f.setArguments(args);
                    }
                    return f;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new Fragment.InstantiationException("Unable to instantiate " + className,
                            e);
                }
            }
            return Fragment.instantiate(context, className, args);
        }
    }

    private static class PluginState {
        Context mContext;
        String mCls;

        private PluginState(String cls, Context context) {
            mCls = cls;
            mContext = context;
        }
    }
}