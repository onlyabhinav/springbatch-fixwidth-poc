package com.onlyabhinav.batchfixwidth.mixformat.fieldsetmappers;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.onlyabhinav.batchfixwidth.mixformat.domain.OneRecord;


public class OneRecordFieldSetMapper implements FieldSetMapper<OneRecord> {

	@Override
	public OneRecord mapFieldSet(FieldSet fieldSet) throws BindException {
		return new OneRecord(fieldSet.readString("id"),
				fieldSet.readString("firstName"),
				fieldSet.readString("lastName"),
				fieldSet.readString("birthdate"));
	}
}
