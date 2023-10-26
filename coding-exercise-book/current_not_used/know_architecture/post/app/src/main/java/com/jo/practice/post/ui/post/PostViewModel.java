package com.jo.practice.post.ui.post;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.jo.practice.post.data.PostService;
import com.jo.practice.post.di.ApplicationContext;
import com.jo.practice.post.util.SingleLiveEvent;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

public class PostViewModel extends AndroidViewModel implements PostItem.EventListener {
    @NonNull
    private final PostService postService;
    @NonNull
    private final SingleLiveEvent<Throwable> errorEvent;

    private final MutableLiveData<List<PostItem>> livePosts = new MutableLiveData<List<PostItem>>();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(true);

    private final SingleLiveEvent<PostItem> postClickEvent = new SingleLiveEvent<>();

    @Inject
    public PostViewModel(@NonNull Application application, PostService postService, @Named("errorEvent") SingleLiveEvent<Throwable> errorEvent) {
        super(application);
        Timber.d("PostViewModel created");
        this.postService = postService;
        this.errorEvent = errorEvent;
    }

    public void loadPosts() {
        compositeDisposable.add(
                postService.getPosts()
                        .flatMapObservable(Observable::fromIterable)
                        .map(post -> new PostItem(post,this))
                        .toList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSuccess(item -> loading.postValue(false))
                        .subscribe(livePosts::setValue, errorEvent::setValue)
        );
    }

    @NonNull
    public MutableLiveData<List<PostItem>> getLivePosts(){
        return livePosts;
    }

    @Override
    protected void onCleared(){
        super.onCleared();
        Timber.d("onCleared");
        compositeDisposable.dispose();
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    @Override
    public void onPostClick(PostItem postItem) {
        postClickEvent.setValue(postItem);
    }

    public SingleLiveEvent<PostItem> getPostClickEvent(){
        return postClickEvent;
    }
}
