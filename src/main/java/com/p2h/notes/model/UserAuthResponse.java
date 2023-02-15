package com.p2h.notes.model;

import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthResponse {

    private String token;

    private String name;

    private String secretKey;


    public static UserAuthResponse instance(String token, String name, String secretKey) {

        return UserAuthResponse.builder()
                .token(token)
                .name(name)
                .secretKey(secretKey)
                .build();
    }
}
