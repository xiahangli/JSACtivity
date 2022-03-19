package com.example.jsactivity.pluginimpl;

import android.content.Context;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.demo.plugin.Plugin;
import com.demo.plugin.QS;
import com.example.jsactivity.R;
import com.example.jsactivity.SubModuleElement;
import com.example.jsactivity.pluginimpl.fragmentinviewpager.Fragment1;
import com.example.jsactivity.pluginimpl.qspanel.QSFragment;
import com.example.jsactivity.pluginimpl.qspanel.dagger.FragmentCreator;
import com.example.jsactivity.pluginimpl.qspanel.dagger.QSFragmentComponent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.inject.Inject;

import dagger.Subcomponent;

/**
 * @author Henry
 * @Date 2022/3/20  2:34 AM
 * @Email 2427417167@qq.com
 */
public class MainActivity1 extends FragmentActivity {

    private static final String TAG_QS = "TAG_QS";
    private Fragment mFragment;
    private Fragment mFragment2;
    private Fragment mFragment1;
    @Inject
    FragmentCreator.Builder mFragmentFactory;

    private ViewPager pager;
    private FragmentManager mSupportFragmentManager;
    private Fragment[] mFragments = new Fragment[2];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityComponent activityComponent = DaggerActivityComponent.create();
        activityComponent.inject(this);
        SubModuleElement subModuleElement = activityComponent.getSubModuleElement();
        FragmentCreator.Builder fragmentCreate = activityComponent.getFragmentCreator();
        FragmentCreator fragmentCreator = fragmentCreate.build();
         activityComponent.getQSFragmentComponent();
//        qsFragmentComponent

        Log.e("xia1", "onCreate: sub " + subModuleElement + "\nfragmentCreator " +
                fragmentCreate + "\nfragmentCreator " + fragmentCreator);
        mFragment1 = new Fragment(R.layout.fragment1);
        mFragment2 = new Fragment(R.layout.fragment2);
        mFragments[0] = mFragment1;
        mFragments[1] = mFragment2;
        setContentView(R.layout.activity_main);
        pager = findViewById(R.id.view_pager);

        //TODO 放开下面两行
//        Log.e("xia1", "onCreate: mFragmentFactory " + mFragmentFactory);
        addFragmentInstantiationProvider(fragmentCreator);
        mFragment = create(QSFragment.class);
        mSupportFragmentManager = getSupportFragmentManager();
        mSupportFragmentManager.beginTransaction()
                .replace(R.id.container, mFragment, TAG_QS)
                .commitAllowingStateLoss();
        /**
         *
         *
         *   ExtensionFragmentListener.attachExtensonToFragment(container,
         *   QS.TAG,
         *   R.id.qs_frame,
         *   mExtensionController.newExtension(QS.class)
         *                        .withPlugin(QS.class)
         *                         .withDefault(this::createDefaultQSFragment)
         *                         .build()
         *                         );
         *
         *  private ExtensionFragmentListener(View view,
         *  String tag,
         *  int id,
         *  Extension<T> extension
         *  ) {
         *         mTag = tag;
         *         mFragmentHostManager = FragmentHostManager.get(view);
         *         mExtension = extension;
         *         mId = id;
         *         mFragmentHostManager.getFragmentManager().beginTransaction()
         *                 .replace(id, (Fragment) mExtension.get(), mTag)
         *                 .commit();
         *         mExtension.clearItem(false);
         *     }
         */


//        ExtensionFragmentListener.attachExtensonToFragment(container, QS.TAG, R.id.qs_frame,
//                mExtensionController
//                        .newExtension(QS.class)
//                        .withPlugin(QS.class)
//                        .withDefault(this::createDefaultQSFragment)
//                        .fragmentCreator());


        /*pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return false;
            }
        });*/

        pager.setAdapter(new FragmentPagerAdapter(mSupportFragmentManager) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                Log.e("xia1", "getItem: position " + position);
                return mFragments[position];
            }

            @Override
            public int getCount() {
                return 2;
            }
        });


    }

    private final ExtensionFragmentManager mPlugins = new ExtensionFragmentManager();

    public <T> T create(Class<T> fragmentCls) {
        Log.e("xia1", "create: fragmentCls " + fragmentCls.getName());
        return (T) mPlugins.instantiate(this, fragmentCls.getName(), null);
    }


    class ExtensionFragmentManager {
        private final ArrayMap<String, Context> mExtensionLookup = new ArrayMap<>();


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
            FragmentInstantiationInfo fragmentInstantiationInfo = mInjectionMap.get(className);
            if (fragmentInstantiationInfo != null) {
                try {
                    Fragment f = (Fragment) fragmentInstantiationInfo
                            .mMethod
                            .invoke(fragmentInstantiationInfo.mDaggerComponent);
                    Log.e("xia1", "instantiateWithInjections: args  " + args
                    + "\nfragmentInstantiationInfo1 " + fragmentInstantiationInfo.mMethod); // args null
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
////////////////////////////////////////////

    private final ArrayMap<String, FragmentInstantiationInfo> mInjectionMap = new ArrayMap<>();

    ArrayMap<String, FragmentInstantiationInfo> getInjectionMap() {
        return mInjectionMap;
    }



    /**
     * Adds a new Dagger component object that provides method(s) to create fragments via injection.
     */
    public void addFragmentInstantiationProvider(Object daggerComponent) {
        Log.e("xia1", "addFragmentInstantiationProvider: ");
        for (Method method : daggerComponent.getClass().getDeclaredMethods()) {
            Log.e("xia1", "addFragmentInstantiationProvider: " + method);
            if (Fragment.class.isAssignableFrom(method.getReturnType())
                    && (method.getModifiers() & Modifier.PUBLIC) != 0) {
                String fragmentName = method.getReturnType().getName();
                Log.e("xia1", "addFragmentInstantiationProvider: fragmentName " + fragmentName);
                if (mInjectionMap.containsKey(fragmentName)) {
                    Log.e("xia1", "Fragment " + fragmentName + " is already provided by different"
                            + " Dagger component; Not adding method");
                    continue;
                }
                mInjectionMap.put(
                        fragmentName, new FragmentInstantiationInfo(method, daggerComponent));
            }
        }
    }

    /** An object containing the information needed to instantiate a fragment. */
    static class FragmentInstantiationInfo {
        /** The method that returns a newly-created fragment of the given class. */
        final Method mMethod;
        /** The Dagger component that the method should be invoked on. */
        final Object mDaggerComponent;
        FragmentInstantiationInfo(Method method, Object daggerComponent) {
            this.mMethod = method;
            this.mDaggerComponent = daggerComponent;
        }
    }


}
