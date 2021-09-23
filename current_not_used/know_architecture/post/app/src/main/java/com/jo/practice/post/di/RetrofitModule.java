package com.jo.practice.post.di;

import com.jo.practice.post.data.CommentService;
import com.jo.practice.post.data.PostService;
import com.jo.practice.post.data.UserService;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import retrofit2.Retrofit;

@Module
public class RetrofitModule {
    @Provides
    @Reusable
    PostService providePostService(Retrofit retrofit){
        return retrofit.create(PostService.class);
    }

    @Provides
    @Reusable
    UserService provideUserService(Retrofit retrofit){
        return retrofit.create(UserService.class);
    }

    @Provides
    @Reusable
    CommentService provideCommentService(Retrofit retrofit){
        return retrofit.create(CommentService.class);
    }
}
