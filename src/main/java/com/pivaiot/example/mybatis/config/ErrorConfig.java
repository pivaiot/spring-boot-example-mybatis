package com.pivaiot.example.mybatis.config;

import com.pivaiot.common.lang.exception.CommonException;
import com.pivaiot.common.lang.http.ResponseJson;
import com.pivaiot.example.mybatis.exception.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;


/**
 * 页面和API返回格式配置
 */
@Controller
@Slf4j
public class ErrorConfig extends BasicErrorController {

    private static final String ERROR_ATTRIBUTE = DefaultErrorAttributes.class.getName() + ".ERROR";

    private Environment environment;

    public ErrorConfig(ServerProperties serverProperties, Environment environment) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
        this.environment = environment;
    }

    /**
     * HTML 统一错误页面
     */
    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        WebRequest webRequest = new ServletWebRequest(request);
        Throwable throwable = getError(webRequest);
        LOGGER.debug("Page Error found: {}", request.getRequestURI());

        Map<String, Object> model = Collections.unmodifiableMap(this.getErrorAttributes(request,
            this.isIncludeStackTrace(request, MediaType.TEXT_HTML)));

        String errorViewName = "400";
        if (throwable instanceof CommonException) {
            CommonException ex = (CommonException) throwable;
            if (ErrorCodeEnum.NOT_FOUND.equals(ex.getErrorCode())) {
                errorViewName = "404";
                return new ModelAndView("error/" + errorViewName, model, HttpStatus.NOT_FOUND);
            }
        }

        return new ModelAndView("error/" + errorViewName, model, HttpStatus.BAD_REQUEST);
    }

    /**
     * API 统一错误 JSON 格式
     */
    @Override
    @SuppressWarnings("unchecked")
    public ResponseEntity error(HttpServletRequest request) {
        WebRequest webRequest = new ServletWebRequest(request);
        Throwable throwable = getError(webRequest);

        if (throwable != null) {
            LOGGER.warn("Api error found: {}", request.getRequestURI(), throwable);

            if (throwable instanceof CommonException) {
                CommonException ex = (CommonException) throwable;
                return new ResponseEntity<>(ResponseJson.err(getAppName(), ex), HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(ResponseJson.err(getAppName(), new CommonException(throwable,
            ErrorCodeEnum.UNKNOWN)),
            HttpStatus.BAD_REQUEST);
    }

    public Throwable getError(WebRequest webRequest) {
        Throwable exception = getAttribute(webRequest, ERROR_ATTRIBUTE);
        if (exception == null) {
            exception = getAttribute(webRequest, "javax.servlet.error.exception");
        }
        return exception;
    }

    @SuppressWarnings("unchecked")
    private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
        return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }

    private String getAppName() {
        return environment.getProperty("spring.application.name").toUpperCase();
    }
}
