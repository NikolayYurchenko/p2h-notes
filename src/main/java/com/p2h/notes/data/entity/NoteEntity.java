package com.p2h.notes.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notes")
public class NoteEntity {

    @Id
    private String id;

    private UUID uuid;

    private String userUid; // Because author of the note can be Anonymous

    private String content;

    private Long likes;

    private boolean authorIsAnonymous = false;

    @CreatedDate
    private LocalDateTime createdAt;
}
