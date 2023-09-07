package com.example.accessingdatamysql.transformers;

public interface CompositeObjectTransformer<ToDTO> {
    ToDTO transform(Object... args) throws ClassCastException;
}
