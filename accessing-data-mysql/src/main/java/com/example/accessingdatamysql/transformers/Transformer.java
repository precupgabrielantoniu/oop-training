package com.example.accessingdatamysql.transformers;

public interface Transformer<Entity, DTO> {
    DTO fromEntity(Entity entity);
    Entity fromDTO(DTO dto);
}
