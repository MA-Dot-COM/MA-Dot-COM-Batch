package com.sorhive.batchserver.lifing.command.domain.repository;

import com.sorhive.batchserver.lifing.command.domain.model.Lifing;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * <pre>
 * Class : LifingRepository
 * Comment: 라이핑 레포지토리
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-12-05       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
public interface LifingRepository extends Repository<Lifing, Long> {

    void save(Lifing lifing);

    Optional<Lifing> findByLifingId(Long lifingId);
}
