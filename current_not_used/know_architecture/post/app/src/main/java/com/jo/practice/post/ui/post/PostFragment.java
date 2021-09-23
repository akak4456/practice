package com.jo.practice.post.ui.post;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jo.practice.post.databinding.FragmentPostBinding;
import com.jo.practice.post.di.AppViewModelFactory;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerFragment;

public class PostFragment extends DaggerFragment {

    @Inject
    FragmentPostBinding binding;
    @Inject
    AppViewModelFactory viewModelFactory;

    PostViewModel viewModel;

    @Inject
    PostAdapter adapter;

    @Inject
    LinearLayoutManager layoutManager;

    @Inject
    Lazy<NavController> navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this,viewModelFactory).get(PostViewModel.class);
        if(savedInstanceState == null){
            viewModel.loadPosts();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstance){
        super.onViewCreated(view,savedInstance);

        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.setViewModel(viewModel);
        viewModel.getLivePosts()
                .observe(getViewLifecycleOwner(),list->adapter.setItems(list));

        viewModel.getPostClickEvent()
                .observe(getViewLifecycleOwner(),postItem -> navController.get().navigate(0));
    }
}
