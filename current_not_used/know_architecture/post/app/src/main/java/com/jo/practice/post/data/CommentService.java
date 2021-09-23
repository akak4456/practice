package com.jo.practice.post.data;

import com.jo.practice.post.data.entity.Comment;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CommentService {
    @GET("/comments")
    Single<List<Comment>> getComments(@Query("postId") long id);
}
