/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pertemuan14;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author nabil
 */
public class Koneksi {
    
    public static Connection koneksi;

    public static Connection konfigDB() throws SQLException {
        try {
            String url = "jdbc:mysql://localhost/db_perusahaan";
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


    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) throws SQLException {
//        // TODO code application logic here
//         Connection c = (Connection)Koneksi.konfigDB();
//
//    }
    
}
