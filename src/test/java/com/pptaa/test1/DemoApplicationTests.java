package com.pptaa.test1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Bean
	public HttpFirewall allowUrlSemicolonHttpFirewall(){
		StrictHttpFirewall firewall = new StrictHttpFirewall();
		firewall.setAllowSemicolon(true);
		return firewall;
	}

}
