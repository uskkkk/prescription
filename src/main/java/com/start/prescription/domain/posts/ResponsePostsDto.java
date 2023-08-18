package com.start.prescription.domain.posts;

import com.start.prescription.domain.CommonDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "POSTS")
public class ResponsePostsDto extends CommonDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CNTNTS_SN")
    private int cntntsSn; // 콘텐츠 일련번호

    @Column(name = "CNTNTS_CL_SN")
    private int cntntsClSn; // 콘텐츠 목록 일련번호

    @Column(name = "CNTNTS_CL_NM")
    private String cntntsClNm; // 콘텐츠 제목

    @Column(name = "CNTNTS_CL_CN")
    private String cntntsClCn; // 콘텐츠 분류 내용

    @Builder
    public ResponsePostsDto(int cntntsSn, int cntntsClSn, String cntntsClNm, String cntntsClCn) {
        this.cntntsSn = cntntsSn;
        this.cntntsClSn = cntntsClSn;
        this.cntntsClNm = cntntsClNm;
        this.cntntsClCn = cntntsClCn;
    }
}
