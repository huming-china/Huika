package com.huika.hkmall.ui.main;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.huika.hkmall.R;
import com.huika.lib.core.ext.ui.act.BaseAct;
import com.huika.lib.core.ext.views.CustomViewPager;

public class GuideActivity extends BaseAct implements CustomViewPager.OnRightEndScrollListener {

    private CustomViewPager mViewPager;

    private ImageView mPage0;
    private ImageView mPage1;
    private ImageView mPage2;
    private ImageView mPage3;
    @SuppressWarnings("unused")
    private int currIndex = 0;

    public GuideActivity() {
        setAllowFullScreen(true);
    }

    @Override
    public void setRootView() {
        setContentView(R.layout.layout_guide_view);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        mViewPager = (CustomViewPager) findViewById(R.id.whatsnew_viewpager);
        mViewPager.setRightEndScrollListener(this);
        mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        mPage0 = (ImageView) findViewById(R.id.page0);
        mPage1 = (ImageView) findViewById(R.id.page1);
        mPage2 = (ImageView) findViewById(R.id.page2);
        mPage3 = (ImageView) findViewById(R.id.page3);
        PagerAdapter mPagerAdapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public void destroyItem(View container, int position, Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @Override
            public View instantiateItem(View container, int position) {
                LayoutInflater inflater = LayoutInflater.from(container.getContext());
                int layoutId = getResources().getIdentifier("view_guide_" + (position + 1), "layout",
                        getPackageName());
                View pView = inflater.inflate(layoutId, (ViewPager) container, false);
                ((ViewPager) container).addView(pView);
                return pView;
            }
        };

        mViewPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
    }

    /**
     * 最后一张图片Viewpager上面的开始按钮
     */
    public void startbutton(View v) {
        System.gc();
        Intent intent = new Intent();
        intent.setClass(GuideActivity.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void onRightEndScrollListener() {
        startbutton(null);
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        public void onPageSelected(int arg0) {
            switch (arg0) {
                case 0:
                    mPage0.setImageResource(R.drawable.page_now);
                    mPage1.setImageResource(R.drawable.page);
                    mPage2.setImageResource(R.drawable.page);
                    mPage3.setImageResource(R.drawable.page);
                    break;
                case 1:
                    mPage1.setImageResource(R.drawable.page_now);
                    mPage0.setImageResource(R.drawable.page);
                    mPage2.setImageResource(R.drawable.page);
                    mPage3.setImageResource(R.drawable.page);
                    break;
                case 2:
                    mPage2.setImageResource(R.drawable.page_now);
                    mPage0.setImageResource(R.drawable.page);
                    mPage1.setImageResource(R.drawable.page);
                    mPage3.setImageResource(R.drawable.page);
                    break;
                case 3:
                    mPage3.setImageResource(R.drawable.page_now);
                    mPage0.setImageResource(R.drawable.page);
                    mPage1.setImageResource(R.drawable.page);
                    mPage2.setImageResource(R.drawable.page);
                    break;
            }
            currIndex = arg0;
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_guide);
//    }
//
//
//    @Override
//    public void setRootView() {
//
//    }
}
