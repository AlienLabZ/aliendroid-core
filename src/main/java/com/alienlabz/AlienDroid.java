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
package com.alienlabz;

import java.util.List;

import roboguice.RoboGuice;
import android.app.Application;
import android.content.Context;

import com.alienlabz.util.Beans;
import com.alienlabz.util.Dex;
import com.alienlabz.util.Reflection;
import com.google.inject.AbstractModule;
import com.google.inject.Injector;

/**
 * Use this class to initialize the AlienDroid framework.<br/>
 * Use {@link #init(Context)} as soon as possible in your {@link Application} subclass {@link Application#onCreate()} method.
 * 
 * @author Marlon Silva Carvalho
 * @since 1.0.0
 */
final public class AlienDroid {

	/**
	 * This class can't have subclasses nor be instantiated.
	 */
	private AlienDroid() {
	}

	/**
	 * Initialize AlienDroid.
	 * 
	 * @param app Android Context.
	 */
	public static final void init(final Application app) {
		List<Class<?>> modulesClasses = Dex.getModules(app);
		AbstractModule[] modules = new AbstractModule[modulesClasses.size() + 1];
		for (int i = 0; i < modulesClasses.size(); i++) {
			Class<?> cls = modulesClasses.get(i);
			modules[i] = (AbstractModule) Reflection.instantiate(cls, Context.class, app);
		}
		modules[modulesClasses.size()] = RoboGuice.newDefaultRoboModule(app);
		Injector injector = RoboGuice.setBaseApplicationInjector(app, RoboGuice.DEFAULT_STAGE, modules);
		Beans.setInjector(injector);
	}

}
