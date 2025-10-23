package com.ssg.board.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostVO {

    private Long postId;
    private String title;
    private String content;
    private String writer;
    private String passphrase;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
