package com.p2h.notes.service;

import com.p2h.notes.config.security.JwtResolver;
import com.p2h.notes.data.service.UserDataService;
import com.p2h.notes.model.UserAuthResponse;
import com.p2h.notes.model.UserRequest;
import com.p2h.notes.model.UserResponse;
import com.p2h.notes.service.contract.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDataService userDataService;

    private final JwtResolver jwtResolver;

    @Override
    @Transactional
    public UserResponse create(UserRequest userRequest) {

        return userDataService.create(userRequest);
    }

    @Override
    public UserAuthResponse register(UserRequest userRequest) {

        UserResponse user = userDataService.create(userRequest);

        String token = jwtResolver.generateToken(user.getUuid());

        return UserAuthResponse.instance(token, user.getName(), user.getSecretKey());
    }

    @Override
    public UserAuthResponse login(String secretKey) {

        UserResponse user = userDataService.findBySecretKey(secretKey);

        String token = jwtResolver.generateToken(user.getUuid());

        return UserAuthResponse.instance(token, user.getName(), user.getSecretKey());
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
