package com.onlyabhinav.batchfixwidth.mixformat.classifier;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.classify.Classifier;
import org.springframework.stereotype.Component;

import com.onlyabhinav.batchfixwidth.mixformat.domain.ARecord;
import com.onlyabhinav.batchfixwidth.mixformat.domain.BRecord;
import com.onlyabhinav.batchfixwidth.mixformat.domain.MixRecord;
import com.onlyabhinav.batchfixwidth.mixformat.domain.OneRecord;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Component
public class MixRecordClassifier implements Classifier<MixRecord, ItemWriter<MixRecord>> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ARecordWriter aRecordWriter;

	@Autowired
	private BRecordWriter bRecordWriter;

	@Autowired
	private OneRecordWriter oneRecordWriter;

	@Autowired
	private UnknownRecordWriter unknownRecordWriter;

//    public MixRecordClassifier(ItemWriter evenItemWriter, ItemWriter oddItemWriter) {
//        this.aRecordWriter = evenItemWriter;
//        this.bRecordWriter = oddItemWriter;
//    }

	@Override
	public ItemWriter<MixRecord> classify(MixRecord mixRecord) {

		log.info("Inside MixRecordClassifier :: classify -  mixRecord is of type={}", mixRecord.getClass());

		if (mixRecord instanceof ARecord) {
			log.info("Writer: A");
			return aRecordWriter;
		}

		if (mixRecord instanceof BRecord) {
			log.info("Writer: B");
			return bRecordWriter;
		}

		if (mixRecord instanceof OneRecord) {
			log.info("Writer: 1");
			return oneRecordWriter;
		}

		else {
			log.info("Writer: UNKNOWN");
			return unknownRecordWriter;
		}

	}
}