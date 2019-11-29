package com.bawei.tianyuchuan20191126;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.bawei.tianyuchuan20191126.bas.BasActivity;
import com.bawei.tianyuchuan20191126.fragment.Home_Fragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
/*
* 作者：田钰川
* 作用：
* 2019年11月26日15:40:36；
* */
public class MainActivity extends BasActivity {

    private TabLayout tb;
    private ViewPager vp;
    ArrayList<Fragment> list = new ArrayList<>();
    ArrayList<String> list1 = new ArrayList<>();
    private ImageView iv;

    @Override
    protected void initData() {
        Home_Fragment fragment1 = new Home_Fragment();
        Home_Fragment fragment2 = new Home_Fragment();
        Home_Fragment fragment3 = new Home_Fragment();
        Home_Fragment fragment4 = new Home_Fragment();

        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);
        list.add(fragment4);
        //田钰川初始化了周考代码
        list1.add("推荐");
        list1.add("视频");
        list1.add("时事");
        list1.add("财经");

        //设置适配器
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return list1.get(position);
            }
        });
    }

    @Override
    protected void initView() {
        tb = findViewById(R.id.tb);
        vp = findViewById(R.id.vp);
        iv = findViewById(R.id.iv);

        tb.setupWithViewPager(vp);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,100);
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    //@Override
    /*protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100&&resultCode==RESULT_OK){
            Uri data1 = data.getData();
            MediaStore.Images.Media.getBitmap(,data1);
        }
    }*/
}
