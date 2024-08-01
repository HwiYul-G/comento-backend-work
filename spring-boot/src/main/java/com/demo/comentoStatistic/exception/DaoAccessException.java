package com.demo.comentoStatistic.exception;

public class DaoAccessException extends RuntimeException {

    public DaoAccessException() {
        super("서버 내부에서 데이터베이스 접근 오류가 발생했습니다.");
    }

}
