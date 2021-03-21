package com.android.fetchrewards.data.service;

import com.android.fetchrewards.data.model.User;
import com.android.fetchrewards.data.model.UserResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * GsonDeserializer class to custom Deserializer the value from the user api
 * If construct the Group of used based on the id and its store in the sorted order
 */
public class GsonDeserializer implements JsonDeserializer<UserResponse> {
    @Override
    public UserResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        //Initialize the Map of user gorup
        Map<Integer, List<User>> userGroup = new TreeMap<>();
        //Get the json Object from the received response
        JsonObject itemJsonObject = json.getAsJsonObject();
        //Get the list of user data from the parent object
        JsonArray itemsJsonArray = itemJsonObject.getAsJsonArray( "user" );
        User user;
        List<User> users;
        String name;
        int listId;
        int id;
        //Iterate the array to get the user infomation
        for (JsonElement itemsJsonElement : itemsJsonArray) {
            itemJsonObject = itemsJsonElement.getAsJsonObject();
            //Check if the name is Null or not
            if(itemJsonObject.get("name") != JsonNull.INSTANCE) {
                //Get the name from the object
                name = itemJsonObject.get( "name" ).getAsString();
                //Check the name is empty or null
                if (null != name && name.trim().length() > 0 && !name.trim().equalsIgnoreCase( "null" )) {
                    //Get the id from the object
                    id = itemJsonObject.get( "id" ).getAsInt();
                    //Get the list id from the object
                    listId = itemJsonObject.get( "listId" ).getAsInt();
                    //Construct the user object
                    user = new User( name, listId );
                    //Get the list of users from the map
                    users = userGroup.get( id );
                    //Check list is null or not
                    if (null == users) {
                        //If null, initialize the empty array
                        users = new ArrayList<>();
                    }
                    //Add the user model into list
                    users.add( user );
                    //Check list of user size is more than one before to sort to avoid the un-necessary sort
                    if(users.size()>1) {
                        Collections.sort( users );
                    }
                    //Put the list into the map
                    userGroup.put( id, users );
                }
            }
        }
        //Create the userResponse instance
        UserResponse userResponse = new UserResponse();
        //Set the users information into the userResponse
        userResponse.setUsers(userGroup);
        //Return the data into the callback
        return userResponse;
    }
}
