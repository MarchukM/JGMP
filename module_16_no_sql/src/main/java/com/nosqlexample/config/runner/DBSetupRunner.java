package com.nosqlexample.config.runner;

import com.couchbase.client.core.error.CollectionExistsException;
import com.couchbase.client.core.error.IndexExistsException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.manager.collection.CollectionManager;
import com.couchbase.client.java.manager.collection.CollectionSpec;
import com.couchbase.client.java.query.QueryResult;
import com.nosqlexample.config.CollectionNames;
import com.nosqlexample.config.DBProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * This class run after the application startup. It automatically sets up all needed indexes
 */
@Component
public class DBSetupRunner implements CommandLineRunner {

    @Autowired
    private Bucket bucket;
    @Autowired
    private Cluster cluster;
    @Autowired
    private DBProperties props;

    @Override
    public void run(String... args) {
        createBucketPrimaryIndex();
        createCollection();
        createPrimaryIndexOnUser();
        createIndexOnEmail();
        System.out.println("Application is ready.");
    }

    private void createPrimaryIndexOnUser() {
        try {
            final String query = "CREATE PRIMARY INDEX idx_primary_user ON "+props.getBucketName()+"._default."+ CollectionNames.USERS;
            System.out.printf("Creating idx_primary_user: <%s>%n", query);
            final QueryResult result = cluster.query(query);
            for (JsonObject row : result.rowsAsObject()){
                System.out.printf("Index Creation Status %s%n",row.getObject("meta").getString("status"));
            }
            System.out.println("Created primary index on collection " + CollectionNames.USERS);
            Thread.sleep(1000);
        } catch (IndexExistsException e){
            System.out.println("Collection's primary index already exists");
        } catch (Exception e){
            System.out.printf("General error <%s> when trying to create index %n",e.getMessage());
        }
    }

    private void createCollection() {
        CollectionManager collectionManager = bucket.collections();
        try {
            CollectionSpec spec = CollectionSpec.create(CollectionNames.USERS, bucket.defaultScope().name());
            collectionManager.createCollection(spec);
            System.out.println("Created collection '" + spec.name() + "' in scope '" + spec.scopeName() + "' of bucket '" + bucket.name() + "'");
            Thread.sleep(1000);
        } catch (CollectionExistsException e){
            System.out.printf("Collection <%s> already exists%n", CollectionNames.USERS);
        } catch (Exception e) {
            System.out.printf("Generic error <%s>%n",e.getMessage());
        }
    }

    private void createBucketPrimaryIndex() {
        try {
            cluster.queryIndexes().createPrimaryIndex(props.getBucketName());
            System.out.println("Created primary index" + props.getBucketName());
        } catch (Exception e) {
            System.out.println("Primary index already exists on bucket "+props.getBucketName());
        }
    }

    private void createIndexOnEmail() {
        try {
            final QueryResult result = cluster.query("CREATE INDEX idx_find_user_by_email ON " + props.getBucketName() + "._default." + CollectionNames.USERS + "(email)");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.printf("Failed to create secondary index on user.email: %s%n", e.getMessage());
        }
    }
}
