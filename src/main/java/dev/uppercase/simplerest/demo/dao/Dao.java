package dev.uppercase.simplerest.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


    public interface Dao<K, E> {


        E get(K k);

        List<E> getAll();


        boolean add(E t);


        boolean update(E t, E param);


        boolean delete(E t);


        default void close(ResultSet rs, PreparedStatement pstmt, Connection con){
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
