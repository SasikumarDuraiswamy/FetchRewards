package com.android.fetchrewards.ui.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.fetchrewards.R;
import com.android.fetchrewards.data.model.User;
import com.android.fetchrewards.ui.base.BaseAdapter;

import java.util.List;
import java.util.Map;

public class UserAdapter extends BaseAdapter<Integer, List<User>> {


    public UserAdapter(Context context) {
        super(context);
    }

    public void setData(Map<Integer, List<User>> data){
        super.setData(data);
        super.notifyDataSetChanged();
    }

    @Override
    public int getChildrenCount(int i) {
        return null != getGroup(i)? getGroup(i).size() : 0;
    }

    @Override
    public String getChild(int i, int i1) {
        User user = null;
        if (null != getGroup( i )) {
            user =  getGroup( i ).size() > i1 ? getGroup( i ).get( i1 ) : null;
            if(null != user){
                return user.getName();
            }
        }

        return null;
    }


    @Override
    public View getGroupView(int groupPosition, boolean b, View currentView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(null == currentView){
            currentView = LayoutInflater.from(getContext()).inflate(R.layout.user_group, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.mNameTextView = currentView.findViewById(R.id.user_id);
            currentView.setTag( viewHolder );
        } else {
            viewHolder = (ViewHolder)currentView.getTag();
        }
        viewHolder.mNameTextView.setText( new StringBuilder().append( getGroupItem(groupPosition) ).append( "" ).toString() );
        return currentView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View currentView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(null == currentView){
            currentView = LayoutInflater.from(getContext()).inflate(R.layout.user_item, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.mNameTextView = currentView.findViewById(R.id.user_name);
            currentView.setTag( viewHolder );
        } else {
            viewHolder = (ViewHolder)currentView.getTag();
        }

        viewHolder.mNameTextView.setText(getChild(groupPosition, childPosition));
        return currentView;
    }


    public class ViewHolder {
        private TextView mNameTextView;
    }

}
