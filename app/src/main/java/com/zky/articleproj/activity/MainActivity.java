package com.zky.articleproj.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.yimingyu.android.core.MyCallBack;
import com.yimingyu.android.lib.duasdk.DuaTest;
import com.zky.articleproj.R;
import com.zky.articleproj.base.BaseActivity;
import com.zky.articleproj.fragment.IndexFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("NewApi")
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewInject(R.id.tool_bar)
    private Toolbar mToolbar;
    @ViewInject(R.id.tab_layout)
    private TabLayout mTabLayout;
    @ViewInject(R.id.view_pager)
    private ViewPager mViewPager;
    @ViewInject(R.id.draw_layout)
    private DrawerLayout mDrawerLayout;
    @ViewInject(R.id.nav_view)
    private NavigationView mNavigationView;

    private View headView;

    private ActionBarDrawerToggle mDrawerToggle;
    private List<Fragment> pagers;
    private ImageView user_icon;

    private DuaTest duaTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        duaTest = DuaTest.getInstance(this);

        duaTest.getCurrentDuaId(new MyCallBack() {
            @Override
            public void onSuccess(String s) {
                System.out.println("--------" + s);
            }

            @Override
            public void onError(String s) {

            }
        });

        headView = mNavigationView.getHeaderView(0);
        user_icon = (ImageView) headView.findViewById(R.id.user_icon);
        user_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                Toast.makeText(MainActivity.this, "登录", Toast.LENGTH_SHORT).show();
            }
        });

        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("ArticleClient");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);

        pagers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pagers.add(new IndexFragment());
        }
    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        private final String tabTitles[] = new String[]{"番组", "动画", "音乐", "舞蹈", "游戏",
                "科技", "娱乐", "鬼畜", "电影", "电视剧"};
        private final String typeid[] = new String[]{"13", "1", "117", "20", "4",
                "36", "5", "119", "23", "11"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return pagers.get(position);
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //dua.duaAwake();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // dua.duaSleep();
    }
}