package com.example.accessingdatamysql.transformers;

public interface DTOTransformer <FromDTO, ToDTO>{
    ToDTO transform(FromDTO fromDTO);
}
