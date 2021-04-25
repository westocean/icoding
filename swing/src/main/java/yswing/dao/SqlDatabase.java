package yswing.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlDatabase {

    private Connection connection;

    private void connect(String daName){
        try {
            Class.forName("org.sqlite.JDBC");
            connection =  DriverManager.getConnection("jdbc:sqlite:" + daName);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private Statement getStatement(){
        Statement statement = null;
        try {
            if(connection == null){
                connect("ui.db");
            }
            if(connection != null){
                statement = connection.createStatement();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }


    public JSONArray querySql(String sql) throws SQLException {
        JSONArray jsonArray = new JSONArray();
        Statement statement = getStatement();
        if(statement != null){
            ResultSet resultSet = statement.executeQuery(sql);

            List<ColumnInfo> columnInfos = new ArrayList<>();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int ct =metaData.getColumnCount();
            for (int i=0; i<ct; i++){
                ColumnInfo columnInfo = new ColumnInfo();
                columnInfo.idx = i+1;
                columnInfo.name=metaData.getColumnName(i+1);
                columnInfo.valueType = metaData.getColumnTypeName(i+1);
                columnInfos.add(columnInfo);
            }

            while (resultSet.next()){
                jsonArray.add(toObj(resultSet, columnInfos));
            }
            statement.close();
        }
        return jsonArray;
    }

    private JSONObject toObj(ResultSet resultSet, List<ColumnInfo> columnInfos) throws SQLException {
        JSONObject jsonObject = new JSONObject();
        for(ColumnInfo columnInfo : columnInfos){
            Object val = getValue(resultSet, columnInfo);
            jsonObject.put(columnInfo.name, val);
        }
        return jsonObject;
    }

    private Object getValue(ResultSet resultSet, ColumnInfo columnInfo) throws SQLException {
        switch (columnInfo.valueType){
            case "INT":
                return resultSet.getInt(columnInfo.idx);
            case "TEXT":
                return resultSet.getString(columnInfo.idx);
            default:
                return resultSet.getObject(columnInfo.idx);
        }
    }

    public int executeSql(String sql) throws SQLException {
        int result = -1;
        Statement statement = getStatement();
        if(statement != null){
            result = statement.executeUpdate(sql);
            statement.close();
        }
        return result;
    }


    public static void main(String[] args) {
        try {
            SqlDatabase sqlDatabase = new SqlDatabase();
//            int res = sqlDatabase.executeSql("create table books(ID INT PRIMARY KEY, NAME TEXT, PAY INT);");
//            System.out.println(res);
            // sqlDatabase.executeSql("INSERT INTO BOOKS VALUES (1, 'think in java', 10), (2, 'think in cpp', 15);");
            JSONArray d = sqlDatabase.querySql("select * from books;");
            System.out.println(d.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
