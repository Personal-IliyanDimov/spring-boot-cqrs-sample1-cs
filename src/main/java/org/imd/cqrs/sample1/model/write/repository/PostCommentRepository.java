package org.imd.cqrs.sample1.model.write.repository;

import org.imd.cqrs.sample1.model.write.entity.PostCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostCommentEntity, Long> {

    List<PostCommentEntity> findAllByPostId(Long postId);
}