package com.nosqlexample.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class DBProperties {
    @Value("${spring.couchbase.bootstrap-hosts}")
    private String hostName;
    @Value("${spring.couchbase.bucket.user}")
    private String username;
    @Value("${spring.couchbase.bucket.password}")
    private String password;
    @Value("${spring.couchbase.bucket.name}")
    private String bucketName;
}
