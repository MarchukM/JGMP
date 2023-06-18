package com.nosqlexample.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.repository.Collection;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

@Document
@Getter
@Setter
@NoArgsConstructor
@Collection("users")
public class User {
    @Id
    @GeneratedValue(strategy = UNIQUE)
    private String id;
    @Field
    private String email;
    @Field
    private String fullName;
    @Field
    private LocalDate birthDate;
    @Field
    private String gender;
    @Field
    private List<Sport> sport;
}


