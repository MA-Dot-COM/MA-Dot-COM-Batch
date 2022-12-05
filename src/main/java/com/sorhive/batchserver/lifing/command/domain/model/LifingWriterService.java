package com.sorhive.batchserver.lifing.command.domain.model;

import com.sorhive.batchserver.member.command.domain.model.member.MemberCode;

/**
 * <pre>
 * Class : LifingWriterService
 * Comment: 라이핑 작성자 서비스
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-12-05       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 * @see (참고할 class 또는 외부 url)
 */
public interface LifingWriterService {

    LifingWriter createLifingWriter(MemberCode lifingWriterMemberCode);
}
