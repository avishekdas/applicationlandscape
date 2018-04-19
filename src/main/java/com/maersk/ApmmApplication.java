/*
 * Copyright [2011-2016] "Neo Technology"
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 *
 */
package com.maersk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import com.maersk.events.EventPublisher;
import com.maersk.events.PostSaveEvent;
import com.maersk.events.PreDeleteEvent;
import com.maersk.events.PreSaveEvent;


@SpringBootApplication
@EntityScan("com.maersk.domain")
public class ApmmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApmmApplication.class, args);
	}

	/**
	 * Simply defining a Neo4j OGM <code>EventListener</code> will register it
	 * with the session factory.
	 */
	@Bean
	public EventPublisher eventPublisher() {
		return new EventPublisher();
	}


	@EventListener
	public void onPreSaveEvent(PreSaveEvent event) {
		Object entity = event.getSource();
		System.out.println("Before save of: " + entity);
	}

	@EventListener
	public void onPostSaveEvent(PostSaveEvent event) {
		Object entity = event.getSource();
		System.out.println("After save of: " + entity);
	}

	@EventListener
	public void onPreDeleteEvent(PreDeleteEvent event) {
		Object entity = event.getSource();
		System.out.println("Before delete of: " + entity);
	}
}
