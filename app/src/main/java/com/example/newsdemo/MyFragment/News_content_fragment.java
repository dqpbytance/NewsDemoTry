package com.example.newsdemo.MyFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.newsdemo.R;

public class News_content_fragment extends Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //将显示内容的碎片的布局加载出来，并通过一个类变量保存下来，方便后续对这个碎片里面的控件进行更新操作
        view=inflater.inflate(R.layout.news_content_fragment,container,false);
        return view;
    }

    public void refresh(String title,String content){
        View visiableLayout=view.findViewById(R.id.visible_layout);
        visiableLayout.setVisibility(View.VISIBLE);
        TextView tv_title=view.findViewById(R.id.news_title);
        TextView tv_content=view.findViewById(R.id.news_content);
        tv_title.setText(title);
        tv_content.setText(content);
    }
}
