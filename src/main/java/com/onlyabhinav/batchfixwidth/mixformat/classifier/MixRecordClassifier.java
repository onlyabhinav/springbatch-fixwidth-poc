package com.onlyabhinav.batchfixwidth.mixformat.classifier;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.classify.Classifier;
import org.springframework.stereotype.Component;

import com.onlyabhinav.batchfixwidth.mixformat.domain.ARecord;
import com.onlyabhinav.batchfixwidth.mixformat.domain.BRecord;
import com.onlyabhinav.batchfixwidth.mixformat.domain.MixRecord;
import com.onlyabhinav.batchfixwidth.mixformat.domain.OneRecord;
import com.onlyabhinav.batchfixwidth.mixformat.writers.ARecordWriter;
import com.onlyabhinav.batchfixwidth.mixformat.writers.BRecordWriter;
import com.onlyabhinav.batchfixwidth.mixformat.writers.OneRecordWriter;
import com.onlyabhinav.batchfixwidth.mixformat.writers.UnknownRecordWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MixRecordClassifier implements Classifier<MixRecord, ItemWriter<MixRecord>> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ARecordWriter<MixRecord> aRecordWriter;

	@Autowired
	private BRecordWriter<MixRecord> bRecordWriter;

	@Autowired
	private OneRecordWriter<MixRecord> oneRecordWriter;

	@Autowired
	private UnknownRecordWriter<MixRecord> unknownRecordWriter;



	@Override
	public ItemWriter<MixRecord> classify(MixRecord mixRecord) {

		log.debug("Inside MixRecordClassifier :: classify -  mixRecord is of type={}", mixRecord.getClass());

		if (mixRecord instanceof ARecord) {
			log.debug("Writer: A");
			return aRecordWriter;
		}

		if (mixRecord instanceof BRecord) {
			log.debug("Writer: B");
			return bRecordWriter;
		}

		if (mixRecord instanceof OneRecord) {
			log.debug("Writer: 1");
			return oneRecordWriter;
		}

		else {
			log.debug("Writer: UNKNOWN");
			return unknownRecordWriter;
		}

	}
}