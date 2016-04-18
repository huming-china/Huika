package com.huika.hkmall.ui.main;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.huika.hkmall.R;
import com.huika.hkmall.ui.index.fragment.HKServiceFragment;
import com.huika.hkmall.ui.index.fragment.HKshopFragment;
import com.huika.hkmall.ui.my.login.MeForTabFragment;
import com.huika.hkmall.ui.pugin.PuginFra;
import com.huika.lib.core.ext.ui.act.BaseAct;
import com.huika.lib.core.ext.views.JazzyViewPager;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by zhangchao on 2015/12/16.
 */
public class MainActivity extends BaseAct implements ViewPager.OnPageChangeListener {

    private JazzyViewPager pager;
    private LinearLayout mPpageIndicator;
    private Set<Integer> initFrags;
        private MainPagerAdapter pagerAdapter;
    private ImageView tab_pop_icon_me;
    /**
     * 是否退出应用
     */
    private boolean isExit;
    private TextView numberTv;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            isExit = false;
        }
    };

    public JazzyViewPager getPager() {
        return pager;
    }

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initWidget() {
        super.initWidget();

        //findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        //    }
        //});

        pager = (JazzyViewPager) findViewById(R.id.pager);
        pager.setFadeEnabled(true);
        pager.setTransitionEffect(JazzyViewPager.TransitionEffect.Standard);

        pager.setOffscreenPageLimit(1);

        pager.addOnPageChangeListener(this);
        ArrayList<Fragment> arrayFragments = new ArrayList<Fragment>();
        arrayFragments.add(new PuginFra());
        arrayFragments.add(new HKServiceFragment());
        arrayFragments.add(new HKshopFragment());
        arrayFragments.add(new HKServiceFragment());
        arrayFragments.add(new MeForTabFragment());
        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), arrayFragments);
        pager.setAdapter(pagerAdapter);

        mPpageIndicator = (LinearLayout) findViewById(R.id.page_indicator);
        int tabCount = mPpageIndicator.getChildCount();
        for (int i = 0; i < tabCount; i++) {
            mPpageIndicator.getChildAt(i).setTag(i);
            mPpageIndicator.getChildAt(i).setOnClickListener(this);
        }

        onPageSelected(0);
    }

    @Override public void widgetClick(View v) {
        int index = (Integer) v.getTag();
        if (index != pager.getCurrentItem()) {
            pager.setCurrentItem(index, false);
        }
        super.widgetClick(v);
    }
    //    private void login() {
//        String md5Pwd = MD5Security.getMd5_32_UP("l12345678");
//        String rsaPwd = RsaHelper.encryptDataFromStr(md5Pwd, RsaHelper.publicKey);
//        String aaa = RsaHelper.encryptDataFromStr("17700000114", RsaHelper.publicKey);
//        HuiKaRetrofitApi.getInstance().get().login(aaa, rsaPwd, "2", new ResultCallback<RequestResult<Object>>() {
//            @Override
//            public void success(RequestResult<Object> objectRequestResult, Response response) {
//                super.success(objectRequestResult, response);
//                Log.e("login", "objectRequestResult=" + objectRequestResult);
//                Log.e("login", "response=" + response);
//            }
//        });
//
//    }


    private class MainPagerAdapter extends PagerAdapter {
        private FragmentManager fm = null;
        private ArrayList<Fragment> fragmentList;
        //记录是否是首次显示
        ArrayMap<Integer, Boolean> isFirstVisbles = new ArrayMap<Integer, Boolean>(5);

        public MainPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentList) {
            this.fm = fm;
            this.fragmentList = fragmentList;
            for (int i = 0; i < 5; i++) {
                isFirstVisbles.put(i, false);
            }
        }

        @Override public void setPrimaryItem(ViewGroup container, int position, Object object) {
            // TODO Auto-generated method stub
            if (isFirstVisbles.get(position) == false) {
        /* 这里是首次调用 */
                Fragment frag = fragmentList.get(position);
                isFirstVisbles.put(position, true);// 设置为不是首次
                //if (frag instanceof BaseFragment) ((BaseFragment) frag).initFristData();// 加载数据
            }
        }

        @Override public int getCount() {
            // TODO Auto-generated method stub
            return 5;
        }

        @Override public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = fragmentList.get(position);
            if (!fragment.isAdded()) { // 如果fragment还没有added
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(fragment, fragment.getClass().getSimpleName());
                ft.commit();
                /**
                 * 在用FragmentTransaction.commit()方法提交FragmentTransaction对象后
                 * 会在进程的主线程中，用异步的方式来执行。 如果想要立即执行这个等待中的操作，就要调用这个方法（只能在主线程中调用）。
                 * 要注意的是，所有的回调和相关的行为都会在这个调用中被执行完成，因此要仔细确认这个方法的调用位置。
                 */
                fm.executePendingTransactions();
            }

            if (fragment.getView().getParent() == null) {
                container.addView(fragment.getView()); // 为viewpager增加布局
            }

            return fragment.getView();
        }

        @Override public void destroyItem(ViewGroup container, int position, Object object) {
            // TODO Auto-generated method stub
            // super.destroyItem(container, position, object);
            container.removeView(fragmentList.get(position).getView());
            object = null;
        }
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        final int tabCount = mPpageIndicator.getChildCount();
        for (int i = 0; i < tabCount; i++) {
            final View child = mPpageIndicator.getChildAt(i);
            final boolean isSelected = (i == position);
            child.setSelected(isSelected);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
