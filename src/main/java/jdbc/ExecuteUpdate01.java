package jdbc;

import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "147758");
        Statement st = con.createStatement();

        //1.ornek: number_of_employees degeri ortalama calisan sayisindan az olan number_of_employees degerlerini
        //1600 olarak UPDADATE edin.

        String sql1 = "update companies\n" +
                "set number_of_employees = 16000\n" +
                "where number_of_employees < (select avg (number_of_employees)\n" +
                "                            from companies)";

        int updateSatirSayisi = st.executeUpdate(sql1);// update edilen satir sayisini verir
        System.out.println("updateSatirSayisi = " + updateSatirSayisi);

        String sql2 =" SELECT * FROM companies";
        ResultSet result1 = st.executeQuery(sql2);

        while (result1.next()){
            System.out.println(result1.getInt(1)+"--"+ result1.getString(2)+"--"+result1.getInt(3));

        }
        con.close();
        st.close();
        result1.close();
    }
}
