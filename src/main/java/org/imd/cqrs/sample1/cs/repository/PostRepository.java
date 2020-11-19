package org.imd.cqrs.sample1.cs.repository;

import org.imd.cqrs.sample1.cs.model.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    public Optional<PostEntity> findByTitle(String title);
}