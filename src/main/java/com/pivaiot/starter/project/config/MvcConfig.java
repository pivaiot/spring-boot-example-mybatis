package com.pivaiot.starter.project.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

/**
 * @author wangle<thisiswangle@gmail.com>
 */
@ControllerAdvice
public class MvcConfig {

    @ModelAttribute("cdn")
    public Cdn cdn(ResourceUrlProvider resourceUrlProvider) {
        return new Cdn(resourceUrlProvider);
    }


    public static class Cdn {
        private ResourceUrlProvider resourceUrlProvider;

        public Cdn(ResourceUrlProvider resourceUrlProvider) {
            this.resourceUrlProvider = resourceUrlProvider;
        }

        public String url(String path) {
            //return resourceUrlProvider.getForLookupPath(path);
            return path;
        }
    }
}
