package com.p2h.notes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteRequest {

    @NotBlank
    @NotNull
    private String content;

    private String userUid;

    @NotNull
    private boolean authorIsAnonymous;
}
