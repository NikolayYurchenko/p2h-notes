package com.p2h.notes.service;

import com.p2h.notes.data.redis.UserLikesRepository;
import com.p2h.notes.data.service.NoteDataService;
import com.p2h.notes.model.NoteRequest;
import com.p2h.notes.model.NoteResponse;
import com.p2h.notes.service.contract.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteDataService noteDataService;

    private final UserLikesRepository userLikesRepository;

    @Override
    public NoteResponse create(NoteRequest noteRequest) {

        return noteDataService.create(noteRequest) ;
    }

    @Override
    public NoteResponse findByUuid(String noteUid) {

        return noteDataService.findByUuid(noteUid);
    }

    @Override
    public List<NoteResponse> findAll() {

        return noteDataService.findAll();
    }

    @Override
    public List<NoteResponse> findAllByUserUid(String userUid) {

        return noteDataService.findAllByUserUid(userUid);
    }

    @Override
    public void addLike(String userUid, String noteUid) {

        boolean isAlreadySetLike = userLikesRepository.isUserLikeThisNote(userUid, noteUid);

        if(!isAlreadySetLike) {

            noteDataService.addLike(noteUid);

            userLikesRepository.rememberLikeForNote(userUid, noteUid);
        }
    }

    @Override
    public void removeLike(String userUid, String noteUid) {

        boolean isAlreadySetLike = userLikesRepository.isUserLikeThisNote(userUid, noteUid);

        if(isAlreadySetLike) {

            noteDataService.removeLike(noteUid);

            userLikesRepository.forgetLikeForNote(userUid, noteUid);
        }
    }

    @Override
    public NoteResponse update(String userUid, String noteUid, NoteRequest noteRequest) {

        return noteDataService.update(userUid, noteUid, noteRequest);
    }

    @Override
    public void delete(String noteUid) {

        noteDataService.delete(noteUid);
    }
}
