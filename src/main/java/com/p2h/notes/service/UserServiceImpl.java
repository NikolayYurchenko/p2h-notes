package com.p2h.notes.service;

import com.p2h.notes.data.service.UserDataService;
import com.p2h.notes.model.UserRequest;
import com.p2h.notes.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private UserDataService userDataService;

    @Override
    public UserResponse create(UserRequest userRequest) {

        return userDataService.create(userRequest);
    }

    @Override
    public UserResponse whoami(String userUid) {

        return userDataService.findByUuid(userUid);
    }

    @Override
    public UserResponse update(String userUid, UserRequest userRequest) {

        return userDataService.update(userUid, userRequest);
    }

    @Override
    public UserResponse findBySecretKey(String secretKey) {

        return userDataService.findBySecretKey(secretKey);
    }

    @Override
    public void delete(String userUid) {

        userDataService.delete(userUid);
    }
}
