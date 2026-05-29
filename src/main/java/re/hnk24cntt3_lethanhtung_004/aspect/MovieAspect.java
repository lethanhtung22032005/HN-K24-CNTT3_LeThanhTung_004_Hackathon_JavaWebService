package re.hnk24cntt3_lethanhtung_004.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MovieAspect {

    @Before("execution(* re.hnk24cntt3_lethanhtung_004.service.MovieService.*(..)) " +
            "&& (execution(* *save*(..)) || execution(* *update*(..)))")
    public void logMovieOperation(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("=== AOP LOG: Method {} được gọi ===", methodName);
    }
}