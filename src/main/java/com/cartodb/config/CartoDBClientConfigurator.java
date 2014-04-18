package com.cartodb.config;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import com.cartodb.CartoDBClientIF;
import com.cartodb.CartoDBException;
import com.cartodb.impl.CartoDBClient;
import com.cartodb.impl.SecuredCartoDBClient;

public class CartoDBClientConfigurator {

	private static final String configurationFile = "/cartodb.properties";
	private static final String cartodbUserProperty = "cartodb.user";
	private static final String cartodbPasswordProperty = "cartodb.password";
	private static final String cartodbConsumerKeyProperty = "cartodb.consumer_key";
	private static final String cartodbConsumerSecretProperty = "cartodb.consumer_secret";
	private static final String cartodbApiKeyProperty = "cartodb.api_key";
	
	private static Properties getCartoDBProperties() throws CartoDBException{
		
		URL configurationResourceFile = CartoDBClientConfigurator.class.getResource(configurationFile);
		Properties cartodbProperties = new Properties();
		try {
			cartodbProperties.load(configurationResourceFile.openStream());		
		} catch (IOException e) {
			throw new CartoDBException("Unable to read properties file: "+configurationFile);
		}
		if (!cartodbProperties.containsKey(cartodbUserProperty) ||
			!cartodbProperties.containsKey(cartodbPasswordProperty)||
			!cartodbProperties.containsKey(cartodbConsumerKeyProperty) ||
			!cartodbProperties.containsKey(cartodbConsumerSecretProperty) ||
			!cartodbProperties.containsKey(cartodbApiKeyProperty)){
			
			throw new CartoDBException("Unable to read cartodb.user property from "+configurationFile);
		}
		
		return cartodbProperties;
	}
	
	public static CartoDBClientIF getReadOnlyCartoDBClient() throws CartoDBException{
		CartoDBClientIF cartoDBClient = null;
		
		Properties cartodbProperties = getCartoDBProperties();
		
		cartoDBClient = new CartoDBClient(cartodbProperties.getProperty(cartodbUserProperty));
		
		return cartoDBClient;
	}

	public static CartoDBClientIF getFullCartoDBClient() throws CartoDBException{
		CartoDBClientIF cartoDBClient=null;
		
		Properties cartodbProperties = getCartoDBProperties();
		cartoDBClient = new SecuredCartoDBClient(cartodbProperties.getProperty(cartodbUserProperty),cartodbProperties.getProperty(cartodbPasswordProperty),
												cartodbProperties.getProperty(cartodbConsumerKeyProperty),cartodbProperties.getProperty(cartodbConsumerSecretProperty));
		
		return cartoDBClient;
	}
}
