package com.p2h.notes.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class UserEntity {

    @Id
    private String id;

    private UUID uuid;

    private String name;

    private List<String> noteIds;

    private String secretKey;

    private LocalDateTime createdAt;
}
