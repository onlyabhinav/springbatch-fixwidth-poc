package com.onlyabhinav.batchfixwidth.mixformat.fieldsetmappers;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.onlyabhinav.batchfixwidth.mixformat.domain.MixRecord;
import com.onlyabhinav.batchfixwidth.mixformat.domain.OneRecord;
import com.onlyabhinav.batchfixwidth.mixformat.domain.UnknownRecord;
import com.onlyabhinav.batchfixwidth.mixformat.domain.UnknownRecord2;


public class UnknownRecordFieldSetMapper implements FieldSetMapper<MixRecord> {

	@Override
	public MixRecord mapFieldSet(FieldSet fieldSet) throws BindException {
		//return new UnknownRecord(fieldSet.readString("lineString"));
		
		return new UnknownRecord2(fieldSet.readString("id"),
				fieldSet.readString("firstName"),
				fieldSet.readString("lastName"),
				fieldSet.readString("birthdate"));
	
	}
}
