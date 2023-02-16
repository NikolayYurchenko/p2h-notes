package com.p2h.notes.data.repository;

import com.p2h.notes.data.entity.NoteEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoteRepository extends MongoRepository<NoteEntity, String> {


    /**
     * Find note by uuid
     * @param noteUid
     * @return
     */
    Optional<NoteEntity> findByUuid(UUID noteUid);

    /**
     * Find all notes by specific user
     * @param userUid
     * @return
     */
    List<NoteEntity> findAllByUserUid(String userUid);

    /**
     * Find all notes by desc
     * @return
     */
    List<NoteEntity> findAllByOrderByCreatedAtDesc();

    /**
     * Delete note by uuid
     * @param noteUid
     */
    void deleteByUuid(UUID noteUid);
}
