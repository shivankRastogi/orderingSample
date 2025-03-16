package org.shivank.service;

import org.shivank.models.UserDetails;

public interface UserService {
    public UserDetails loginUser(String mobileNumber);

    public UserDetails registerUser(String mobileNumber, String gender, String name, String pincode);

    public UserDetails getLoggedInUser();
}
