package com.sorhive.batchserver.lifing.query;

import com.sorhive.batchserver.lifing.query.dto.LifingSummary;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <pre>
 * Class : LifingQueryService
 * Comment: 라이핑 조회원 서비스
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       부시연           최초 생성
 * 2022-11-12       부시연           회원 번호로 회원의 모든 라이핑 조회
 * 2022-11-12       부시연           회원 번호로 회원의 모든 라이핑 조회 시 조회수 추가
 * 2022-11-15       부시연           라이핑 상세 조회 시 댓글 조회 기능 추가
 * 2022-11-16       부시연           라이핑 상세 조회 시 이미지 조회 기능 추가
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Service
@AllArgsConstructor
public class LifingQueryService {

    private static final Logger log = LoggerFactory.getLogger(LifingQueryService.class);
    private final LifingMapper lifingMapper;

    /** 회원 번호로 회원의 모든 라이핑 조회*/
    public List<LifingSummary> findAllLifing() {

        log.info("[LifingQueryService] findAllLifing Start ============");

        List<LifingSummary> lifingData = lifingMapper.findAllLifing();

        log.info("[LifingQueryService] findAllLifing End ============");

        return lifingData;
    }

}
