package com.bawei.tianyuchuan20191126.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.bawei.tianyuchuan20191126.NetUtils;
import com.bawei.tianyuchuan20191126.R;
import com.bawei.tianyuchuan20191126.bean.GsonBean;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    Context context;
    List<GsonBean.ListdataBean> list;
    public MyAdapter(Context context, List<GsonBean.ListdataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int itemType = list.get(i).getItemType();
        Aaa aaa;
        if (view==null){
            if (itemType == 1){
                view = View.inflate(context,R.layout.itme1,null);
            }else {
                view = View.inflate(context,R.layout.itme2,null);
            }
            aaa = new Aaa();
            aaa.imageView = view.findViewById(R.id.iv);
            aaa.textView1 = view.findViewById(R.id.tv1);
            aaa.textView2 = view.findViewById(R.id.tv2);
             view.setTag(aaa);
        }else {
            aaa = (Aaa) view.getTag();
        }
        NetUtils.getInstance().getPhoto(list.get(i).getImageurl(),aaa.imageView);
        aaa.textView1.setText(list.get(i).getTitle());
        aaa.textView2.setText(list.get(i).getType()+list.get(i).getPublishedAt());
        return view;
    }
    public class Aaa{
        ImageView imageView;
        TextView textView1,textView2;
    }
}
