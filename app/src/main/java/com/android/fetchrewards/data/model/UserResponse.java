package com.android.fetchrewards.data.model;

import java.util.List;
import java.util.Map;

/**
 * UserResponse class for FetchRewards user api
 */
public class UserResponse extends BaseResponse{

    /**
     * Variable to hold the users information in the hashmap
     * Its auto sorted based on the key
     */
    private Map<Integer, List<User>> users;

    /**
     * Method to get the list user information
     * @return - Map
     */
    public Map<Integer, List<User>> getUsers() {
        return users;
    }

    /**
     * Method to set the list user information
     * @param users - Map
     */
    public void setUsers(Map<Integer, List<User>> users) {
        this.users = users;
    }
}
