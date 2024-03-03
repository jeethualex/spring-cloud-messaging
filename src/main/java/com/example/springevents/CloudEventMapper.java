package com.example.springevents;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloudevents.CloudEvent;
import io.cloudevents.v03.AttributesImpl;
import io.cloudevents.v03.CloudEventBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.messaging.Message;

/**
 * Simple implementation of a CloudEvent Mapper that maps headers from a SpringMessage to a
 * {@code io.cloudevents.CloudEvent}. It adds the message payload as 'data' mapped to the provided
 * 'mappedClass' using an OjectMapper.
 *
 * <p>Expect this implementation to be replaced with one provided in Spring CLoud Function in
 * the near future.
 *
 * @author Thomas Risberg
 */
public class CloudEventMapper {

    final static String CE_ID = "Ce-Id";
    final static String CE_TYPE = "Ce-Type";
    final static String CE_SOURCE = "Ce-Source";
    final static String CE_TIME = "Ce-Time";

    private static Log log = LogFactory.getLog(CloudEventMapper.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> CloudEvent<AttributesImpl, T> convert(Message<JsonNode> message, Class<T> mappedClass) throws IllegalStateException {
        Map<String, String> headers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for (String key : message.getHeaders().keySet()) {
            if (key.toLowerCase().startsWith("ce-")) {
                Object value = message.getHeaders().get(key);
                if (value != null) {
                    headers.put(key,value.toString());
                }
            }
        }
        if (headers.get(CE_ID) == null || (headers.get(CE_SOURCE) == null || headers.get(CE_TYPE) == null)) {
            throw new IllegalStateException("Cloud Event required fields are not present.");
        }

        JsonNode node = message.getPayload();
        T result = null;
        try {
            result = objectMapper.treeToValue(node, mappedClass);
        } catch (JsonProcessingException e) {
            log.error("Failed parsing JSON data: " + e.getMessage());
        }

        return CloudEventBuilder.<T>builder().withId(headers.get(CE_ID))
                .withType(headers.get(CE_TYPE))
                .withSource((headers.get(CE_SOURCE) != null) ? URI.create(headers.get(CE_SOURCE)) : null)
                .withTime((headers.get(CE_TIME) != null) ? ZonedDateTime.parse(headers.get(CE_TIME)) : null)
                .withData(result)
                .withDatacontenttype("application/x-java-object;type=" + mappedClass.getTypeName())
                .build();
    }
}
