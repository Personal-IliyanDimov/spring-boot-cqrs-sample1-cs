package org.imd.cqrs.sample1.cs.repository;

import org.imd.cqrs.sample1.cs.model.entity.PostCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostCommentEntity, Long> {

    List<PostCommentEntity> findAllByPostId(Long postId);
}