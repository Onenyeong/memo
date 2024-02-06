package com.sparta.memo.entity;

import com.sparta.memo.dto.MemoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "memo") // 매핑할 테이블의 이름을 지정
@RequiredArgsConstructor
public class Memo extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memoid;

    @Column(nullable = false)
    private String memotitle;

    @Column(nullable = false, length = 500)
    private String memocontents;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Memo(MemoRequestDto requestDto, User user) {
        this.memotitle = requestDto.getMemotitle();
        this.memocontents = requestDto.getMemocontents();
//        this.user = user;
    }


    public void update(MemoRequestDto requestDto) {
        this.memotitle = requestDto.getMemotitle();
        this.memocontents = requestDto.getMemocontents();
        this.user = user;
    }


}