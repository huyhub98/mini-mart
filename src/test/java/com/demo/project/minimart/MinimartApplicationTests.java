package com.demo.project.minimart;

import com.demo.project.minimart.interfaces.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest()
@ActiveProfiles("test")
//@TestPropertySource(locations = "classpath:application-test.properties")
class MinimartApplicationTests {

	@Test
	void contextLoads() {
	}

}
