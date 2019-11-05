package by.epam.javatraining.beseda.webproject.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.AUTHORIZATION_LOGGER;

@Aspect
@Component
public class LoggingAspect {

    private static Logger authLogger = Logger.getLogger(AUTHORIZATION_LOGGER);

    @Pointcut("execution(* by.epam.javatraining.beseda.webproject.controller.security.AuthSuccessHandler.onAuthenticationSuccess(..))")
    public void callWhenUserAuthorized() {
    }

    @After("callWhenUserAuthorized()")
    public void loggedIn(JoinPoint joinPoint) {
        authLogger.trace(SecurityContextHolder.getContext().getAuthentication().getName() + "  logged IN");
    }


    @Pointcut("execution(* by.epam.javatraining.beseda.webproject.controller.security.SuccessLogoutHandler.logout(..))")
    public void callWhenUserLoggedOut(){}

    @After("callWhenUserLoggedOut()")
    public void loggedOut(JoinPoint joinPoint) {
        authLogger.trace(SecurityContextHolder.getContext().getAuthentication().getName() + "  logged OUT");
    }

}
