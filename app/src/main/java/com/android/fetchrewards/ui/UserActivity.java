package com.android.fetchrewards.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.fetchrewards.R;
import com.android.fetchrewards.data.model.User;
import com.android.fetchrewards.ui.base.BaseActivity;
import com.android.fetchrewards.ui.list.UserAdapter;
import com.android.fetchrewards.ui.list.UserViewModel;
import com.android.fetchrewards.ui.list.UserViewModelFactory;
import com.android.fetchrewards.utils.NetworkUtils;

import java.util.List;
import java.util.Map;

public class UserActivity extends BaseActivity<UserViewModel> {

    private UserAdapter mUserAdapter = null;

    private ProgressBar mProgressBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.user_activity );

        mUserAdapter = new UserAdapter(this);
        ExpandableListView expandableListView = findViewById( R.id.user_expandable_list );
        expandableListView.setAdapter(mUserAdapter);

        mProgressBar = findViewById(R.id.progress);

        mViewModel.getLoadingStatus().observeForever( new LoadingObserver() );
        mViewModel.getUser().observeForever(new UserObserver());

        displayUser();
    }

    @NonNull
    @Override
    protected UserViewModel createViewModel() {
        UserViewModelFactory userViewModelFactory = new UserViewModelFactory();
        return new ViewModelProvider(this, userViewModelFactory).get(UserViewModel.class);
    }


    private void displayUser(){
        if(NetworkUtils.isNetworkConnected(this)) {
            mViewModel.loadUser();
        } else {
            Toast.makeText( this, R.string.no_network, Toast.LENGTH_LONG).show();
        }
    }
    //Observer
    private class LoadingObserver implements Observer<Boolean> {

        @Override
        public void onChanged(@Nullable Boolean isLoading) {
            if (isLoading == null) return;

            if (isLoading) {
                mProgressBar.setVisibility( View.VISIBLE);
            } else {
                mProgressBar.setVisibility(View.GONE);
            }
        }
    }

    private class UserObserver implements Observer<Map<Integer, List<User>>> {

        @Override
        public void onChanged(@Nullable Map<Integer, List<User>> users) {
            if (users == null) return;
            mUserAdapter.setData(users);
        }
    }

}
