package com.pivaiot.starter.project.config;

import com.pivaiot.common.lang.exception.CommonException;
import com.pivaiot.common.lang.http.ResponseJson;
import com.pivaiot.starter.project.exception.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
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

    @Autowired
    private Environment environment;

    private static final String ERROR_ATTRIBUTE = DefaultErrorAttributes.class.getName() + ".ERROR";
    public ErrorConfig(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }

    /**
     * HTML 统一错误页面
     */
    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Throwable throwable = getError(requestAttributes);
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
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Throwable throwable = getError(requestAttributes);
        LOGGER.warn("Api error found: {}", request.getRequestURI(), throwable);

        if (throwable instanceof CommonException) {
            CommonException ex = (CommonException) throwable;
            return new ResponseEntity<>(ResponseJson.err(getAppName(), ex), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(ResponseJson.err(getAppName(), new CommonException(throwable,
                    ErrorCodeEnum.UNKNOWN)),
                    HttpStatus.BAD_REQUEST);
        }
    }

    private Throwable getError(RequestAttributes requestAttributes) {

        return (Throwable) this.getAttribute(requestAttributes, ERROR_ATTRIBUTE);
    }

    private Object getAttribute(RequestAttributes requestAttributes, String name) {
        return requestAttributes.getAttribute(name, 0);
    }

    private String getAppName() {
        // FixMe: 获取应用名称失败
        return "unknown";
        //return environment.getProperty("spring.application.name").toUpperCase();
    }
}
