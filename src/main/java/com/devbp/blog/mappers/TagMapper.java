package com.devbp.blog.mappers;

import com.devbp.blog.domain.PostStatus;
import com.devbp.blog.domain.dtos.TagResponse;
import com.devbp.blog.domain.entities.Post;
import com.devbp.blog.domain.entities.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy =  ReportingPolicy.IGNORE)
public interface TagMapper {
    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    TagResponse toTagResponse(Tag tag);

    @Named("calculatePostCount")
    default Integer calculatePostCount(Set<Post> postList) {

        if(postList == null){
            return 0;
        }
        return (int) postList.stream().filter(post -> PostStatus.PUBLISHED.equals(post.getStatus())).count();

    }
}
