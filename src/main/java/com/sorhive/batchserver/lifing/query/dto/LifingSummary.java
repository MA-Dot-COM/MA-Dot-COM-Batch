package com.sorhive.batchserver.lifing.query.dto;

import lombok.Getter;

import java.sql.Timestamp;

/**
 * <pre>
 * Class : LifingSummary
 * Comment: 라이핑 데이터 전송객체
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
@Getter
public class LifingSummary {

    private Long lifingId;
    private Long lifingWriterCode;
    private Timestamp createTime;
    private Character deleteYn;

    protected LifingSummary() {}

}
