package jbc.practica.users;

import jbc.practica.users.model.User;
import jbc.practica.users.serializer.UserCustomSerdes;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

public class RekeyedUsersKStream {

    public static void main(String[] args) {

        UserTopology.createTopics();

        final StreamsBuilder builder = new StreamsBuilder();

        KStream<String, User> movieStream = builder.stream(UserTopology.USERS_TOPIC,
                        Consumed.with(Serdes.String(), UserCustomSerdes.User()))
                .map((key, user) ->
                        new KeyValue<>(user.getRegionid(), user));

        movieStream.to(UserTopology.REKEYED_USERS_TOPIC,
                Produced.with(Serdes.String(), UserCustomSerdes.User()));

        Topology topology = builder.build();
        final KafkaStreams streams = new KafkaStreams(topology,
                UserTopology.createStreamsConfigProperties("usersRekeyed"));
        streams.cleanUp();

        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }


}
