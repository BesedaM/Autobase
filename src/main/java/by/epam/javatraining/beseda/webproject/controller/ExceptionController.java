package by.epam.javatraining.beseda.webproject.controller;

import by.epam.javatraining.beseda.webproject.exception.TruckingCompanyProjectException;
import org.apache.log4j.Logger;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

@ControllerAdvice
public class ExceptionController {

    private static Logger logger = Logger.getLogger(ERROR_LOGGER);

    @ExceptionHandler(value = RuntimeException.class)
    public ModelAndView  runtimeErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
        if (AnnotationUtils.findAnnotation
                (e.getClass(), ResponseStatus.class) != null)
            throw e;

        // Otherwise setup and send the user to a default error-view.
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.setViewName("generic_error_page");
        logger.error(e);
        return mav;
    }

    @ExceptionHandler(value = TruckingCompanyProjectException.class)
    public ModelAndView  defaultErrorHandler(HttpServletRequest req, Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.setViewName("standard_error_page");
        logger.error(e);
        return mav;
    }

}