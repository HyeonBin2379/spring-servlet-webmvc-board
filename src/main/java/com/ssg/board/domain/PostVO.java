package com.ssg.board.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import lombok.ToString;

@Getter
@Builder
@ToString
public class PostVO {

    private Long postId;
    private String title;
    private String content;
    private String writer;
    private String passphrase;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
