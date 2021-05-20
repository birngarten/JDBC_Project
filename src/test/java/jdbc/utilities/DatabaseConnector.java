package jdbc.utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseConnector {

    private static final String username = ConfigurationReader.getProperty("userName");
    private static final String password = ConfigurationReader.getProperty("password");
    private static final String url      = ConfigurationReader.getProperty("url");

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static ResultSetMetaData rsmd;

    public static ResultSet getResultSet(String query){

        try {
            connection = DriverManager.getConnection(url,username,password);
            if (connection != null){
                System.out.println("EN: Connected to the database...");
            } else {
                System.out.println("EN: Database connection failed");
            }

            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(query);

        } catch (SQLException sqlEx) {
            System.out.println("SQL Exception:" + sqlEx.getStackTrace());
        }
        return resultSet;
    }

    //query sonucunu list map seklinde almak icin bu method kullanilabilir
    public static List<Map<String,String>> getQueryAsAListOfMaps(String query) throws SQLException {
        resultSet=getResultSet(query);
        rsmd = resultSet.getMetaData();
        int sizeOfColumns=rsmd.getColumnCount();
        List<String> nameOfColumnsList=new ArrayList<>();
        for (int i=1;i<=rsmd.getColumnCount();i++){
            nameOfColumnsList.add(rsmd.getColumnName(i));
        }
        resultSet.beforeFirst();

        List<Map<String,String>> listOfResultset=new ArrayList<>();
        while (resultSet.next()){
            Map<String,String> mapOfEachRow=new HashMap<>();
            for (int j=0;j<sizeOfColumns;j++)
            {
                mapOfEachRow.put(nameOfColumnsList.get(j),resultSet.getString(nameOfColumnsList.get(j)));
            }
            listOfResultset.add(mapOfEachRow);
        }
        return listOfResultset;
    }

    public static void closeConnection() {
        try {
            if (resultSet != null) { //icten disa dogru kapatmak daha uygundur
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}