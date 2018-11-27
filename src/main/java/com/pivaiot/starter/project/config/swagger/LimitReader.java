package com.pivaiot.starter.project.config.swagger;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Optional;
import com.pivaiot.common.lang.annotation.Limit;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 1000)
public class LimitReader implements ParameterBuilderPlugin {
    private TypeResolver resolver;

    public LimitReader(TypeResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void apply(ParameterContext parameterContext) {
        ResolvedMethodParameter methodParameter = parameterContext.resolvedMethodParameter();
        Optional<Limit> requestParam = methodParameter.findAnnotation(Limit.class);
        if (requestParam.isPresent()) {
            parameterContext.parameterBuilder()
                    .parameterType("query")
                    .name(requestParam.get().name())
                    .defaultValue(requestParam.get().defaultValue())
                    .required(requestParam.get().required())
                    .type(resolver.resolve(Integer.class));
        }
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return true;
    }
}
