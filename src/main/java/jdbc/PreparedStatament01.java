package jdbc;


import java.sql.*;

public class PreparedStatament01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "147758");
        Statement st = con.createStatement();

        //1.ornek:Prepared Satetement kullanarak company adi IBM olan number_of_employees degerini 9999 olarak güncelleyin.

        //1.Adım: Prepared statement query'sini olustur

        String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ?";


        //2.Adim: preparedStatement objesini olustur.
        PreparedStatement pst1 = con.prepareStatement(sql1);


        //3.Adim: set...() method'lari ile soru isaretleri icin deger gir.
        pst1.setInt(1,9999);
        pst1.setString(2,"IBM");

        // 4.Adim: Execute query
        int updateRowSayisi = pst1.executeUpdate();
        System.out.println(updateRowSayisi + " satir guncellendi.");

        String sql2 = "SELECT * FROM companies";
        ResultSet result1 = st.executeQuery(sql2);

        while (result1.next()){
            System.out.println(result1.getInt(1)+"--"+result1.getString(2)+"--"+result1.getInt(3));
        }
        //3.Adim: set...() method'lari ile soru isaretleri icin deger gir.
        pst1.setInt(1,15000);
        pst1.setString(2,"GOOGLE");

        // 4.Adim: Execute query
        int updateRowSayisi2 = pst1.executeUpdate();
        System.out.println(updateRowSayisi + " satir guncellendi.");

        String sql3 = "SELECT * FROM companies";
        ResultSet result2 = st.executeQuery(sql2);

        while (result2.next()){
            System.out.println(result2.getInt(1)+"--"+result2.getString(2)+"--"+result2.getInt(3));
        }

        //2.ornek: "SELECT * FROM <table name>" query'sini prepared statement ile kullanin.
        System.out.println("===============");
        read_data(con,"companies");
  }

    // Birt talonun istenilen datasini PreparedStatement ile cagirtmak icin kullanilan () method.
    public static void read_data(Connection con, String tableName){

        try {

            String query = String.format("SELECT * FROM %s",tableName);// Format() method'u dinamik String olusturmak icin kullanilir.

            Statement statement = con.createStatement();
            // SQL query calistir.
            ResultSet rs = statement.executeQuery(query);//Datayi cagirip ResultSet konteynirina koyuyoruz

            while (rs.next()){// Tum datayi cagiralim
                System.out.println(rs.getInt(1)+"--"+rs.getString(2)+"--"+rs.getInt(3));
            }

        }catch (Exception e){
            System.out.println(e);

        }




    }
}
