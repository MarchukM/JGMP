package com.nosqlexample.config;

import com.couchbase.client.core.msg.kv.DurabilityLevel;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.manager.bucket.BucketSettings;
import com.couchbase.client.java.manager.bucket.BucketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@Configuration
@EnableCouchbaseRepositories
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    @Autowired
    private DBProperties dbProp;

    @Bean(destroyMethod = "disconnect")
    @Primary
    public Cluster getCouchbaseCluster() {
        return Cluster.connect(dbProp.getHostName(), dbProp.getUsername(), dbProp.getPassword());
    }

    @Bean
    @Primary
    public Bucket getCouchbaseBucket(Cluster cluster) {
        // Creates the cluster if it does not exist yet
        if (!cluster.buckets().getAllBuckets().containsKey(dbProp.getBucketName())) {
            cluster.buckets().createBucket(
                BucketSettings.create(dbProp.getBucketName())
                    .bucketType(BucketType.COUCHBASE)
                    .minimumDurabilityLevel(DurabilityLevel.NONE)
                    .ramQuotaMB(128));
        }
        return cluster.bucket(dbProp.getBucketName());
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }

    @Override
    public String getConnectionString() {
        return "couchbase://" + dbProp.getHostName();
    }

    @Override
    public String getUserName() {
        return dbProp.getUsername();
    }

    @Override
    public String getPassword() {
        return dbProp.getPassword();
    }

    @Override
    public String getBucketName() {
        return dbProp.getBucketName();
    }
}
