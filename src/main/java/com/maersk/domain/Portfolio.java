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

@JsonIdentityInfo(generator = JSOGGenerator.class)
@NodeEntity
public class Portfolio {

	private Long id;

	private String name;

	@Relationship(type = "PORTFOLIO_MEMBER")
	private Set<Application> applications;

	public Portfolio() {
		this.applications = new HashSet<>();
	}

	public Portfolio(String name) {
		this();
		this.name = name;
	}

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
		return "Portfolio{" +
				"id=" + getId() +
				", name='" + name + '\'' +
				", teachers=" + applications.size() +
				'}';
	}

	public void updateFrom(Portfolio portfolio) {
		this.name = portfolio.name;
	}
}
