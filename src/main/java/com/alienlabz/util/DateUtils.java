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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Responsible for providing utilities to format dates.
 * 
 * @author Marlon Silva Carvalho
 * @since 1.0.0
 */
final public class DateUtils {

	private DateUtils() {
	}

	public static String format(final Date data, final String formato) {
		if (data != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
			return dateFormat.format(data);
		} else {
			return "";
		}
	}

	public static Date format(final String strData, final String formato) {
		if (strData != null) {
			SimpleDateFormat df = new SimpleDateFormat(formato);
			try {
				return (Date) df.parse(strData);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}
}
