package com.example.springevents;

import java.util.function.Function;

import com.example.types.SpringEvent;
import com.fasterxml.jackson.databind.JsonNode;
import io.cloudevents.CloudEvent;
import io.cloudevents.v03.AttributesImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
public class SpringEventsApplication {

	private Logger log = LoggerFactory.getLogger(SpringEventsApplication.class);

	@Bean
	public Function<Message<JsonNode>, Message<String>> fun() {
		return (in) -> {
			log.info(in.toString());
			//CloudEvent<AttributesImpl, SpringEvent> cloudEvent = CloudEventMapper.convert(in, SpringEvent.class);
			//String results = "Processed: " + cloudEvent.getData();
			String results = "Processed...";
			log.info(results);
			return MessageBuilder.withPayload(results).build();
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringEventsApplication.class, args);
	}

}
