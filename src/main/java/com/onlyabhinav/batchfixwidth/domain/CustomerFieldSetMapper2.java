package com.onlyabhinav.batchfixwidth.domain;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;


public class CustomerFieldSetMapper2 implements FieldSetMapper<Customer2> {

	@Override
	public Customer2 mapFieldSet(FieldSet fieldSet) throws BindException {
		return new Customer2(fieldSet.readLong("id"),
				fieldSet.readString("firstName"),
				fieldSet.readString("lastName"),
				fieldSet.readString("birthdate"));
	}
}
