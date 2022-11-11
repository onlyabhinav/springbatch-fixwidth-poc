package com.onlyabhinav.batchfixwidth.mixformat.writers;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;

import com.onlyabhinav.batchfixwidth.mixformat.domain.ARecord;
import com.onlyabhinav.batchfixwidth.mixformat.domain.BRecord;
import com.onlyabhinav.batchfixwidth.mixformat.domain.MixRecord;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BRecordWriter<MixRecord> extends AbstractWriter  implements ItemWriter<MixRecord> {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static String INSERT_SQL = "INSERT INTO spring_batch_1 (id, first_name, last_name, birth_date) VALUES (:id, :firstName, :lastName, :birthdate)";

	@Override
	public void write(List<? extends MixRecord> items) throws Exception {

		log.info("Load Mode: getIsBulkMode={}",mixFormatConfig.getIsBulkMode());
		if(mixFormatConfig.getIsBulkMode()) {
			 saveToDBBulk((List<BRecord>) items);
		}else {
			for (MixRecord item : items) {
				//log.debug(item.toString());
				saveToDB((BRecord) item);
			}	
		}
	}

	public int saveToDB(BRecord a) {
		return namedParameterJdbcTemplate.update(INSERT_SQL, new BeanPropertySqlParameterSource(a));
	}

	public int[] saveToDBBulk(List<BRecord> a) {
		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(a);
		return namedParameterJdbcTemplate.batchUpdate(INSERT_SQL, batch);
	}

	
	

}
