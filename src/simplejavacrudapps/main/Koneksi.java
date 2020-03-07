/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplejavacrudapps.main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Farras Jibran
 */
public class Koneksi {
    //  Variable untuk menyimpan koneksi
        Connection conn;
        //  Variable untuk memberikan statement untuk nantinya di eksekusi
        Statement st;
        //  Variable untuk menyimpan hasil statement yang nanti nya akan di eksekusi
        ResultSet rs;

        private static Connection connection;

        public static
        Connection GetConnect()
            {
                try
                    {
                        String url = "jdbc:mysql://localhost:3306/db_sekolah";
                        String username = "root";
                        String password = "";

                        //  Koneksi mysql menggunakan jdbc driver
                        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                        connection = DriverManager.getConnection(url, username, password);
                        System.out.println("Koneksi Berhasil");
                    } catch (Exception e)
                    {
                        System.out.println(e);
                        System.err.println("Koneksi Gagal");
                    }

                //  Method untuk mengembalikan nilai
                return connection;
            };
}
