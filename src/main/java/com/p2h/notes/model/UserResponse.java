package com.p2h.notes.model;

import lombok.*;

import java.util.List;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String uuid;

    private String name;

    private List<String> noteIds; //TODO: return NoteDto or ... ?

    private String secretKey;

    private String createdAt;

    public static UserResponse instance(String uuid,
                                        String name,
                                        List<String> noteIds,
                                        String secretKey,
                                        String createdAt) {

        return UserResponse.builder()
                .uuid(uuid)
                .name(name)
                .noteIds(noteIds)
                .secretKey(secretKey)
                .createdAt(createdAt)
                .build();
    }
}
