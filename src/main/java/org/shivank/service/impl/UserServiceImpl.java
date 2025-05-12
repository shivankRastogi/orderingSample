package org.shivank.service.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.shivank.enums.Gender;
import org.shivank.models.UserDetails;
import org.shivank.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class UserServiceImpl implements UserService {
    private Map<String, UserDetails> mobileNumberWiseUserView ; //key is mobile number
    private String currentLoggedInUserMobileNumber ;



    @Override
    public UserDetails loginUser(String mobileNumber) {
          if(!mobileNumberWiseUserView.containsKey(mobileNumber)){
              log.error("User not found");
              return null;
          }
          currentLoggedInUserMobileNumber = mobileNumber;
          return mobileNumberWiseUserView.get(mobileNumber);
    }

    @Override
    public UserDetails registerUser(String mobileNumber, String gender, String name, String pincode)  {
        if(StringUtils.isAnyBlank(mobileNumber)){
           log.error("Mobile number is blank");
           return null;
        }
        if(mobileNumberWiseUserView.containsKey(mobileNumber)){
            log.error("Mobile number is already registered");
            return null;
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

    @Override
    public UserDetails getLoggedInUser()
    {
        if(currentLoggedInUserMobileNumber==null)
        {
            log.warn("No user logged in");
            return null;
        }
        return mobileNumberWiseUserView.get(currentLoggedInUserMobileNumber);
    }


}
