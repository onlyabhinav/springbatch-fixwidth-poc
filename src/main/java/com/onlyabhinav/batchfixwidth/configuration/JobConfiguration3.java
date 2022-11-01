/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.onlyabhinav.batchfixwidth.configuration;



import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.onlyabhinav.batchfixwidth.domain.Customer;
import com.onlyabhinav.batchfixwidth.domain.Customer2;
import com.onlyabhinav.batchfixwidth.domain.CustomerFieldSetMapper;
import com.onlyabhinav.batchfixwidth.domain.CustomerFieldSetMapper2;


@Configuration
public class JobConfiguration3 {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	
	@Bean(name = "customerItemReader2")
	public FlatFileItemReader<Customer2> customerItemReader() {
		FlatFileItemReader<Customer2> reader = new FlatFileItemReader<>();

		reader.setLinesToSkip(1);
		reader.setResource(new ClassPathResource("/data/customer-fixwd2.txt"));

		DefaultLineMapper<Customer2> customerLineMapper = new DefaultLineMapper<>();

		
		//FixedLengthTokenizer fixLenTokenizer
		
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		
		tokenizer.setNames(new String[] {"id", "firstName", "lastName", "birthdate"});

//		customerLineMapper.setLineTokenizer(tokenizer);
		customerLineMapper.setLineTokenizer(fixedLengthTokenizer());
		customerLineMapper.setFieldSetMapper(new CustomerFieldSetMapper2());
		customerLineMapper.afterPropertiesSet();

		reader.setLineMapper(customerLineMapper);

		return reader;
	}
	
	
    public FixedLengthTokenizer fixedLengthTokenizer() {
            FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
    
            tokenizer.setNames("id", "firstName", "lastName", "birthdate");
            tokenizer.setColumns(new Range(1,5),
                                 new Range(6,17),
                                 new Range(18,29),
                                 new Range(30));
            return tokenizer;
    }

	@Bean(name="customerItemWriter2")
	public ItemWriter<Customer2> customerItemWriter2() {
		return items -> {
			for (Customer2 item : items) {
				System.out.println(item.toString());
			}
		};
	}
	

	@Bean(name="step2")
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<Customer2, Customer2>chunk(500)
				.reader(customerItemReader())
				.writer(customerItemWriter2())
				.build();
	}

	@Bean(name="customer2job")
	public Job job() {
		return jobBuilderFactory.get("customer2job")
				.start(step1())
				.build();
	}
}
