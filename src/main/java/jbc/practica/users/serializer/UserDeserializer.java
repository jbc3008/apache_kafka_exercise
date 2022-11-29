package jbc.practica.users.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import jbc.practica.users.model.User;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class UserDeserializer implements Deserializer<User> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public User deserialize(String value, byte[] bytes) {

        ObjectMapper objectMapper = new ObjectMapper();
        User pojo = null;

        try {
            pojo = objectMapper.readValue(bytes, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pojo;
    }

    @Override
    public void close() {

    }
}
