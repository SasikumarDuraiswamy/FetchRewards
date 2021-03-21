package com.android.fetchrewards.data.model;

/**
 * User model class to hold the name and list id from the api
 * Comparable to sort the class based on the user name
 */
public class User extends BaseModel implements Comparable<User> {

    /**
     * Variable to hold the user name
     */
    private String name;

    /**
     * Variable to hold the list item id
     */
    private int listId;

    /**
     * Constructor to set the username and list id
     * @param name - User name
     * @param listId - Item id
     */
    public User(String name, int listId){
        this.name = name;
        this.listId = listId;
    }

    /**
     * Method to get the user name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Method to set the user name
     * @param name - String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to set the list item id
     * @param listId - String
     */
    public void setListId(int listId) {
        this.listId = listId;
    }

    @Override
    public int compareTo(User user) {
        return this.getName().compareTo(user.getName());
    }
}
