package io.madcamp.yh.mc_assignment1;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final int PERMISSION_REQ_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 탭 초기화 */
        setupTabs();
    }

    private void setupTabs() {
        /* 필요한 View를 불러옴 */
        ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.sliding_tabs);
        /* TabPagerAdapter 추가 */
        viewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(), MainActivity.this));
        /* tabLayout 초기화 */
        tabLayout.setupWithViewPager(viewPager);
    }

}
