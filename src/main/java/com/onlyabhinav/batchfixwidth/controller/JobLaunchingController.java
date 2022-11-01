package com.onlyabhinav.batchfixwidth.controller;



import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobLaunchingController {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private JobOperator jobOperator;

	@Autowired
	private Job job;
	

	@RequestMapping(value = "/1", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void launch(@RequestParam("name") String name) throws Exception {

//		this.jobOperator.start("customer-job", String.format("name=%s", name));
		this.jobOperator.start("customer2job", String.format("name=%s", name));
	}

	@RequestMapping(value = "/2", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void launch2(@RequestParam("name") String name) throws Exception {
		JobParameters jobParameters =
				new JobParametersBuilder()
						.addString("name", name)
						.toJobParameters();
		this.jobLauncher.run(job, jobParameters);
	}
}
