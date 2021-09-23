package com.jo.practice.post.ui.post;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jo.practice.post.BR;
import com.jo.practice.post.R;
import com.jo.practice.post.util.ViewBindingHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PostAdapter extends RecyclerView.Adapter<ViewBindingHolder> {
    private final List<PostItem> items = new ArrayList<>();

    @Inject
    public PostAdapter(){

    }

    @Override
    public int getItemViewType(int position){
        return R.layout.view_post;
    }

    @NonNull
    @Override
    public ViewBindingHolder<?> onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        return new ViewBindingHolder(parent.getContext(),viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewBindingHolder holder, int position){
        holder.getBinding().setVariable(BR.item,items.get(position));
        holder.getBinding().executePendingBindings();
    }

    public void setItems(List<PostItem> items){
        this.items.clear();
        this.items.addAll(items);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        return items.size();
    }
}
