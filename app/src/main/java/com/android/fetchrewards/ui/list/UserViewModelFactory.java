package com.android.fetchrewards.ui.list;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.android.fetchrewards.data.service.UserService;

public class UserViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserViewModel.class)) {
            return (T) new UserViewModel(UserService.getInstance());
        }

        throw new IllegalArgumentException("Unknown model");
    }
}
