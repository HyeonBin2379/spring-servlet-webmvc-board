package com.ssg.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private Long postId;
    private String title;
    private String content;
    private String writer;
    private String passphrase;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
