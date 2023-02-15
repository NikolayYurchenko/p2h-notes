package com.p2h.notes.data.service;

import com.p2h.notes.data.entity.UserEntity;
import com.p2h.notes.data.repository.UserRepository;
import com.p2h.notes.model.UserRequest;
import com.p2h.notes.model.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDataService {

    private final UserRepository userRepository;

    /**
     * Create user
     * @param userRequest
     * @return
     */
    @Transactional
    public UserResponse create(UserRequest userRequest) {

        log.debug("Creating user from data:[{}]", userRequest);

        this.checkIsUserExistBySecretKey(userRequest.getSecretKey());

        UserEntity userEntity = UserEntity.builder()
                .uuid(UUID.randomUUID())
                .name(userRequest.getName())
                .noteIds(new ArrayList<>())
                .secretKey(userRequest.getSecretKey())
                .createdAt(LocalDateTime.now())
                .build();

        UserEntity savedUser = userRepository.save(userEntity);

        return UserResponse.instance(savedUser.getUuid().toString(),
                savedUser.getName(),
                savedUser.getNoteIds(),
                savedUser.getSecretKey(),
                savedUser.getCreatedAt().toString());
    }

    /**
     * Find user by uuid
     * @param userUid
     * @return
     */
    public UserResponse findByUuid(String userUid) {

        log.debug("Searching user by uuid:[{}]", userUid);

        UserEntity userEntity = userRepository.findByUuid(UUID.fromString(userUid))
                .orElseThrow(() -> new EntityNotFoundException("Not found user by uuid:["+ userUid +"]"));

        log.debug("...found:[{}]", userEntity);

        return UserResponse.instance(userEntity.getUuid().toString(),
                userEntity.getName(),
                userEntity.getNoteIds(),
                userEntity.getSecretKey(),
                userEntity.getCreatedAt().toString());
    }

    /**
     * Find user by secret key
     * @param secretKey
     * @return
     */
    public UserResponse findBySecretKey(String secretKey) {

        log.debug("Searching user by secretKey:[{}]", secretKey);

        UserEntity userEntity = userRepository.findBySecretKey(secretKey)
                .orElseThrow(() -> new EntityNotFoundException("Not found user by secretKey:["+ secretKey +"]"));

        log.debug("...found:[{}]", userEntity);

        return UserResponse.instance(userEntity.getUuid().toString(),
                userEntity.getName(),
                userEntity.getNoteIds(),
                userEntity.getSecretKey(),
                userEntity.getCreatedAt().toString());
    }

    /**
     * Update user by uuid
     * @param userUid
     * @param userRequest
     * @return
     */
    public UserResponse update(String userUid, UserRequest userRequest) {

        log.debug("Updating user by uuid:[{}]", userUid);

        UserEntity userEntity = userRepository.findByUuid(UUID.fromString(userUid))
                .orElseThrow(() -> new EntityNotFoundException("Not found user by uuid:["+ userUid +"]"));

        log.debug("...found:[{}]", userEntity);

        userEntity.setName(userRequest.getName());

        userEntity.setSecretKey(userRequest.getSecretKey());

        UserEntity updatedUser = userRepository.save(userEntity);

        return UserResponse.instance(updatedUser.getUuid().toString(),
                updatedUser.getName(),
                updatedUser.getNoteIds(),
                updatedUser.getSecretKey(),
                updatedUser.getCreatedAt().toString());
    }

    /**
     * Delete user by uuid
     * @param userUid
     */
    public void delete(String userUid) {

        log.debug("Deleting user by uuid:[{}]", userUid);

        userRepository.deleteByUuid(UUID.fromString(userUid));
    }

    public void checkIsUserExistBySecretKey(String secretKey) {

        log.info("Checking user with the same secret key:[{}]", secretKey);

        Optional<UserEntity> user = userRepository.findBySecretKey(secretKey);

        if(user.isPresent()) {

            throw new RuntimeException("User with secret key:["+ secretKey +"], already exist");
        }
    }
}
