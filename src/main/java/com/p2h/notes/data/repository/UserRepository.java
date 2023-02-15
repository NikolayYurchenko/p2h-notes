package com.p2h.notes.data.repository;

import com.p2h.notes.data.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends MongoRepository<UserEntity, String> {

    /**
     * Find user by secret key
     * @param secretKey
     * @return
     */
  Optional<UserEntity> findBySecretKey(String secretKey);

    /**
     * Find user by uuid
     * @param userUid
     * @return
     */
  Optional<UserEntity> findByUuid(UUID userUid);

    /**
     * Delete user by uuid
     * @param userUid
     */
  void deleteByUuid(UUID userUid);
}
