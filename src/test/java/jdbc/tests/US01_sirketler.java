package jdbc.tests;

import jdbc.utilities.DatabaseConnector;
import jdbc.utilities.DatabaseConnector2;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class US01_sirketler {
    List<Map<String, String>> list = new ArrayList<>();

    @Test
    public void TC0101() throws SQLException {
        // sirketler tablosunda toplam kac sirket ismi var
        String query = "select *\n" +
                "from orders\n" +
                "where order_id = 10250";
        list = DatabaseConnector2.getQueryResultWithAListMap(query);
        System.out.println("list = " + list.size());

    }

    @Test
    public void TC0102() throws SQLException {
        // almaya merkezi sirketlerin sayisi kac
        String query = "select *\n" +
                "from SIRKETLER";
        list = DatabaseConnector.getQueryAsAListOfMaps(query);
        System.out.println("list = " + list.size());


    }

    @Test
    public void TC0103(){
        //turkiye sirketlerinin toplam kac obonesi var


    }

    @Test
    public void TC0104(){
        //alman sirketlerinin ortalama kac obonesi var (veri yok ise hesaplamaya dahil etmeyin)


    }

    @Test
    public void TC0105(){
        //en cok abonesi olan 2. sirket hangisi

    }
}
