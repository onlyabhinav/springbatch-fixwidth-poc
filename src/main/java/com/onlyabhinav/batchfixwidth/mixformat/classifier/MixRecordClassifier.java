package com.onlyabhinav.batchfixwidth.mixformat.classifier;

import org.springframework.batch.item.ItemWriter;
import org.springframework.classify.Classifier;

import com.onlyabhinav.batchfixwidth.mixformat.domain.MixRecord;

import lombok.Setter;


@Setter
public class MixRecordClassifier implements Classifier<MixRecord, ItemWriter<MixRecord>> {
 
    private static final long serialVersionUID = 1L;
     
    private ItemWriter<MixRecord> aRecordWriter;
    private ItemWriter<MixRecord> bRecordWriter;
    private ItemWriter<MixRecord> oneRecordWriter;
 
//    public MixRecordClassifier(ItemWriter evenItemWriter, ItemWriter oddItemWriter) {
//        this.aRecordWriter = evenItemWriter;
//        this.bRecordWriter = oddItemWriter;
//    }
 
    @Override
    public ItemWriter<MixRecord> classify(MixRecord mixRecord) {
       return Math.random() % 2 == 0 ? aRecordWriter : bRecordWriter;
    }
}