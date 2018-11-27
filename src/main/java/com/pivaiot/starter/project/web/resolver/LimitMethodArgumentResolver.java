package com.pivaiot.starter.project.web.resolver;

import com.pivaiot.common.lang.annotation.Limit;
import com.pivaiot.common.lang.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 对外接口中，所有分页 limit 最大统一限制为 100
 */
@Slf4j
@Component
public class LimitMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private static final Integer MAX_LIMIT = 100;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(Integer.class) &&
                parameter.hasParameterAnnotation(Limit.class);

    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {

        Limit limitAnnotation = parameter.getParameterAnnotation(Limit.class);
        String paramName = limitAnnotation.name();
        Integer defaultValue = Integer.parseInt(limitAnnotation.defaultValue());
        defaultValue = defaultValue > MAX_LIMIT ? MAX_LIMIT : defaultValue;

        String limitValue = webRequest.getParameter(paramName);
        if (null == limitValue)
            return defaultValue;

        try {
            Integer limit = Integer.parseInt(limitValue);
            if (limit > MAX_LIMIT) {
                LOGGER.info("Request limit is {}: request ip is: {}",
                        limit, webRequest.getHeader(IpUtil.X_FORWARD_FOR));
            }
            return limit > defaultValue ? defaultValue: limit;
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
