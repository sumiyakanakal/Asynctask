package com.example.pn.booksearchnew;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.example.pn.booksearchnew.R.id.bookimage;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context ct;
    ArrayList<Booksmodel> lists;
    public MyAdapter(MainActivity mainActivity, ArrayList<Booksmodel> list) {
        this.ct=mainActivity;
        this.lists=list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       // return null;
        View v = LayoutInflater.from(ct).inflate(R.layout.row,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(v);
        return myViewHolder;
    }

    @Override

    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Booksmodel model=lists.get(i);
        String imagelink;
        imagelink = model.getImage1();
        Glide.with(ct).load(imagelink).into(myViewHolder.iv2);
        myViewHolder .tv2.setText(model.getTitle1());

    }



    @Override
    public int getItemCount() {
        //return 0;
        return lists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv2;
        TextView tv2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv2=itemView.findViewById(R.id.bookimage);
            tv2=itemView.findViewById(R.id.booktitle);

        }

    }
}
