package com.p2h.notes.exception.global;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GlobalErrorResponse {

    private Long timestamp;

    private String msg;

    private String errorMsg;

    private String code;
}
