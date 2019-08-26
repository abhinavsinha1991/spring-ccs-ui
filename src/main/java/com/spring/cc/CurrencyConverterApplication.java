/*

 */

package com.spring.cc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.spring.cc.config.SystemConfig;

@SpringBootApplication
public class CurrencyConverterApplication implements CommandLineRunner {

	@Autowired
	private Environment env;

	public void run(String... args) throws Exception {
		// System.out.println(env.getProperty(env.getProperty("service.path")));
		SystemConfig.SERVICE_URI = env.getProperty(env.getProperty("service.path"));
		// System.out.println(SystemConfig.SERVICE_URI + "******");
	}

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConverterApplication.class, args);
	}

}
