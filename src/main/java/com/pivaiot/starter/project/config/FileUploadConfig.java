package com.pivaiot.starter.project.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "upload")
public class FileUploadConfig {
    /**
     * Directory for storing upload files
     */
    @Getter @Setter
    private String dir = "./files";

    @Getter @Setter
    private String tmpDir = "./files/tmp";

    @Getter @Setter
    private Long maxFileSize = 10 * 1024L * 1024L; // 10MB
}
