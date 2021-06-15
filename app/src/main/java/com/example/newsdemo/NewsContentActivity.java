package com.example.newsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.newsdemo.MyFragment.News_content_fragment;


//因为存在单叶的可能性，这个时候点击标题就会跳转到对应的页面中去，通常来讲，跳转过来时会带着题目以及内容过来，这个时候就可以在这个activity
//的布局文件复用之前的新闻详细内容碎片，这样就可以直接对他进行更新，避免二次编写代码
public class NewsContentActivity extends AppCompatActivity {


    //这是一种很好的启动别的activity的方法，让每一个activity都实现这样一个 方法，想要启动他时直接调用这个方法就行
    public static void activityStart(Context context,String title,String content){
        Intent intent=new Intent(context,NewsContentActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("content",content);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        Intent intent=getIntent();
        String news_title=intent.getStringExtra("title");
        String news_content=intent.getStringExtra("content");
        News_content_fragment news_content_fragment=(News_content_fragment) getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        news_content_fragment.refresh(news_title,news_content);
    }
}