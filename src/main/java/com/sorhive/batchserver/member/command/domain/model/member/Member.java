package com.sorhive.batchserver.member.command.domain.model.member;
import lombok.Builder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * <pre>
 * Class : Member
 * Comment: 회원 도메인 모델
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-22       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Entity
@Table(name = "tbl_members")
@DynamicInsert
@DynamicUpdate
public class Member {

    @Id
    @Column(name = "member_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberCode;

    @Embedded
    private MemberId memberId;

    @Column(name = "member_name")
    private String memberName;

    @Column(name ="member_email")
    private String memberEmail;

    @Column(name = "member_role")
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ROLE_MEMBER'")
    private MemberRole memberRole;

    @Column(name = "password")
    private String password;

    @Column(name = "member_create_time")
    private Timestamp createTime;

    @Column(name = "member_upload_time")
    private Timestamp uploadTime;

    @Column(name = "member_delete_time")
    private Timestamp deleteTime;

    @Column(name = "member_delete_yn")
    @ColumnDefault("'N'")
    private Character deleteYn;

    @Column(name = "member_room_image")
    @ColumnDefault("''")
    private String roomImagePath;

    @Column(name = "member_avatar_image")
    @ColumnDefault("''")
    private String avatarImagePath;

    @Column(name = "member_lifing_no")
    @ColumnDefault("-1")
    private Long lifingNo;

    @Column(name = "member_lifing_category_no")
    @ColumnDefault("-1")
    private Long lifingCategoryNo;

    @Column(name = "member_lifing_yn")
    @ColumnDefault("'N'")
    private Character lifingYn;

    protected Member() { }

    public Member(MemberId memberId, String memberName, String password) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.password = password;
        this.createTime = new Timestamp(System.currentTimeMillis());
        this.uploadTime = new Timestamp(System.currentTimeMillis());
    }

    @Builder
    public Member(Long memberCode, MemberId memberId, String memberName, String memberEmail, MemberRole memberRole, String password, Timestamp createTime, Timestamp uploadTime, Timestamp deleteTime, Character deleteYn) {
        this.memberCode = memberCode;
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberRole = memberRole;
        this.password = password;
        this.createTime = createTime;
        this.uploadTime = uploadTime;
        this.deleteTime = deleteTime;
        this.deleteYn = deleteYn;
    }
    public void changeLifingYn() {
        this.lifingYn = 'N';
    }
}
