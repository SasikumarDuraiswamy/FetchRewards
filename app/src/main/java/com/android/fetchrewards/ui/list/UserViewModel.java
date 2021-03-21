package com.android.fetchrewards.ui.list;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.android.fetchrewards.data.model.User;
import com.android.fetchrewards.data.model.UserResponse;
import com.android.fetchrewards.data.service.UserService;
import com.android.fetchrewards.ui.base.BaseViewModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends BaseViewModel {

    private MutableLiveData<Map<Integer, List<User>>> mUserData;
    private MutableLiveData<Boolean> mIsLoading;

    private UserService mUserService;

    UserViewModel(UserService userService){
        this.mUserService = userService;
        mIsLoading = new MutableLiveData<>();
        mUserData = new MutableLiveData<>();
    }

    public void setUser(Map<Integer, List<User>> userData) {
        toggleProgress( false);
        this.mUserData.postValue(userData);
    }

    public void toggleProgress(boolean isLoading){
        this.mIsLoading.postValue( isLoading);
    }

    public void loadUser(){
        toggleProgress( true);
        mUserService.getHiringApi().getAllUser().enqueue(new UserCallback());
    }

    public MutableLiveData<Map<Integer, List<User>>> getUser() {
        return mUserData;
    }

    public MutableLiveData<Boolean> getLoadingStatus() { return mIsLoading; };

    /**
     * Callback
     **/
    private class UserCallback implements Callback<UserResponse> {

        @Override
        public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
            UserResponse movieResponse = response.body();

            if (movieResponse != null) {
                setUser(movieResponse.getUsers());
            } else {
                setUser(null);
            }
        }

        @Override
        public void onFailure(Call<UserResponse> call, Throwable t) {
            setUser(null);

        }
    }
}
