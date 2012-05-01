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

import roboguice.RoboGuice;
import android.content.Context;

import com.alienlabz.util.Beans;

/**
 * Use this class to initialize the AlienDroid framework.<br/>
 * Use {@link #init(Context)} as soon as possible in your {@link Application} subclass {@link Application#onCreate()} method.
 * 
 * @author Marlon Silva Carvalho
 * @since 1.0.0
 */
final public class AlienDroid {

	/**
	 * This class can't have subclasses or be instantiated.
	 */
	private AlienDroid() {
	}

	/**
	 * Initialize AlienDroid.
	 * 
	 * @param context Android Context.
	 */
	public static final void init(final Context context) {
		Beans.setInjector(RoboGuice.getInjector(context));
	}

}
