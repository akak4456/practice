package com.jo.practice.dagger2;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.AndroidSupportInjection;

public class MainFragment extends Fragment {
    @Inject
    @Named("app")
    String appString;

    @Inject
    @Named("activity")
    String activityString;

    @Inject
    @Named("fragment")
    String fragmentString;

    @Override
    public void onAttach(Context context){
        AndroidSupportInjection.inject(this);
        Log.e("MainFragment",appString);
        Log.e("MainFragment",activityString);
        Log.e("MainFragment",fragmentString);
        super.onAttach(context);
    }
}
