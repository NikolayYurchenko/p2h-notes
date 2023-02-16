package com.p2h.notes.data.service;

import com.p2h.notes.data.entity.NoteEntity;
import com.p2h.notes.data.repository.NoteRepository;
import com.p2h.notes.model.NoteRequest;
import com.p2h.notes.model.NoteResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoteDataService {


    private final NoteRepository noteRepository;

    private final UserDataService userDataService;

    /**
     * Create note
     * @param noteRequest
     * @return
     */
    @Transactional
    public NoteResponse create(NoteRequest noteRequest) {

        log.debug("Creating note from data:[{}]", noteRequest);

        NoteEntity noteEntity = NoteEntity.builder()
                .uuid(UUID.randomUUID())
                .userUid(noteRequest.getUserUid() != null ? noteRequest.getUserUid() : "")
                .content(noteRequest.getContent())
                .likes(0L)
                .authorIsAnonymous(noteRequest.isAuthorIsAnonymous())
                .createdAt(LocalDateTime.now())
                .build();

        if(noteRequest.getUserUid() != null && !noteRequest.getUserUid().isBlank()) {

            userDataService.addNoteUid(noteRequest.getUserUid(), noteEntity.getUuid().toString());
        }

        return NoteResponse.instance(noteRepository.save(noteEntity));
    }

    /**
     * Find note by uuid
     * @param noteUid
     * @return
     */
    public NoteResponse findByUuid(String noteUid) {

        log.debug("Searching note by uuid:[{}]", noteUid);

        NoteEntity noteEntity = noteRepository.findByUuid(UUID.fromString(noteUid))
                .orElseThrow(() -> new EntityNotFoundException("Not found note by uuid:["+ noteUid +"]"));

        log.debug("...found:[{}]", noteEntity);

        return NoteResponse.instance(noteEntity);
    }

    /**
     * Find all notes
     * @return
     */
    public List<NoteResponse> findAll() {

        log.debug("Searching for all notes");

        return NoteResponse.instance(noteRepository.findAllByOrderByCreatedAtDesc());
    }

    /**
     * Find all notes by specific user
     * @param userUid
     * @return
     */
    public List<NoteResponse> findAllByUserUid(String userUid) {

        log.debug("Searching notes by user uuid:[{}]", userUid);

        List<NoteEntity> noteEntities = noteRepository.findAllByUserUid(userUid);

        log.debug("...found:{}", noteEntities);

        return NoteResponse.instance(noteEntities);
    }

    /**
     * Add like for note
     * @param noteUid
     */
    public void addLike(String noteUid) {

        log.debug("Adding like for note by uuid:[{}]", noteUid);

        NoteEntity noteEntity = noteRepository.findByUuid(UUID.fromString(noteUid))
                .orElseThrow(() -> new EntityNotFoundException("Not found note by uuid:["+ noteUid +"]"));

        noteEntity.setLikes(noteEntity.getLikes() + 1);

        noteRepository.save(noteEntity);
    }

    /**
     * Remove like from note
     * @param noteUid
     */
    public void removeLike(String noteUid) {

        log.debug("Adding like for note by uuid:[{}]", noteUid);

        NoteEntity noteEntity = noteRepository.findByUuid(UUID.fromString(noteUid))
                .orElseThrow(() -> new EntityNotFoundException("Not found note by uuid:["+ noteUid +"]"));

        if(noteEntity.getLikes() >= 1) {

            noteEntity.setLikes(noteEntity.getLikes() - 1);
        }

        noteRepository.save(noteEntity);
    }

    /**
     * Update note by uuid
     * @param userUid
     * @param noteUid
     * @param noteRequest
     * @return
     */
    @SneakyThrows
    public NoteResponse update(String userUid, String noteUid, NoteRequest noteRequest) {

        log.debug("Updating note by uuid:[{}]", noteUid);

        NoteEntity noteEntity = noteRepository.findByUuid(UUID.fromString(noteUid))
                .orElseThrow(() -> new EntityNotFoundException("Not found note by uuid:["+ noteUid +"]"));

        if(!noteEntity.getUserUid().isBlank() && !noteEntity.getUserUid().equals(userUid)) {

            throw new IllegalAccessException("User:{"+ userUid +"} cant update this note:{"+ noteUid +"}");
        }

        noteEntity.setContent(noteRequest.getContent());

        return NoteResponse.instance(noteRepository.save(noteEntity));

    }

    /**
     * Delete note by uuid
     * @param noteUid
     */
    public void delete(String noteUid) {

        log.debug("Deleting note by uuid:[{}]", noteUid);

        NoteEntity noteEntity = noteRepository.findByUuid(UUID.fromString(noteUid))
                .orElseThrow(() -> new EntityNotFoundException("Not found note by uuid:["+ noteUid +"]"));

        if(noteEntity.getUserUid() != null && !noteEntity.getUserUid().isBlank()) {

            userDataService.removeNoteUid(noteEntity.getUserUid(), noteEntity.getUuid().toString());
        }

        noteRepository.deleteByUuid(UUID.fromString(noteUid));
    }
}
