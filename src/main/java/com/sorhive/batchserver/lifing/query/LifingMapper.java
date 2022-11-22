package com.sorhive.batchserver.lifing.query;

import com.sorhive.batchserver.lifing.query.dto.LifingSummary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <pre>
 * Class : LifingMapper
 * Comment: 라이핑 매퍼
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-12       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
@Mapper
public interface LifingMapper {

    List<LifingSummary> findAllLifing();

}
