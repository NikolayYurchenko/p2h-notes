package com.p2h.notes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String secretKey;
}
