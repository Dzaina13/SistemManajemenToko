/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package manajemen_toko;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 *
 * @author Syahrul Ramadhan
 */
public class KoneksiDB {
    public static Connection mysqlconfig;
    public static Connection configDB() throws SQLException {
    try {
        String url="jdbc:mysql://localhost/db_toko";
        String user="root";
        String pass="13Juni2**5";
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        mysqlconfig = DriverManager.getConnection(url, user, pass);
        //JOptionPane.showMessageDialog(null, "Berhasil Koneksi");   
        } catch (SQLException e) {
        //JOptionPane.showMessageDialog(null, "Gagal Koneksi");  
        System.err.println("Koneksi Gagal " + e.getMessage());
        }
        
        return mysqlconfig;
    }
}
