package jbc.practica.users;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.streams.StreamsConfig;

import java.util.*;

public class UserTopology {

    public static final String USERS_TOPIC = "users";
    public static final String REKEYED_USERS_TOPIC = "rekeyed-users";

    public static final String BOOTSTRAP_SERVER = "localhost:9092";
    private static final String TEMP_STATE_DIR = "./temp";
    private static final int NUM_PARTITIONS = 1;
    private static final short REPLICATION_FACTOR = 3;
    private static final String PROCESSING_GUARANTEE_CONFIG= "exactly_once";

    public static Properties createStreamsConfigProperties(String applicationId) {

        Properties streamsConfiguration = new Properties();
        streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationId);
        streamsConfiguration.put(
                StreamsConfig.STATE_DIR_CONFIG, TEMP_STATE_DIR);
        streamsConfiguration.put(
                StreamsConfig.PROCESSING_GUARANTEE_CONFIG,PROCESSING_GUARANTEE_CONFIG);

        return streamsConfiguration;
    }

    public static void createTopics() {
        Map<String, Object> config = new HashMap<>();

        config.put("bootstrap.servers", BOOTSTRAP_SERVER);
        AdminClient client = AdminClient.create(config);

        List<NewTopic> topics = new ArrayList<>();

        topics.add(new NewTopic(
                USERS_TOPIC,
                NUM_PARTITIONS,
                REPLICATION_FACTOR));

        topics.add(new NewTopic(
                REKEYED_USERS_TOPIC,
                NUM_PARTITIONS,
                REPLICATION_FACTOR));

        client.createTopics(topics);
        client.close();
    }
}
