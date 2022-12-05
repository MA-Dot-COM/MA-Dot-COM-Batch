package com.sorhive.batchserver.lifing.command.domain.model;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * <pre>
 * Class : Lifing
 * Comment: 라이핑 도메인 모델
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
@Entity
@Table(name = "tbl_lifings")
@Getter
public class Lifing {

    @Id
    @Column(name="lifing_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lifingId;

    @Column(name = "lifing_content")
    private String lifingConetent;

    @Column(name = "lifing_no")
    private Long lifingNo;

    @Column(name = "lifing_category_no")
    private Long lifingCategoryNo;

    private LifingWriter lifingWriter;

    @Column(name = "lifing_create_time")
    private Timestamp createTime;

    @Column(name = "lifing_upload_time")
    private Timestamp uploadTime;

    @Column(name = "lifing_delete_time")
    private Timestamp deleteTime;

    @Column(name = "lifing_delete_yn")
    @ColumnDefault(value = "'N'")
    private Character deleteYn;

    @Column(name = "lifing_day_yn")
    @ColumnDefault(value = "'N'")
    private Character lifingDayYn;

    @Column(name = "honey_count")
    @ColumnDefault("0")
    private Integer honeyCount;

    protected Lifing() { }

    public void changeLifingDayYn() {
        this.lifingDayYn = 'Y';
    }
}
