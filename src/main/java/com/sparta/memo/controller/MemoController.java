package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import com.sparta.memo.security.UserDetailsImpl;
import com.sparta.memo.service.MemoService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //  객체를 bean으로 등록하는 annotation
@RequestMapping("/api")
public class MemoController {
    private final MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoService.createMemo(requestDto,userDetails.getUser());
    }
    @GetMapping("/memos/memoid")
    public Memo getMemo(@PathVariable Long memoid){
        return memoService.getMemo(memoid);
    }
    @GetMapping("/memos")
    public List<MemoResponseDto> getMemoList() {
        return memoService.getMemoList();
    }

    @PutMapping("/memos/{memoid}")
    public Memo updateMemo(@PathVariable Long memoid, @RequestBody MemoRequestDto requestDto) {
        return memoService.updateMemo(memoid, requestDto);
    }

    @DeleteMapping("/memos/{memoid}")
    public Long deleteMemo(@PathVariable Long memoid) {
        return memoService.deleteMemo(memoid);
    }

    @GetMapping("/test")
    public String temp() {
        return memoService.temp();
    }
}