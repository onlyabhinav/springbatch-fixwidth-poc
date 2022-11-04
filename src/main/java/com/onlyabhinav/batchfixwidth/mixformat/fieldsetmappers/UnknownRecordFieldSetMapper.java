package com.onlyabhinav.batchfixwidth.mixformat.fieldsetmappers;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.onlyabhinav.batchfixwidth.mixformat.domain.OneRecord;
import com.onlyabhinav.batchfixwidth.mixformat.domain.UnknownRecord;


public class UnknownRecordFieldSetMapper implements FieldSetMapper<UnknownRecord> {

	@Override
	public UnknownRecord mapFieldSet(FieldSet fieldSet) throws BindException {
		return new UnknownRecord(fieldSet.readString("lineString"));
	}
}
