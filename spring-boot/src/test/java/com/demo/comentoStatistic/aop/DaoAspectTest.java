package com.demo.comentoStatistic.aop;

import com.demo.comentoStatistic.exception.DaoAccessException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Mockito를 가능하게 하는 어노테이션 추가
// 자동적으로 mock들을 초기화하고 mock들을 우리의@InjectMocks 어노테이션된 테스팅 유닛에 삽입한다.
@ExtendWith(MockitoExtension.class)
class DaoAspectTest {
    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    @InjectMocks
    private DaoAspect daoAspect;

    @DisplayName("RuntimeException 발생시 DaoAccessException이 제대로 던져 지는지 확인")
    @Test
    void whenExecuteJoinPoint_thenDaoAccessExceptionIsThrown() throws Throwable {
        when(proceedingJoinPoint.proceed()).thenThrow(new RuntimeException());

        assertThrows(DaoAccessException.class, ()-> daoAspect.daoAround(proceedingJoinPoint));
        verify(proceedingJoinPoint, times(1)).proceed();
    }

}