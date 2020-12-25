package org.imd.cqrs.sample1.jaxrs.model.mapper.domain;

import org.imd.cqrs.sample1.model.domain.Post;
import org.imd.cqrs.sample1.model.write.entity.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostDomainMapper {
    Post toPost(PostEntity pe);
    PostEntity toPostEntity(Post post);

    void transfer(Post post, @MappingTarget PostEntity existingPostEntity);

    List<Post> toPosts(List<PostEntity> allEntities);
}
