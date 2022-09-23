package jdbc;

import java.sql.*;

public class Execute02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
   // Not : EXECUTE (Calistir anlamina geliyor)
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","147758");
        Statement st = con.createStatement();

    //1.ornek: region id'si 1 olan "country_name" degerlerini cagirin
   // not: SELECT demek cagirmak anlamina gelir yani  date query.
    String sql1 ="SELECT country_name FROM countries WHERE region_id = 1";
    boolean r1 = st.execute(sql1);
    System.out.println(r1);// true yada false verir cunku data cagirma islemi yaptik.

    // Recordlari gormek icin executeQuery() methodu kullanmaliyiz
    ResultSet result1 = st.executeQuery(sql1);

    while (result1.next()) {

        System.out.println(result1.getString("country_name"));
    }
        //2.ornek:"region_id"'nin 2'den buyuk oldugu "country_id"
        // ve "country_name" degerlerini cagirin.

        String sql2 = "SELECT country_name, country_id FROM countries WHERE region_id > 2 ";
        ResultSet result2 = st.executeQuery(sql2);
        while (result2.next()){
            System.out.println(result2.getString("country_id")+ "-->"+result2.getString("country_name"));
        }

    //3.ornek: "number_of_emplyoees" degeri en dusuk olan satirin tum degerlerini cagirin

        String sql3 = "SELECT * FROM companies WHERE number_of_employees = (SELECT MIN(number_of_employees)FROM companies) ";
        ResultSet result3 = st.executeQuery(sql3);

        while (result3.next()){
            System.out.println(result3.getInt("company_id")+"--"+result3.getString("company")+"--"+result3.getInt("number_of_employees"));

        }
        con.createStatement();
        st.close();

      }

    }

