package org.example.service.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.enums.Gender;
import org.example.models.UserDetails;
import org.example.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class UserServiceImpl implements UserService {


    public UserDetails loginUser(String mobileNumber, Map<String, UserDetails> mobileNumberWiseUserView, String currentLoggedInUserMobileNumber) {
          if(!mobileNumberWiseUserView.containsKey(mobileNumber)){
              log.error("User not found");
          }
          currentLoggedInUserMobileNumber = mobileNumber;
          return mobileNumberWiseUserView.get(mobileNumber);
    }

    public UserDetails registerUser(String mobileNumber, String gender, String name, String pincode, Map<String, UserDetails> mobileNumberWiseUserView)  {
        if(StringUtils.isAnyBlank(mobileNumber)){
           log.error("Mobile number is blank");
        }
        if(mobileNumberWiseUserView.containsKey(mobileNumber)){
            log.error("Mobile number is already registered");
        }
        UserDetails newUser= UserDetails.builder()
                .phoneNumber(mobileNumber)
                .gender(gender.equalsIgnoreCase("m")?Gender.M:Gender.F)
                .pincode(pincode)
                .name(name)
                .build();
        mobileNumberWiseUserView.put(mobileNumber,newUser);
        return newUser;
    }


}
