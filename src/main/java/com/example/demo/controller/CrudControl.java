package com.example.demo.controller;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class CrudControl {

	JdbcTemplate db;
	
	public CrudControl() {
		DataSource dataSource = new DriverManagerDataSource();
    	((DriverManagerDataSource) dataSource).setDriverClassName("org.postgresql.Driver");
    	((DriverManagerDataSource) dataSource).setUrl("jdbc:postgresql://localhost:5432/template");
    	((DriverManagerDataSource) dataSource).setUsername("postgres");
    	((DriverManagerDataSource) dataSource).setPassword("root");

    	db = new JdbcTemplate(dataSource);
	}
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Object> getAllEntities(String entitie) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    	Class<?> clazz = Class.forName("com.example.demo.model."+entitie);
    	Object instance = clazz.getDeclaredConstructor().newInstance();
        return db.query("select * from "+entitie, new BeanPropertyRowMapper(instance.getClass()));
    }
    
    public List<String> getFieldsList(String nameClass) throws ClassNotFoundException {
    	Class<?> clazz = Class.forName("com.example.demo.model."+nameClass);
        List<String> fieldsList = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
        	String fieldName = field.getName();
            String capitalizedFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            fieldsList.add(capitalizedFieldName);
        }
        return fieldsList;
    }
    
    public void findByName() {
    	
    }
    
    public void findById() {
    	
    }
    
    public void deleteById() {
    	
    }
    
    public void insert() {
    	
    }

}
