package com.onlyabhinav.batchfixwidth.mixformat.fieldsetmappers;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.onlyabhinav.batchfixwidth.mixformat.domain.ARecord;


public class ARecordFieldSetMapper implements FieldSetMapper<ARecord> {

	@Override
	public ARecord mapFieldSet(FieldSet fieldSet) throws BindException {
		return new ARecord(fieldSet.readString("id"),
				fieldSet.readString("firstName"),
				fieldSet.readString("lastName"),
				fieldSet.readString("birthdate"));
	}
}
