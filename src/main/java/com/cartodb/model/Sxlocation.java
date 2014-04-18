package com.cartodb.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

public class Sxlocation implements Serializable {
	
	private static final long serialVersionUID = -5064104743293724913L;
	
	
	private Long cartodb_id;
	private String address;
 
	private Boolean approved;
 
	private String country;
	private String description;
 
	private String id;
	private String lat;
	private String lng;
	private String region_code;
	private String sector;
	private String title;
	private String type;
	private String uri;

	private Date created_at;
	private Date updated_at;
	
	public Sxlocation(){
		super();
	}
	
	public Sxlocation(Long cartodb_id, String address, Boolean approved,
			String country, String description, String id, String lat,
			String lng, String region_code, String sector, String title,
			String type, String uri, Date created_at, Date updated_at) {
		super();
		this.cartodb_id = cartodb_id;
		this.address = address;
		this.approved = approved;
		this.country = country;
		this.description = description;
		this.id = id;
		this.lat = lat;
		this.lng = lng;
		this.region_code = region_code;
		this.sector = sector;
		this.title = title;
		this.type = type;
		this.uri = uri;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public Sxlocation(Object response) throws NoSuchFieldException, SecurityException{
		
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				String fieldName = field.getName();
				Object fieldType = field.getType();
				Field localField = this.getClass().getField(fieldName);
				Object remoteValue = ((Field) response).get(fieldName);
				localField.set(fieldType, remoteValue);
			
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	


}
