package com.android.fetchrewards.ui.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<ViewModel extends BaseViewModel> extends AppCompatActivity {

    protected ViewModel mViewModel;

    @NonNull
    protected abstract ViewModel createViewModel();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = createViewModel();
    }

}
