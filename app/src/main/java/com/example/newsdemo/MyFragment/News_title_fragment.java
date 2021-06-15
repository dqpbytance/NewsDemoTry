package com.example.newsdemo.MyFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsdemo.Dao.News;
import com.example.newsdemo.NewsContentActivity;
import com.example.newsdemo.R;

import java.util.ArrayList;
import java.util.List;


//新闻标题碎片，这个是不管单叶还是双叶都会包含的一个碎片，用recyclerview来展示，在这个碎片的createview环节，为这个recylcerview设置自己创建的适配器
public class News_title_fragment extends Fragment {
    private boolean isTwoPane;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.news_title_fragment,container,false);
        RecyclerView recyclerView=view.findViewById(R.id.news_title_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new MyAdapter(getNews()));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(getActivity().findViewById(R.id.news_content_layout)!=null){
            isTwoPane=true;
        }else{
            isTwoPane=false;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if(getActivity().findViewById(R.id.news_content_layout)!=null){
            isTwoPane=true;
        }else{
            isTwoPane=false;
        }
    }
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private List<News> list;
        public  MyAdapter(List<News> list){
            this.list=list;
        }
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //加载recyclerview子布局，
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
            MyAdapter.MyViewHolder myViewHolder=new MyAdapter.MyViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news=list.get(myViewHolder.getBindingAdapterPosition());
//                    if (!isTwoPane)
//                    Toast.makeText(getActivity(),"1111111111",Toast.LENGTH_SHORT).show();
                    if (isTwoPane){
                        News_content_fragment news_content_fragment= (News_content_fragment) getParentFragmentManager().findFragmentById(R.id.news_content_fragment);
                        news_content_fragment.refresh(news.getTitle(), news.getContent());
                    }else{
                        NewsContentActivity.activityStart(getActivity(),news.getTitle(),news.getContent());
                    }
                }
            });
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
            News news=list.get(position);
            holder.tv.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
        class MyViewHolder extends RecyclerView.ViewHolder{
            public TextView tv;
            public MyViewHolder(View itemView) {
                super(itemView);
                tv=itemView.findViewById(R.id.recycler_title);
            }
        }
    }

    List<News> getNews(){
        List<News> list=new ArrayList<>();
        for (int i=0;i<50;i++){
            News news=new News();
            news.setTitle("this is title"+i);
            news.setContent("hhahahaahahahahahahahahaa"+i);
            list.add(news);
        }
        return list;
    }
}
