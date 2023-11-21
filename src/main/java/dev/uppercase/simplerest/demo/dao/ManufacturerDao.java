package dev.uppercase.simplerest.demo.dao;


import dev.uppercase.simplerest.demo.connector.ConnectionManager;
import dev.uppercase.simplerest.demo.entity.Manufacturer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerDao implements Dao<String, Manufacturer>{


    public int maxId() {
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            Manufacturer manufacturer = new Manufacturer();
            String sqlQuery = "select MAX(id) as maxKey from test.Manufacturers";
            connection =  new ConnectionManager().getConnection();
            statement = connection.prepareStatement(sqlQuery);
            resultSet = statement.executeQuery();
            resultSet.next();
            int temp = resultSet.getInt("maxKey");
            System.out.println("is: " + temp);
            return ++temp;
        } catch (Exception e) {
            System.out.println("ошибка получения");
            throw new RuntimeException(e);
        } finally {
            close(resultSet, statement, connection);
        }
    }


    @Override
    public Manufacturer get(String id) {
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            Manufacturer manufacturer = new Manufacturer();
            String sqlQuery = "select * from test.Manufacturers where id like '" + id +"'";
            connection =  new ConnectionManager().getConnection();
            statement = connection.prepareStatement(sqlQuery);
            resultSet = statement.executeQuery();
            resultSet.next();
            manufacturer.setId(resultSet.getInt("id"));
            manufacturer.setName_of_manufacturer(resultSet.getString("name_of_manufacturer"));
            manufacturer.setCountry(resultSet.getString("country"));
            return manufacturer;
        } catch (Exception e) {
            System.out.println("ошибка получения");
            throw new RuntimeException(e);
        } finally {
            close(resultSet, statement, connection);
        }
    }

    @Override
    public List<Manufacturer> getAll()  {
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            List<Manufacturer> list = new ArrayList<>();
            String sqlQuery = "select * from test.Manufacturers";
            connection =  new ConnectionManager().getConnection();
            statement = connection.prepareStatement(sqlQuery);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Manufacturer manufacturer = new Manufacturer();
                manufacturer.setId(resultSet.getInt("id"));
                manufacturer.setName_of_manufacturer(resultSet.getString("name_of_manufacturer"));
                manufacturer.setCountry(resultSet.getString("country"));
                list.add(manufacturer);
            }
            return list;
        } catch (Exception e) {
            System.out.println("ошибка получения");
            throw new RuntimeException(e);
        } finally {
            close(resultSet, statement, connection);
        }
    }

    public List<Manufacturer> getByDate(String str)  {
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            List<Manufacturer> list = new ArrayList<>();
            String sqlQuery = "select DISTINCT id, name_of_manufacturer," +
                    "                            country from test.Manufacturers" +
                    "                            inner join test.Souvenirs on id = id_manufacturer where year(date_of_release) = year('" + str+"')";
            connection =  new ConnectionManager().getConnection();
            statement = connection.prepareStatement(sqlQuery);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Manufacturer manufacturer = new Manufacturer();
                manufacturer.setId(resultSet.getInt("id"));
                manufacturer.setName_of_manufacturer(resultSet.getString("name_of_manufacturer"));
                manufacturer.setCountry(resultSet.getString("country"));
                list.add(manufacturer);
            }
            return list;
        } catch (Exception e) {
            System.out.println("ошибка получения");
            throw new RuntimeException(e);
        } finally {
            close(resultSet, statement, connection);
        }
    }

    public List<Manufacturer> getByPrice(String str)  {
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            List<Manufacturer> list = new ArrayList<>();
            String sqlQuery = "select DISTINCT id, name_of_manufacturer," +
                    "                            country from test.Manufacturers " +
                    "                            inner join test.Souvenirs on id = id_manufacturer where price <" + str;
            connection =  new ConnectionManager().getConnection();
            statement = connection.prepareStatement(sqlQuery);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Manufacturer manufacturer = new Manufacturer();
                manufacturer.setId(resultSet.getInt("id"));
                manufacturer.setName_of_manufacturer(resultSet.getString("name_of_manufacturer"));
                manufacturer.setCountry(resultSet.getString("country"));
                list.add(manufacturer);
            }
            return list;
        } catch (Exception e) {
            System.out.println("ошибка получения");
            throw new RuntimeException(e);
        } finally {
            close(resultSet, statement, connection);
        }
    }

    @Override
    public boolean add(Manufacturer manufacturer) {
        PreparedStatement statement = null;
        Connection connection = null;
        boolean flag = false;
        try{
            System.out.println(manufacturer);
            String sqlQuery = "insert into test.Manufacturers values ("+ maxId() + ",'" + manufacturer.getName_of_manufacturer() + "','"
                    + manufacturer.getCountry() + "')";
            connection =  new ConnectionManager().getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.executeUpdate();
            flag = true;
        } catch (Exception e) {
            System.out.println("ошибка добавления");
            throw new RuntimeException(e);
        }finally {
            close(null, statement, connection);
            return flag;
        }
    }


    @Override
    public boolean update(Manufacturer manufacturer, Manufacturer param) {
        delete(manufacturer);
        add(param);
        return true;
    }

    @Override
    public boolean delete(Manufacturer manufacturer)  {
        PreparedStatement statement = null;
        Connection connection = null;
        boolean flag = false;
        try{
            String sqlQuery = "delete from test.Manufacturers where id=" + manufacturer.getId();
            connection =  new ConnectionManager().getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.executeUpdate();
            flag = true;
        } catch (Exception e) {
            System.out.println("ошибка удаления");
            throw new RuntimeException(e);
        }finally {
            close(null, statement, connection);
            return flag;
        }
    }

    public boolean delete(String id)  {
        PreparedStatement statement = null;
        Connection connection = null;
        boolean flag = false;
        try{
            String sqlQuery = "delete from test.Manufacturers where id=" + id;
            connection =  new ConnectionManager().getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.executeUpdate();
            flag = true;
        } catch (Exception e) {
            System.out.println("ошибка удаления");
            throw new RuntimeException(e);
        }finally {
            close(null, statement, connection);
            return flag;
        }
    }
}

