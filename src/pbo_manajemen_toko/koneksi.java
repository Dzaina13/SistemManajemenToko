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
public class koneksi {
     public static Connection koneksi;

    public static Connection konfigDB() throws SQLException {
        try {
            String url = "jdbc:mysql://localhost/db_toko";
            String user = "root";
            String pass = "";
            koneksi = DriverManager.getConnection(url, user, pass);
            //JOptionPane.showMessageDialog(null, "Berhasil koneksi!");
            System.out.println("Koneksi berhasil.");
        } catch (SQLException e) { 
            //JOptionPane.showMessageDialog(null, "Gagal koneksi: " + e.getMessage());
            System.err.println("Koneksi gagal: " + e.getMessage());
            throw e;
        }
        return koneksi;
    }
}
 