package com.p2h.notes.service;

import com.p2h.notes.model.UserAuthResponse;
import com.p2h.notes.model.UserRequest;
import com.p2h.notes.model.UserResponse;

public interface UserService {

    /**
     * Create user
     * @param userRequest
     * @return
     */
    UserResponse create(UserRequest userRequest);

    /**
     * Register user
     * @param userRequest
     * @return
     */
    UserAuthResponse register(UserRequest userRequest);

    /**
     * Login user by secret key
     * @param secretKey
     * @return
     */
    UserAuthResponse login(String secretKey);

    /**
     * Find information about user by uuid
     * @param userUid
     * @return
     */
    UserResponse whoami(String userUid);

    /**
     * Update user by uuid
     * @param userUid
     * @param userRequest
     * @return
     */
    UserResponse update(String userUid, UserRequest userRequest);

    /**
     * Find user by secret key
     * @param secretKey
     * @return
     */
    UserResponse findBySecretKey(String secretKey);

    /**
     * Delete user by uuid
     * @param userUid
     */
    void delete(String userUid);
}
