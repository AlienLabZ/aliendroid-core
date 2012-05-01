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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import roboguice.util.Ln;
import android.content.Context;
import android.content.pm.PackageManager;
import dalvik.system.DexFile;

/**
 * Search for classes and informations inside the DEX file. 
 * 
 * @author Marlon Silva Carvalho
 * @since 1.0.0
 */
final public class Dex {

	/**
	 * Looking for classes which have a specific name.  
	 * 
	 * @param context Android Context.
	 * @param clasz Class name to search for.
	 * @return List containing all found Models.
	 */
	public static List<Class<?>> searchForClass(final Context context, final String clasz) {
		ArrayList<Class<?>> modelClasses = new ArrayList<Class<?>>();
		try {
			String path = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir;
			DexFile dexfile = new DexFile(path);
			Enumeration<?> entries = dexfile.entries();

			while (entries.hasMoreElements()) {
				String name = (String) entries.nextElement();
				Class<?> discoveredClass = null;
				Class<?> superClass = null;
				try {
					discoveredClass = Class.forName(name, true, context.getClass().getClassLoader());
					superClass = discoveredClass.getSuperclass();
				} catch (ClassNotFoundException e) {
					Ln.e("AlienDroid", e.getMessage());
				}

				if ((discoveredClass == null) || (superClass == null) || (superClass.getName().indexOf(clasz) == -1)) {
					continue;
				}
				modelClasses.add(discoveredClass);
			}

		} catch (IOException e) {
			Ln.e("AlienDroid", e.getMessage());
		} catch (PackageManager.NameNotFoundException e) {
			Ln.e("AlienDroid", e.getMessage());
		}

		return modelClasses;
	}

}
