package com.p2h.notes.model;

import com.p2h.notes.data.entity.NoteEntity;
import lombok.*;

import java.util.List;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class NoteResponse {

    private String uuid;

    private String userUid;

    private String content;

    private Long likes;

    private boolean isAuthorIsAnonymous = false;

    private String createdAt;

    public static NoteResponse instance(NoteEntity noteEntity) {

        return NoteResponse.builder()
                .uuid(noteEntity.getUuid().toString())
                .userUid(noteEntity.getUserUid())
                .content(noteEntity.getContent())
                .likes(noteEntity.getLikes())
                .isAuthorIsAnonymous(noteEntity.isAuthorIsAnonymous())
                .createdAt(noteEntity.getCreatedAt().toString())
                .build();
    }

    public static List<NoteResponse> instance(List<NoteEntity> noteEntities) {

        return noteEntities.stream()
                .map(NoteResponse::instance)
                .toList();
    }
}
