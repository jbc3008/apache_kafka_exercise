package jbc.practica.users.serializer;

import jbc.practica.users.model.User;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public class UserCustomSerdes {

    public static Serde<User> User() { return new UserSerde(); }

    public static final class UserSerde extends Serdes.WrapperSerde<User> {
        public UserSerde() { super(new UserSerializer(), new UserDeserializer()); }
    }
}
