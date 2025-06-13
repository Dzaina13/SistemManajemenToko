/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo_manajemen_toko;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author nabil
 */
public class Koneksi {
     public static Connection koneksi;

    public static Connection konfigDB() throws SQLException {
        try {
            String url = "jdbc:mysql://localhost/db_toko";
            String user = "root";
            String pass = "13Juni2**5";
            koneksi = DriverManager.getConnection(url, user, pass);
            //JOptionPane.showMessageDialog(null, "Berhasil Koneksi!");
            System.out.println("Koneksi berhasil.");
        } catch (SQLException e) { 
            //JOptionPane.showMessageDialog(null, "Gagal Koneksi: " + e.getMessage());
            System.err.println("Koneksi gagal: " + e.getMessage());
            throw e;
        }
        return koneksi;
    }
}
 