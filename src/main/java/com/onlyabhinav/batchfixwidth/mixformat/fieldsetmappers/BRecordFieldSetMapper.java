package com.onlyabhinav.batchfixwidth.mixformat.fieldsetmappers;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.onlyabhinav.batchfixwidth.mixformat.domain.BRecord;
import com.onlyabhinav.batchfixwidth.mixformat.domain.MixRecord;


public class BRecordFieldSetMapper implements FieldSetMapper<MixRecord> {

	@Override
	public MixRecord mapFieldSet(FieldSet fieldSet) throws BindException {
		return new BRecord(fieldSet.readString("id"),
				fieldSet.readString("firstName"),
				fieldSet.readString("lastName"),
				fieldSet.readString("birthdate"));
	}
}
