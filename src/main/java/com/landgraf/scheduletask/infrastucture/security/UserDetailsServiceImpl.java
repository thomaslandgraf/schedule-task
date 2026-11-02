package com.landgraf.scheduletask.infrastucture.security;


import com.landgraf.scheduletask.infrastucture.business.dto.UserDTO;
import com.landgraf.scheduletask.infrastucture.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UserClient client;

    public UserDetails loadDataFromUser(String email, String token){
        UserDTO userDTO = client.getUserByEmail(email, token);
        return User
                .withUsername(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
    }
}
