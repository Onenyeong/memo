package com.sparta.memo.service;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import com.sparta.memo.entity.User;
import com.sparta.memo.entity.UserRoleEnum;
import com.sparta.memo.jwt.JwtUtil;
import com.sparta.memo.repository.MemoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemoService {

    private final MemoRepository memoRepository;
    private final JwtUtil jwtUtil;

    public MemoService(MemoRepository memoRepository, JwtUtil jwtUtil) {
        this.memoRepository = memoRepository;
        this.jwtUtil = jwtUtil;
    }

    public String temp() {
        String username = "test_user";
        UserRoleEnum userRoleEnum = UserRoleEnum.USER;
        return jwtUtil.createToken(username, userRoleEnum);
    }


    public MemoResponseDto createMemo(MemoRequestDto requestDto, User user) {
        // RequestDto -> Entity
        Memo memo = new Memo(requestDto,user);

        // DB 저장
        Memo saveMemo = memoRepository.save(memo);

        // Entity -> ResponseDto
        MemoResponseDto memoResponseDto = new MemoResponseDto(saveMemo);

        return memoResponseDto;
    }

    public Memo getMemo(Long memoid) {
        return findMemo(memoid);
    }

    public List<MemoResponseDto> getMemoList() {
        // DB 조회
        return memoRepository.findAllByOrderByModifiedAtDesc().stream().map(MemoResponseDto::new).toList();
    }

    @Transactional
    public Memo updateMemo(Long memoid, MemoRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = findMemo(memoid);

        // memo 내용 수정
        memo.update(requestDto);
        return memo;
    }

    public Long deleteMemo(Long memoid) {
        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = findMemo(memoid);

        // memo 삭제
        memoRepository.delete(memo);

        return memoid;
    }

    private Memo findMemo(Long memoid) {
        return memoRepository.findById(memoid).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }

}