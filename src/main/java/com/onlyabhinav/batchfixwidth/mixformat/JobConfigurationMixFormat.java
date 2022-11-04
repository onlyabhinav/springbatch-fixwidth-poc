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
package com.onlyabhinav.batchfixwidth.mixformat;



import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.item.support.ClassifierCompositeItemProcessor;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.classify.PatternMatchingClassifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import com.onlyabhinav.batchfixwidth.domain.Customer;
import com.onlyabhinav.batchfixwidth.domain.Customer2;
import com.onlyabhinav.batchfixwidth.domain.CustomerFieldSetMapper;
import com.onlyabhinav.batchfixwidth.domain.CustomerFieldSetMapper2;
import com.onlyabhinav.batchfixwidth.mixformat.domain.MixRecord;
import com.onlyabhinav.batchfixwidth.mixformat.fieldsetmappers.ARecordFieldSetMapper;
import com.onlyabhinav.batchfixwidth.mixformat.fieldsetmappers.BRecordFieldSetMapper;
import com.onlyabhinav.batchfixwidth.mixformat.fieldsetmappers.OneRecordFieldSetMapper;
import com.onlyabhinav.batchfixwidth.mixformat.processors.ARecordItemProcessor;
import com.onlyabhinav.batchfixwidth.mixformat.processors.BRecordItemProcessor;
import com.onlyabhinav.batchfixwidth.mixformat.processors.OneRecordItemProcessor;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Configuration
public class JobConfigurationMixFormat {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	
	private Map tokenizers;
	private Map fieldSetMappers;
	
//    @Bean
//    @StepScope
//    public FlatFileItemReader reader(@Value("#{jobParameters['inputFileName']}") String inputFileName) {
//        FlatFileItemReader reader = new FlatFileItemReader();
//        log.info("[START] - Input File name = {}", inputFileName);
//        reader.setResource(new FileSystemResource(inputFileName));
//        reader.setLineMapper(patternLineMapper());
//        log.info("[END] - Input File name = {}", inputFileName);
//        return reader;
//    }
//    
    @Bean
    @StepScope
    public FlatFileItemReader reader() {
        FlatFileItemReader reader = new FlatFileItemReader();
        log.info("[START] - Input File name = {}");
        reader.setLinesToSkip(1);
		reader.setResource(new ClassPathResource("/data/customer-fixwd-mixformat.txt"));
        reader.setLineMapper(patternLineMapper());
        log.info("[END] - Input File name = {}");
        return reader;
    }
    
    @Bean
    public LineMapper patternLineMapper() {
        PatternMatchingCompositeLineMapper patternLineMapper = new PatternMatchingCompositeLineMapper<>();
        tokenizers = new HashMap<String, LineTokenizer>();
        try {
            tokenizers.put("A*", aRecordTokenizer());
            tokenizers.put("B*", bRecordTokenizer());
            tokenizers.put("1*", oneRecordTokenizer());
        } catch (Exception e) {
            e.printStackTrace();
        }
         fieldSetMappers = new HashMap<String, FieldSetMapper>();
        fieldSetMappers.put("A*", new ARecordFieldSetMapper());
        fieldSetMappers.put("B*", new BRecordFieldSetMapper());
        fieldSetMappers.put("1*", new OneRecordFieldSetMapper());
        patternLineMapper.setTokenizers(tokenizers);
        patternLineMapper.setFieldSetMappers(fieldSetMappers);
        return patternLineMapper;
    }
	
    @Bean
    @StepScope
    public ItemProcessor processor() {

        ClassifierCompositeItemProcessor processor = new ClassifierCompositeItemProcessor();
        PatternMatchingClassifier<ItemProcessor> classifier = new PatternMatchingClassifier<>();
        Map<String, ItemProcessor> patternMap = new HashMap();
        patternMap.put("A*", new ARecordItemProcessor());
        patternMap.put("B*", new BRecordItemProcessor());
        patternMap.put("1*", new OneRecordItemProcessor());
        classifier.setPatternMap(patternMap);
        processor.setClassifier(classifier);
        
        
        return processor;
    }

	@Bean(name="mixFormatItemWriter")
	public ItemWriter mixItemWriter() {

		ClassifierCompositeItemWriter mixItemWriter = new ClassifierCompositeItemWriter<>();
		
		return mixItemWriter;

	}
	
	@Bean(name="mixFormatItemWriterDummy")
	public ItemWriter<MixRecord> mixFormatItemWriterDummy() {
		return items -> {
			for (MixRecord item : items) {
				System.out.println(item.toString());
			}
		};
	}
	
	
//    @Bean
//    public ItemWriter<MixRecord> aRecordItemWriter() {
//        return new FlatFileItemWriterBuilder<Person>()
//                .name("aRecordItemWriter")
//                .resource(new FileSystemResource("foos.txt"))
//                .lineAggregator(new PassThroughLineAggregator<>())
//                .build();
//    }


	
    public FixedLengthTokenizer aRecordTokenizer() {
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();

        tokenizer.setNames("id", "firstName", "lastName", "birthdate");
        tokenizer.setColumns(new Range(1,1),
                             new Range(2,13),
                             new Range(14,26),
                             new Range(27));
        return tokenizer;
}
    public FixedLengthTokenizer bRecordTokenizer() {
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();

        tokenizer.setNames("id", "firstName", "lastName", "birthdate");
        tokenizer.setColumns(new Range(1,1),
                             new Range(2,13),
                             new Range(14,26),
                             new Range(27));
        return tokenizer;
}
    public FixedLengthTokenizer oneRecordTokenizer() {
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();

        tokenizer.setNames("id", "firstName", "lastName", "birthdate");
        tokenizer.setColumns(new Range(1,1),
                             new Range(2,13),
                             new Range(14,26),
                             new Range(27));
        return tokenizer;
}
	

	@Bean(name="mixFormatJobStep")
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.chunk(500)
				.reader(reader())
				.writer(mixFormatItemWriterDummy())
				.build();
	}

	@Bean(name="mixFormatJob")
	public Job job() {
		return jobBuilderFactory.get("mixFormatJob")
				.start(step1())
				.build();
	}
}
