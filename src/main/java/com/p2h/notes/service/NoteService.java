package com.p2h.notes.service;

import com.p2h.notes.model.NoteRequest;
import com.p2h.notes.model.NoteResponse;

import java.util.List;

public interface NoteService {

    /**
     * Create note
     * @param noteRequest
     * @return
     */
    NoteResponse create(NoteRequest noteRequest);

    /**
     * Find note by uuid
     * @param noteUid
     * @return
     */
    NoteResponse findByUuid(String noteUid);

    /**
     * Find all notes
     * @return
     */
    List<NoteResponse> findAll();

    /**
     * Find all note by user
     * @param userUid
     * @return
     */
    List<NoteResponse> findAllByUserUid(String userUid);

    /**
     * Add like for note
     * @param noteUid
     */
    void addLike(String noteUid);

    /**
     * Remove like for note
     * @param noteUid
     */
    void removeLike(String noteUid);

    /**
     * Update note by uuid
     * @param userUid
     * @param noteUid
     * @param noteRequest
     * @return
     */
    NoteResponse update(String userUid, String noteUid, NoteRequest noteRequest);

    /**
     * Delete note by uuid
     * @param noteUid
     */
    void delete(String noteUid);
}
