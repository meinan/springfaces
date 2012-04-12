/*
 * Copyright 2010-2012 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.springfaces.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SimpleController {

	@RequestMapping("simple/{name}")
	public String simple(@PathVariable Name name, Model model) {
		System.out.println(name);
		Hotel hotel = new Hotel();
		hotel.setId(123L);
		model.addAttribute(hotel);
		return "simple";
	}

	@RequestMapping("redirect")
	public String redirect() {
		return "bookmarkRedirect:simple";
	}

	@RequestMapping("hello")
	public String hello() {
		return "testBeanView";
	}

	//	@RequestMapping(method = RequestMethod.POST)
	//	public String postback() {
	//		return "test";
	//	}

}
