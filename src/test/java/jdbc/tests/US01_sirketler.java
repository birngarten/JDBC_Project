package jdbc.tests;

import jdbc.utilities.DatabaseConnector;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class US01_sirketler {
    List<Map<String, String>> list;

    @Test
    public void TC0101() throws SQLException {
        // sirketler tablosunda toplam kac sirket ismi var
        String query = "SELECT count(sirket_adi)\n" +
                        "FROM sirketler";
        list = DatabaseConnector.getQueryAsAListOfMaps(query);
        System.out.println("list = " + list.size());

    }

    @Test
    public void TC0102() throws SQLException {
        // almaya merkezi sirketlerin sayisi kac
        String query = "select count(merkez_ulke)\n" +
                "from sirketler\n" +
                "where merkez_ulke = 'germany';";
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
