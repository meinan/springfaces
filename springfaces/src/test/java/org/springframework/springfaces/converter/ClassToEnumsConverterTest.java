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
package org.springframework.springfaces.converter;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.convert.ConverterNotFoundException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;

/**
 * Tests for {@link ClassToEnumsConverter}.
 * 
 * @author Phillip Webb
 */
public class ClassToEnumsConverterTest {

	private GenericConversionService conversionService;

	@Before
	@SuppressWarnings("deprecation")
	public void setup() throws Exception {
		this.conversionService = new DefaultConversionService();
		this.conversionService.addConverter(new ClassToEnumsConverter());
	}

	@Test
	public void shouldConvertToArray() throws Exception {
		ExampleEnum[] e = this.conversionService.convert(ExampleEnum.class, ExampleEnum[].class);
		assertEnums(e);
	}

	@Test
	public void shouldConvertToCollection() throws Exception {
		Object o = doConvert(collectionType(Collection.class));
		assertEnums(o);
		assertTrue(o instanceof Collection);
	}

	@Test
	public void shouldConvertToSet() throws Exception {
		Object o = doConvert(collectionType(Set.class));
		assertEnums(o);
		assertTrue(o instanceof Set);
	}

	@Test
	public void shouldConvertToList() throws Exception {
		Object o = doConvert(collectionType(List.class));
		assertEnums(o);
		assertTrue(o instanceof List);
	}

	@Test
	public void shouldConvertToArrayList() throws Exception {
		Object o = doConvert(collectionType(ArrayList.class));
		assertEnums(o);
		assertTrue(o instanceof ArrayList);
	}

	@Test(expected = ConverterNotFoundException.class)
	public void shouldNotConvertToMap() throws Exception {
		doConvert(collectionType(Map.class));
	}

	private Object doConvert(TypeDescriptor targetType) {
		return this.conversionService.convert(ExampleEnum.class, TypeDescriptor.forObject(ExampleEnum.class),
				targetType);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void assertEnums(Object o) {
		ExampleEnum[] e = {};
		if (o instanceof ExampleEnum[]) {
			e = (ExampleEnum[]) o;
		} else {
			e = (ExampleEnum[]) ((Collection) o).toArray(e);
		}
		assertTrue(Arrays.equals(ExampleEnum.values(), e));
	}

	private TypeDescriptor collectionType(Class<?> collectionType) {
		if (Map.class.isAssignableFrom(collectionType)) {
			return TypeDescriptor.map(collectionType, TypeDescriptor.valueOf(ExampleEnum.class),
					TypeDescriptor.valueOf(ExampleEnum.class));
		}
		return TypeDescriptor.collection(collectionType, TypeDescriptor.valueOf(ExampleEnum.class));
	}

	private enum ExampleEnum {
		ONE, TWO, THREE, FOUR
	}
}
