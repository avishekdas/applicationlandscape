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
package com.maersk.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * The course object connects a teacher
 * with a subject and the pupils who are taught the subject by the teacher
 */
@JsonIdentityInfo(generator = JSOGGenerator.class)
@NodeEntity(label = "Class")
public class Employee {

	private Long id;

	private String name;

	@Relationship(type = "APPMANAGER", direction = Relationship.INCOMING)
	private Set<Application> applications = new HashSet<>();

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Set<Application> getApplications() {
		return applications;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"id=" + getId() +
				", name='" + name + '\'' +
				", applications=" + applications.size() +
				'}';
	}

	public void updateFrom(Employee employee) {
		this.name = employee.name;
	}
}
