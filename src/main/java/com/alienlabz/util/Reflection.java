/*
 *  Copyright 2012 AlienLabZ
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.alienlabz.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Reflection Helper.
 * 
 * @author Marlon Silva Carvalho
 * @since 1.0.0
 */
final public class Reflection {

	public static String getSimpleClassName(Object object) {
		return object.getClass().getSimpleName();
	}

	public static String getSimpleClassName(Class<?> object) {
		return object.getSimpleName();
	}

	public static Object getFieldValue(String sField, Object object) {
		Object result = null;
		try {
			Field field = object.getClass().getDeclaredField(sField);
			boolean acessible = field.isAccessible();
			field.setAccessible(true);
			result = field.get(object);
			field.setAccessible(acessible);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return result;
	}

	public static String[] getNonStaticDeclaredFieldsNames(Class<?> type) {
		Field[] fields = Reflection.getNonStaticDeclaredFields(type);
		String[] fieldsName = new String[fields.length];
		for (int idx = 0; idx < fields.length; idx++) {
			fieldsName[idx] = fields[idx].getName();
		}
		return fieldsName;
	}

	public static Field[] getNonStaticDeclaredFields(Class<?> type) {
		List<Field> fields = new ArrayList<Field>();

		if (type != null) {
			for (Field field : type.getDeclaredFields()) {
				if (!Modifier.isStatic(field.getModifiers()) && !field.getType().equals(type.getDeclaringClass())) {
					fields.add(field);
				}
			}
		}

		return fields.toArray(new Field[0]);
	}

	public static <T> T instantiate(Class<T> cls) {
		T o = null;
		try {
			o = cls.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		return o;
	}

	public static void setFieldValue(final String f, final Object object, final Object value) {
		try {
			Field field = object.getClass().getDeclaredField(f);
			boolean acessible = field.isAccessible();
			field.setAccessible(true);
			field.set(object, value);
			field.setAccessible(acessible);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Object callMethod(Object object, String methodName, Object... params) {
		try {
			for (Method method : object.getClass().getMethods()) {
				if (method.getName().equals(methodName)) {
					return method.invoke(object, params);
				}
			}
			return null;
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

}