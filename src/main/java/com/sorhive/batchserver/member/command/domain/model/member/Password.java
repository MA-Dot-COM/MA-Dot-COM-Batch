package com.sorhive.batchserver.member.command.domain.model.member;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * <pre>
 * Class : Password
 * Comment: 패스워드 도메인 모델
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-31       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Embeddable
public class Password {

    @Column(name = "password")
    private String value;

    protected Password() {
    }

    public Password(String value) {
        this.value = value;
    }

    public boolean match(String password) {
        return this.value.equals(password);
    }
}
