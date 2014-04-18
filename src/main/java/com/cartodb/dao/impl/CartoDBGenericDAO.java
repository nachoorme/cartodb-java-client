package com.cartodb.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import com.cartodb.CartoDBClientIF;
import com.cartodb.CartoDBException;
import com.cartodb.config.CartoDBClientConfigurator;
import com.cartodb.dao.CartoDBGenericDAOIF;
import com.cartodb.model.CartoDBResponse;
import com.cartodb.model.Sxlocation;

public class CartoDBGenericDAO<Entity, K extends Serializable> implements CartoDBGenericDAOIF<Entity, K>{

	 public Class<Entity> domainClass = getDomainClass();
	 
	  
	 protected Class getDomainClass() {
	   if (domainClass == null) {
		   ParameterizedType thisType = (ParameterizedType) getClass()
		     .getGenericSuperclass();
		   domainClass = (Class) thisType.getActualTypeArguments()[0];
	   }
	   return domainClass;
	 }
	
	 
	 protected String getTableName(){
		 ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		 Type firstOfThem = type.getActualTypeArguments()[0];
		 String[] splittedName = firstOfThem.toString().split("\\.");
		 String tableName=splittedName[splittedName.length - 1];
		 
		 return tableName.toLowerCase();
	 }
	
	 private CartoDBClientIF getCartoDBClient() throws CartoDBException {
		 return CartoDBClientConfigurator.getFullCartoDBClient();	  
	 }
	 
	public void save(Entity t) {
		Field[] fields = t.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				String fieldName = field.getName();
				Object value = field.get(t);
				System.out.println("Campo:"+fieldName + "Valor:"+value.toString());
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void update(Entity t) {
		
		
	}
	
	@SuppressWarnings("unchecked")
	public Entity find(K cartodb_id) throws CartoDBException {			
		CartoDBResponse<Map<String, Object>> res = getCartoDBClient().request("select * from "+ getTableName() +" where cartodb_id="+cartodb_id);
		Entity result=null;
		try {
			Object genericEntity = res.getRows().get(0); 
			result = (Entity) genericEntity;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return result;
	}

	public String findAll() throws CartoDBException {
		String result = getCartoDBClient().executeQuery("select * from "+ getTableName());
		return result;
	}

	public void delete(K cartodb_id) throws CartoDBException {
		String result = getCartoDBClient().executeQuery("delete from "+ getTableName() + " where cartodb_id="+cartodb_id);
		System.out.println(result);	
		
	}
	
}
