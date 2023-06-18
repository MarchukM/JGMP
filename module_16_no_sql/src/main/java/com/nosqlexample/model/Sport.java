package com.nosqlexample.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.couchbase.core.index.QueryIndexed;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.repository.Collection;

import java.util.UUID;

@Document
@Getter
@Setter
@NoArgsConstructor
@Collection("users")
public class Sport {
    @Field
    private UUID id = UUID.randomUUID();
    @Field
    @QueryIndexed
    private String sportName;
    @Field
    private String sportProficiency;
}
