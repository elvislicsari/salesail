package com.ace.salesail.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA. User: Alin
 */
public abstract class FileUtils
{
	private static Properties SYSTEM_PROPS = new Properties();
	private static final String systemPropFile = "system.properties";

	private static final HashMap<String, Properties> cachedProperties = new HashMap<String, Properties>();

	static
	{
		try
		{
			SYSTEM_PROPS.load(FileUtils.class.getClassLoader().getResourceAsStream(systemPropFile));
		}
		catch (IOException e)
		{
			throw new RuntimeException("Failed to load system properties !");
		}
	}

	public static String loadSystemProperty(String key)
	{
		String value = SYSTEM_PROPS.getProperty(key);
		if (value == null)
		{
			throw new RuntimeException("Property '" + key + "' does not exist or is not configured properly! Please see the system.properties file.");
		}
		return value;
	}

	public static String loadSystemProperty(String key, String defaultValue)
	{
		try
		{
			return loadSystemProperty(key);
		}
		catch(Exception e)
		{
			return defaultValue;
		}
	}

	public static String loadProperty(String key, String propertyFile)
	{
		Properties tmpprop = cachedProperties.get(propertyFile);
		if (tmpprop == null)
		{
			tmpprop = new Properties();
			try
			{
				tmpprop.load(FileUtils.class.getClassLoader().getResourceAsStream(propertyFile));
				cachedProperties.put(propertyFile, tmpprop);
			}
			catch (IOException e)
			{
				throw new RuntimeException("Failed to load " + propertyFile + " properties !");
			}
		}

		final String value = tmpprop.getProperty(key);
		if (value == null)
		{
			throw new RuntimeException("Property '" + key + "' does not exist or is not configured properly! Please see the " + propertyFile + " file.");
		}
		return value;
	}

}
