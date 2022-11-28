package com.sorhive.batchserver.schedule;

import com.sorhive.batchserver.batch.LifingBatch;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Class : LifingJobScheduler
 * Comment: 라이핑에 대한 스케줄러
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-23       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Component
public class LifingJobScheduler {
    private final JobLauncher jobLauncher;
    private final LifingBatch lifingBatch;

    public LifingJobScheduler(JobLauncher jobLauncher, LifingBatch lifingBatch) {
        this.jobLauncher = jobLauncher;
        this.lifingBatch = lifingBatch;
    }

    // 5분에 한 번씩 실행
    @Scheduled(cron = "0 0/5 * * * *")
    public void testJobSchedule() {
        // 파라미터를 매번 다르게 설정하여 다른 Job Instance로 지정
        Map<String, JobParameter> config = new HashMap<>();
        config.put("time", new JobParameter(System.currentTimeMillis()));

        JobParameters jobParameters = new JobParameters(config);
        try {
            JobExecution jobExecution = jobLauncher.run(lifingBatch.lifingJob(), jobParameters);
        } catch (JobExecutionAlreadyRunningException
                 | JobRestartException
                 | JobInstanceAlreadyCompleteException
                 | JobParametersInvalidException e) {
            e.getMessage();
        }
    }
}
