package com.start.prescription.persistence.posts;

import com.start.prescription.domain.posts.ResponsePostsDto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostsRespository extends JpaRepository<ResponsePostsDto,Long> {
}
