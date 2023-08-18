package com.start.prescription.persistance.posts;

import com.start.prescription.domain.posts.ResponsePostsDto;
import com.start.prescription.persistence.posts.PostsRespository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    private PostsRespository postsRespository;

    @BeforeEach
    public void cleanUp() {
        postsRespository.deleteAll();
    }

    @Test
    public void getPosts() {
        // given
        int cntntsSn = 1;
        int cntntsClSn = 10;
        String cntntsClNm = "직장탈출증";
        String cntntsClCn = "직장은 결장과 ...";

        postsRespository.save(ResponsePostsDto.builder()
                .cntntsSn(cntntsSn)
                .cntntsClCn(cntntsClCn)
                .cntntsClNm(cntntsClNm)
                .cntntsClSn(cntntsClSn)
                .build());

        //when
        List<ResponsePostsDto> postsList = postsRespository.findAll();

        ResponsePostsDto responsePostsDto = postsList.get(0);
        Assertions.assertEquals(cntntsSn,responsePostsDto.getCntntsSn());
        Assertions.assertEquals(cntntsClCn,responsePostsDto.getCntntsClCn());
        Assertions.assertEquals(cntntsClNm,responsePostsDto.getCntntsClNm());
        Assertions.assertEquals(cntntsClSn,responsePostsDto.getCntntsClSn());
    }
}
