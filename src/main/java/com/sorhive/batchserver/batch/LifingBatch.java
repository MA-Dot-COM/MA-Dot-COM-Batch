package com.sorhive.batchserver.batch;

import com.sorhive.batchserver.lifing.query.LifingQueryService;
import com.sorhive.batchserver.lifing.query.dto.LifingSummary;
import com.sorhive.batchserver.member.command.domain.model.member.Member;
import com.sorhive.batchserver.member.command.domain.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

/**
 * <pre>
 * Class : LifingJob
 * Comment: 배치 잡을 생성 및 순차적으로 실행
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-22       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Configuration
public class LifingBatch {
    private static final Logger log = LoggerFactory.getLogger(LifingBatch.class);
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final LifingQueryService lifingQueryService;
    private final MemberRepository memberRepository;
    private List<LifingSummary> lifingSummaries;

    public LifingBatch(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, LifingQueryService lifingQueryService, MemberRepository memberRepository) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.lifingQueryService = lifingQueryService;
        this.memberRepository = memberRepository;
    }

    @Bean
    public Job lifingJob() {
        
        /*  batch job을 생성 */
        return jobBuilderFactory.get("lifingJob") 
                
                /* bean으로 등록된 Step을 순차적으로 실행 */
                .start(lifingStep1(null))
                .next(lifingStep2(null))
                .build();
    }

    @Bean
    @JobScope
    public Step lifingStep1(@Value("#{jobParameters[requestDate]}") String requestDate) {
        
        /* lifingStep1 batch step을 생성 */
        return stepBuilderFactory.get("lifingStep1")
                
                /* step 안에서 수행될 기능들을 명시, tasklet은 step 안에서 단일로 수행될 커스텀한 기능을 선언할 때 사용 */
                .tasklet((contribution, chunkContext) -> {
                    log.info("[lifingStep1] Start==================");
                    log.info("[lifingStep1] requestDate = {}",requestDate);
                    log.info("[lifingStep1] 현재 시간에서 하루 후~ 하루와 1시간 후 이내에 라이핑 데이터들 조회");

                    lifingSummaries = lifingQueryService.findAllLifing();

                    /* batch가 성공적으로 수행되고 종료됨을 반환 */
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    @JobScope
    public Step lifingStep2(@Value("#{jobParameters[requestDate]}") String requestDate) {
        return stepBuilderFactory.get("lifingStep2")
                .tasklet((contribution, chunkContext) -> {
                    log.info("[lifingStep2] Start==================");
                    log.info("[lifingStep2] requestDate = {}",requestDate);
                    log.info("[lifingStep2] 라이핑 데이터를 통하여 회원들의 lifing_yn 을 N으로 변경 ");

                    for (int i = 0; i < lifingSummaries.size(); i++) {
                        Optional<Member> memberData = memberRepository.findByMemberCode(lifingSummaries.get(i).getLifingWriterCode());
                        Member member = memberData.get();

                        member.changeLifingYn();

                        memberRepository.save(member);
                    }

                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
