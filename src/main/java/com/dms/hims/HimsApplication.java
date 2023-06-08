package com.dms.hims;

import com.dms.hims.data.InfractionRepository;
import com.dms.hims.data.PunishRepository;
import com.dms.hims.data.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class HimsApplication{
	public static void main(String[] args) {
		SpringApplication.run(HimsApplication.class, args);
	}

}
