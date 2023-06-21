package com.example.demo.controller;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.example.demo.model.InfoClass;

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
	public List<Object> getAllEntities(String entitie, int page) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    	Class<?> clazz = Class.forName("com.example.demo.model."+entitie);
    	Object instance = clazz.getDeclaredConstructor().newInstance();
    	List<Object> obj = db.query("select * from "+entitie, new BeanPropertyRowMapper(instance.getClass()));
    	int start = (page*5) -1 ;
    	int end = (page*5) + 5;
    	if (start < 0) {
			start = 0;
		}
    	if (end>obj.size()) {
			end = obj.size();
		}
    	
    	List<Object> subList = obj.subList(start, end);
        return subList;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Object> getAllEntities(String entitie) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    	Class<?> clazz = Class.forName("com.example.demo.model."+entitie);
    	Object instance = clazz.getDeclaredConstructor().newInstance();
    	List<Object> obj = db.query("select * from "+entitie, new BeanPropertyRowMapper(instance.getClass()));
        return obj;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Object> getAllEntities(String entitie,String sql) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    	Class<?> clazz = Class.forName("com.example.demo.model."+entitie);
    	Object instance = clazz.getDeclaredConstructor().newInstance();
    	List<Object> obj = db.query(sql, new BeanPropertyRowMapper(instance.getClass()));
        return obj;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Integer> paginate(int page,String entitie) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    	Class<?> clazz = Class.forName("com.example.demo.model."+entitie);
    	Object instance = clazz.getDeclaredConstructor().newInstance();
    	List<Object> obj = db.query("select * from "+entitie, new BeanPropertyRowMapper(instance.getClass()));
    	List<Integer> taille = new ArrayList<>();
    	int start = (page*5) -1 ;
    	int end = (page*5) + 5;
    	if (start < 0) {
			start = 0;
		}
    	if (end>obj.size()) {
			end = obj.size();
		}
    	taille.add(start);
    	taille.add(end);
    	return taille;
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
    
    public List<InfoClass> getInfoClass(String nameClass) throws ClassNotFoundException {
    	Class<?> clazz = Class.forName("com.example.demo.model."+nameClass);
        List<InfoClass> fieldsList = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        InfoClass infoClass;
        for (Field field : fields) {
        	String fieldName = field.getName();
        	Class<?> typeField = field.getType();
            String capitalizedFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            infoClass = new InfoClass();
            infoClass.setField(capitalizedFieldName);
            infoClass.setType(typeField.getSimpleName());
            infoClass.setValue("");
            fieldsList.add(infoClass);
        }
        return fieldsList;
    }
    
    public List<InfoClass> getInfoClass(String nameClass , Object object) throws ClassNotFoundException {
    	Class<?> clazz = object.getClass();
        List<InfoClass> fieldsList = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        InfoClass infoClass;
        for (Field field : fields) {
        	String fieldName = field.getName();
        	Class<?> typeField = field.getType();
            String capitalizedFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            infoClass = new InfoClass();
            infoClass.setField(capitalizedFieldName);
            infoClass.setType(typeField.getSimpleName());
            try {
                Method getter = clazz.getMethod("get" + capitalizedFieldName);
                Object value = getter.invoke(object);
                if (value != null) {
                    infoClass.setValue(value.toString());
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {}
            fieldsList.add(infoClass);
        }
        return fieldsList;
    }
    
    public Object initialisationEntitie(String nameClass , String[] data) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    	Class<?> clazz = Class.forName("com.example.demo.model."+nameClass);
    	Object instance = clazz.getDeclaredConstructor().newInstance();
    	Method[] methods = clazz.getDeclaredMethods();
    	int i = 0;
    	String fieldName;
    	String value;
    	Class<?> parameterType;
    	Object convertedValue;
    	List<String> list = getFieldsList(nameClass);
    	for (String string : list) {
    		for (Method method : methods) {
    			if (method.getName().startsWith("set"+string)) {
    				fieldName = method.getName().substring(3);
    				fieldName = Character.toLowerCase(fieldName.charAt(0)) + fieldName.substring(1);
    				value = data[i];
    				i++;
    				parameterType = method.getParameterTypes()[0];
    				convertedValue = convertValue(value, parameterType);
    				method.invoke(instance, convertedValue);
    			}
    		}
		}
    	return instance;
	}
    
    private Object convertValue(String value, Class<?> parameterType) {
        if (parameterType == String.class) {
            return value;
        } else if (parameterType == int.class || parameterType == Integer.class) {
            return Integer.parseInt(value);
        } else if (parameterType == long.class || parameterType == Long.class) {
            return Long.parseLong(value);
        } else if (parameterType == double.class || parameterType == Double.class) {
            return Double.parseDouble(value);
        } else if (parameterType == float.class || parameterType == Float.class) {
            return Float.parseFloat(value);
        } else if (parameterType == boolean.class || parameterType == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else if (parameterType == Date.class || parameterType == Date.class) {
            return Date.valueOf(value);
        } else {
            return null;
        }
    }

    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Object> findById(String nameClass , long id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    	Class<?> clazz = Class.forName("com.example.demo.model."+nameClass);
    	Object instance = clazz.getDeclaredConstructor().newInstance();
    	return db.query("select * from "+nameClass+" where id="+id, new BeanPropertyRowMapper(instance.getClass()));

    }
    
    public void findByName() {

	}
    
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteById(String nameClass , long id) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
    	try {
    		Class<?> clazz = Class.forName("com.example.demo.model."+nameClass);
        	Object instance = clazz.getDeclaredConstructor().newInstance();
        	db.query("delete from "+nameClass+" where id="+id, new BeanPropertyRowMapper(instance.getClass()));
		} catch (Exception e) {
		}
    	
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void insert(String[] datas, String nameClass) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
    	Class<?> clazz = Class.forName("com.example.demo.model."+nameClass);
    	Object instance = clazz.getDeclaredConstructor().newInstance();
    	List<String> list = getFieldsList(nameClass);
    	String sql = "INSERT INTO " + nameClass + " (" ;
    	for (int i = 0 ; i < list.size() ; i++) {
			if(!list.get(i).equals("Id") && i!=list.size()-1) {
				sql += list.get(i) + ",";
			}
			else if(!list.get(i).equals("Id") && i==list.size()-1) {
				sql += list.get(i);
			}
		}
    	sql += ") values (";
    	for (int i = 1 ; i < datas.length ; i++) {
			if(i!=datas.length-1) {
				sql += "'" + datas[i] + "',";
			}
			else if(i==datas.length-1) {
				sql += "'" + datas[i] + "'";
			}
		}
    	sql += ")";
    	System.out.println(sql);
    	try {
    		db.query(sql, new BeanPropertyRowMapper(instance.getClass()));
		} catch (Exception e) {
		}
    	
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void update(String[] datas, String nameClass) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
    	Class<?> clazz = Class.forName("com.example.demo.model."+nameClass);
    	Object instance = clazz.getDeclaredConstructor().newInstance();
    	List<String> list = getFieldsList(nameClass);
    	String sql = "update "+nameClass+" set ";
    	for (int i = 0 ; i < list.size() ; i++) {
    		if(!list.get(i).equals("Id") && i!=list.size()-1) {
				sql += list.get(i)+"='"+datas[i]+"',";
			}
			else if(!list.get(i).equals("Id") && i==list.size()-1) {
				sql += list.get(i)+"='"+datas[i]+"'";
			}
    	}
    	sql += " where id="+datas[0];
    	System.out.println(sql);
    	try {
    		db.query(sql, new BeanPropertyRowMapper(instance.getClass()));
		} catch (Exception e) {
		}
    }

}
