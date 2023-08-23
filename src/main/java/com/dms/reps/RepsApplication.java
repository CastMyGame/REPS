package com.dms.reps;

import com.mongodb.client.MongoClient;
import com.twilio.Twilio;
import jakarta.mail.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class RepsApplication {

	public static final String ACCOUNT_SID = "AC31fd459d82bd5d3ff135db0968b011d7";
	public static final String AUTH_TOKEN = "ea0cd2e5f6915fd8cf8dddf7886c058d";
	public static void main(String[] args) {
		SpringApplication.run(RepsApplication.class, args);
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);}
}
