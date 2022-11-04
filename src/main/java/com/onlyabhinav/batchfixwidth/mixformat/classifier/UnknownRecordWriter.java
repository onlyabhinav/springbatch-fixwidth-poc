package com.onlyabhinav.batchfixwidth.mixformat.classifier;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.onlyabhinav.batchfixwidth.mixformat.domain.MixRecord;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class UnknownRecordWriter implements ItemWriter<MixRecord> {

	@Override
	public void write(List<? extends MixRecord> items) throws Exception {
		// TODO Auto-generated method stub
		
		
			for (MixRecord item : items) {
				log.info(item.toString());
			}
		
	}
	
	

}
