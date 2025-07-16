package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.cloud.config.enabled=false",
    "spring.cloud.discovery.enabled=false",
    "spring.kafka.bootstrap-servers=localhost:9092"
})
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
