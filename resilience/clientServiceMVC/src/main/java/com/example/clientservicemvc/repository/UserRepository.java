package com.example.clientservicemvc.repository;

import com.example.clientservicemvc.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRepository {

    public List<UserDto> getUsers(Integer count) {
        List<UserDto> users = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            users.add(new UserDto(i));
        }

        return users;
    }
}
