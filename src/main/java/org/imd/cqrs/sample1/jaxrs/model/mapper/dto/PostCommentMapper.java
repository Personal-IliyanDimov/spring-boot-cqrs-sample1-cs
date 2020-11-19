package org.imd.cqrs.sample1.jaxrs.model.mapper.dto;

import org.imd.cqrs.sample1.jaxrs.model.dto.PostCommentDto;
import org.imd.cqrs.sample1.model.domain.PostComment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostCommentMapper {
    PostCommentDto toPostCommentDto(PostComment postComment);
    PostComment toPostComment(PostCommentDto postDto);

    List<PostCommentDto> toPostCommentDtos(List<PostComment> users);
}
