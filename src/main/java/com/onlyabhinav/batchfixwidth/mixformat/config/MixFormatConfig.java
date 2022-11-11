package com.onlyabhinav.batchfixwidth.mixformat.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Configuration
public class MixFormatConfig {
	
    @Value("${app.enable.bulk.mode}")
    private Boolean isBulkMode;

    @PostConstruct
    public void propsLoaded() {
        log.info("Properties Loaded {}", this);
    }

}
