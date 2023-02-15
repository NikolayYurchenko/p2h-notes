package com.p2h.notes.controller;

import com.p2h.notes.model.*;
import com.p2h.notes.service.NoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Slf4j
@RestController
@Api(tags = "Notes API")
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/create")
    @SneakyThrows
    @ApiOperation("Create note")
    public NoteResponse create(@RequestBody @Valid NoteRequest request) {

        log.info("Request for create note from data:[{}]", request);

        return noteService.create(request);
    }

    @GetMapping
    @ApiOperation("Find all notes")
    public List<NoteResponse> findAll(@AuthenticationPrincipal UserPrincipal principal) {

        log.info("Request for find all notes. User:[{}]", principal.getUserUid());

        return noteService.findAll();
    }

    @GetMapping("/{noteUid}")
    @ApiOperation("Find note by uuid")
    public NoteResponse findByUuid(@PathVariable @NotBlank String noteUid, @AuthenticationPrincipal UserPrincipal principal) {

        log.info("Request for find note by uuid:[{}]. User:[{}]", noteUid, principal.getUserUid());

        return noteService.findByUuid(noteUid);
    }

    @GetMapping("/by-user/{userUid}")
    @ApiOperation("Find note by uuid")
    public List<NoteResponse> findAllByUserUid(@PathVariable @NotBlank String userUid, @AuthenticationPrincipal UserPrincipal principal) {

        log.info("Request for find all notes by user uuid:[{}]. User:[{}]", userUid, principal.getUserUid());

        return noteService.findAllByUserUid(userUid);
    }

    @PutMapping("/add-like/{noteUid}")
    @ApiOperation("Add like for note")
    public void addLike(@PathVariable @NotBlank String noteUid, @AuthenticationPrincipal UserPrincipal principal ) {

        log.info("Request for add like for note by uuid:[{}]", noteUid);

        noteService.addLike(noteUid);
    }

    @PutMapping("/remove-like/{noteUid}")
    @ApiOperation("Remove like for note")
    public void removeLike(@PathVariable @NotBlank String noteUid, @AuthenticationPrincipal UserPrincipal principal ) {

        log.info("Request for remove like for note by uuid:[{}]", noteUid);

        noteService.removeLike(noteUid);
    }

    @DeleteMapping("/{noteUid}")
    @ApiOperation("Delete note")
    public void delete(@PathVariable @NotBlank String noteUid) {

        noteService.delete(noteUid);
    }
}
