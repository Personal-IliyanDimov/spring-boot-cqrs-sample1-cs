package org.imd.cqrs.sample1.model.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostComment {

    private Long id;
    private String review;
}
