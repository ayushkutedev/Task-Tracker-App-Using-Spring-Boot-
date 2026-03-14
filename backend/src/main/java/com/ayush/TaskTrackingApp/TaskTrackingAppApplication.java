package com.ayush.TaskTrackingApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class
TaskTrackingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskTrackingAppApplication.class, args);

		System.out.println(java.util.TimeZone.getDefault());
	}

}
