package com.sparta.memo.dto;

import com.sparta.memo.entity.Memo;
import com.sparta.memo.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemoResponseDto {
    private Long memoid;
    private String memotitle;
    private String memocontents;
    private User user;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public MemoResponseDto(Memo memo) {
        this.memoid = memo.getMemoid();
        this.memotitle = memo.getMemotitle();
        this.memocontents = memo.getMemocontents();
        this.user = memo.getUser();
        this.createdAt = memo.getCreatedAt();
        this.modifiedAt = memo.getModifiedAt();
    }


}