/*
 * AlienDroid Framework
 * Copyright (C) 2012 AlienLabZ
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.alienlabz.util;

import roboguice.event.EventManager;

import com.google.inject.Injector;

public class Beans {
	private static Injector injector;
	private static EventManager eventManager;
	
	public static Injector getInjector() {
		return injector;
	}

	public static void setInjector(final Injector p_injector) {
		injector = p_injector;
	}

	public static <T> T getBean(final Class<T> beanClass) {
		return injector.getInstance(beanClass);
	}

	public static void setEventManager() {
		eventManager = injector.getInstance(EventManager.class);
	}

	public static EventManager getEventManager() {
		return eventManager;
	}

}
