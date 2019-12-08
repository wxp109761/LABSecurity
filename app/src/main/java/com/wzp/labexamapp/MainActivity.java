package com.wzp.labexamapp;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.navigation.NavigationView;
import com.wzp.labexamapp.Adapters.MyFragmentPagerAdapter;
import com.wzp.labexamapp.Fragments.FindFragment;
import com.wzp.labexamapp.Fragments.HomeFragment;
import com.wzp.labexamapp.Fragments.MessageFragment;
import com.wzp.labexamapp.Fragments.NavigationFragment;
import com.wzp.labexamapp.Widget.AlwaysCenterTextView;
import com.xuexiang.xui.XUI;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.fragment_vp)
    ViewPager mViewPager;
    @BindView(R.id.tabs_rg)
    RadioGroup mRadioGroup;
    @BindView(R.id.main_nav_view)
    NavigationView mNavView;
    @BindView(R.id.main_drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.ll_top_menu_nav)
    LinearLayout llTopMenuNav;
    @BindView(R.id.tv_title)
    AlwaysCenterTextView tvTitle;
    @BindView(R.id.find_tab)
    RadioButton findTab;
    @BindView(R.id.message_tab)
    RadioButton messageTab;
    @BindView(R.id.daohang_tab)
    RadioButton daohangTab;


    private List<Fragment> mFragments;
    private FragmentPagerAdapter mAdapter;


    private String[] mTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XUI.init(this.getApplication()); //初始化UI框架
        XUI.debug(true);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        DrawLayout();
        mFragments = new ArrayList<>(4);
        mFragments.add(new HomeFragment());
        mFragments.add(new FindFragment());
        mFragments.add(new MessageFragment());
        mFragments.add(new NavigationFragment());
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(mPageChangeListener);
        mRadioGroup.setOnCheckedChangeListener(mOnCheckedChangeListener);
        tvTitle.setText("首页");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewPager.removeOnPageChangeListener(mPageChangeListener);
    }

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            RadioButton radioButton = (RadioButton) mRadioGroup.getChildAt(position);
            radioButton.setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };
    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            for (int i = 0; i < group.getChildCount(); i++) {
                if (group.getChildAt(i).getId() == checkedId) {
                    mViewPager.setCurrentItem(i);
                    switch (i){
                        case 0:
                            tvTitle.setText("首页");
                            break;
                        case 1:
                            tvTitle.setText("发现");
                            break;

                        case 2:
                            tvTitle.setText("消息");
                            break;
                        case 3:
                            tvTitle.setText("导航");
                            break;

                    }
                    return;
                }
            }
        }
    };

    //侧边栏
    public void DrawLayout(){
        //侧边栏
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d("xxxxxxxxx", item.getTitle() + "");
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        //隐藏NavigationView右侧滚动条
        NavigationMenuView navigationMenuView = (NavigationMenuView) mNavView.getChildAt(0);
        if (navigationMenuView != null) {
            navigationMenuView.setVerticalScrollBarEnabled(false);
        }

        llTopMenuNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("xxxxxxxxx",  "zzz");
                if(mDrawerLayout.isDrawerOpen(GravityCompat.START))
                {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else
                {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
    }


}
