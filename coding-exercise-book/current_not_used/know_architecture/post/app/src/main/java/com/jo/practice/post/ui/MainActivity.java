package com.jo.practice.post.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.Navigator;

import android.os.Bundle;
import android.widget.Toast;

import com.jo.practice.post.R;
import com.jo.practice.post.databinding.ActivityMainBinding;
import com.jo.practice.post.util.SingleLiveEvent;

import org.intellij.lang.annotations.JdkConstants;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    Lazy<ActivityMainBinding> binding;

    @Inject
    @Named("errorEvent")
    SingleLiveEvent<Throwable> errorEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.get().setLifecycleOwner((LifecycleOwner) this);

        errorEvent.observe(this,this::showErrorToast);
    }

    private void showErrorToast(Throwable throwable){
        throwable.printStackTrace();
        showToast(throwable.getMessage());
    }

    private void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}