package com.onlyabhinav.batchfixwidth.mixformat.writers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onlyabhinav.batchfixwidth.mixformat.config.MixFormatConfig;

@Component
public abstract class AbstractWriter {
	
	@Autowired
	public MixFormatConfig mixFormatConfig;
}
