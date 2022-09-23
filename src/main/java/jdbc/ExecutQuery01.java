package jdbc;

import java.sql.*;

public class ExecutQuery01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "147758");
        Statement st = con.createStatement();

        //1.ornek: companies toblosundan en yuksek ikinci number_of_employees degeri olan company
        // ve number_of_employees degerlerini cagirin.
        //1.yol offset ve fetch next kullanarak
        String sql1 = "SELECT company,number_of_employees \n" +
                "FROM companies\n" +
                "order by number_of_employees desc\n" +
                "offset 1 row\n" +
                "fetch next 1 row only";

        ResultSet result1 = st.executeQuery(sql1);
        while (result1.next()) {
            System.out.println(result1.getString("company") + "--" + result1.getInt("number_of_employees"));
        }
        // 2.yol: Subquery ile
        String sql2 = "SELECT company,number_of_employees \n" +
                "FROM companies\n" +
                "Where number_of_employees = (select max(number_of_employees)\n" +
                "                            from companies\n" +
                "                            WHERE number_of_employees < (select max(number_of_employees)\n" +
                "                            from companies))";


        ResultSet result2 = st.executeQuery(sql2);
        while (result2.next()) {
            System.out.println(result2.getString("company") + "--" + result2.getInt("number_of_employees"));
        }
        con.close();
        st.close();
        result1.close();
        result2.close();
    }
}
