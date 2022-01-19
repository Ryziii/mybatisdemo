package com.rysiw.demo.common.utils;

import com.rysiw.demo.exception.DefineException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rysiw
 * @date 2022/1/18 23:26
 */
public class DBUtils {

    private String url;
    private String username;
    private String password;
    private Connection connection = null;

    public DBUtils(String url, String username, String password)  {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DefineException(e);
        }
    }

    public List<Map<String, Object>> executor(String sql){
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnNum = metaData.getColumnCount();
            List<Map<String, Object>> list = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> rowData = new HashMap<>();
                for (int i = 1; i <= columnNum; i++) {
                    rowData.put(metaData.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DefineException(e);
        }

    }

    public void close(){
        try {
            connection.close();
        } catch (Exception e) {
            throw new DefineException(e);
        }
    }
}
