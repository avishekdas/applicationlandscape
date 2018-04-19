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

package com.maersk.contorller;

import org.neo4j.ogm.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.maersk.domain.Application;
import com.maersk.repository.ApplicationRepository;

@RestController
@RequestMapping(value = "/api/applications")
public class ApplicationController {

	private ApplicationRepository applicationRepository;

	@Autowired
	public ApplicationController(ApplicationRepository applicationRepository) {
		this.applicationRepository = applicationRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Application> readAll() {
		return applicationRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public Application create(@RequestBody Application employee) {
		return applicationRepository.save(employee);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Application read(@PathVariable Long id) {
		return applicationRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		applicationRepository.deleteById(id);
	}

	@Transactional
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Application update(@PathVariable Long id, @RequestBody Application update) {
		final Application existing = applicationRepository.findById(id).orElseThrow(NotFoundException::new);
		existing.updateFrom(update);
		return applicationRepository.save(existing);
	}
}
