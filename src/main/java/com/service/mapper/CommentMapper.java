package com.service.mapper;

import com.service.dto.comment.Comment;
import com.service.dto.comment.CommentCreate;
import com.service.entity.CommentEntity;
import com.service.entity.RepairEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public abstract class CommentMapper {

    @Mapping(target = "dateCreate",  expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "id", ignore = true)
    public abstract CommentEntity map(CommentCreate comment, RepairEntity repairEntity);

    @Mapping(target = "account", source = "createBy")
    public abstract Comment map(CommentEntity entity);
}
