package com.bawei.tianyuchuan20191126.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bawei.tianyuchuan20191126.NetUtils;
import com.bawei.tianyuchuan20191126.R;
import com.bawei.tianyuchuan20191126.SendActivity;
import com.bawei.tianyuchuan20191126.adapter.MyAdapter;
import com.bawei.tianyuchuan20191126.bas.BasFragment;
import com.bawei.tianyuchuan20191126.bean.GsonBean;
import com.google.gson.Gson;
import com.qy.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;
/*
 * 作者：田钰川
 * 作用：
 * 2019年11月26日15:40:36；
 * */
/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Fragment extends BasFragment {

    private XListView lv;
    int page = 1;
    List<GsonBean.ListdataBean> list = new ArrayList<>();
    private ImageView iv;

    @Override
    protected void initView(View inflate) {
        lv = inflate.findViewById(R.id.lv);
        lv.setPullLoadEnable(true);
        lv.setPullRefreshEnable(true);
        iv = inflate.findViewById(R.id.ivv);
        lv.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                page = 1;
                list.clear();
                initData();
                lv.stopRefresh();
            }

            @Override
            public void onLoadMore() {
                page++;

                initData();
                lv.stopLoadMore();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), SendActivity.class);
                intent.putExtra("key","https://www.thepaper.cn/newsDetail_forward_4923534");
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        //判断是否有网
        boolean b = NetUtils.getInstance().hasNet(getActivity());
        if (b){
            iv.setVisibility(View.GONE);
            lv.setVisibility(View.VISIBLE);
            String HttpUrla = "";
            if (page==1){
                HttpUrla = "http://blog.zhaoliang5156.cn/api/pengpainews/pengpai1.json";
            }else {
                HttpUrla = "http://blog.zhaoliang5156.cn/api/pengpainews/pengpai2.json";
            }
            NetUtils.getInstance().getString(HttpUrla, new NetUtils.MyCallBack() {
                @Override
                public void onGetJson(String json) {
                    GsonBean gsonBean = new Gson().fromJson(json, GsonBean.class);
                    List<GsonBean.ListdataBean> listdata = gsonBean.getListdata();
                    list.addAll(listdata);
                    MyAdapter myAdapter = new MyAdapter(getActivity(),list);
                    lv.setAdapter(myAdapter);
                }
            });
        }else {
            lv.setVisibility(View.GONE);
            iv.setVisibility(View.VISIBLE);
        }
    }
}
