package com.cartodb;

import com.cartodb.config.CartoDBClientConfigurator;
import com.cartodb.dao.impl.SxlocationDAO;
import com.cartodb.model.Sxlocation;

public class AppTest {

	public static void main(String[] args) throws CartoDBException {
		// TODO Auto-generated method stub
		System.out.println("Hola mundo");
		Object resource= AppTest.class.getResource("/cartodb.properties");
		
		CartoDBClientIF clientRO = CartoDBClientConfigurator.getReadOnlyCartoDBClient();		
		CartoDBClientIF clientRW = CartoDBClientConfigurator.getFullCartoDBClient();
		
		SxlocationDAO sxlocationdao = new SxlocationDAO();
		//System.out.println(sxlocationdao.findAll());
		
		sxlocationdao.save(new Sxlocation());
		sxlocationdao.find(15629L);
	}

}
