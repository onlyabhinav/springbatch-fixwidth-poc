package com.onlyabhinav.batchfixwidth.mixformat.writers;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onlyabhinav.batchfixwidth.mixformat.domain.MixRecord;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OneRecordWriter<MixRecord> implements ItemWriter<MixRecord> {

	@Override
	public void write(List<? extends MixRecord> items) throws Exception {
		// TODO Auto-generated method stub

		for (MixRecord item : items) {
			log.info(item.toString());
		}

	}

}
