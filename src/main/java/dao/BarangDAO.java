/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Barang;

/**
 *
 * @author Harits
 */
public class BarangDAO {

    public boolean tambah(Barang b) throws SQLException {
        String sql = "INSERT INTO barang VALUES (?, ?, ?, ?, ?)";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, b.getIdBarang());
            p.setString(2, b.getNamaBarang());
            p.setString(3, b.getKategori());
            p.setString(4, b.getWarna());
            p.setString(5, b.getLokasi());
            return p.executeUpdate() > 0;
        }
    }

    public List<Barang> semua() throws SQLException {
        List<Barang> data = new ArrayList<>();
        String sql = "SELECT * FROM barang ORDER BY id_barang";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement p = c.prepareStatement(sql);
             ResultSet r = p.executeQuery()) {
            while (r.next()) {
                data.add(new Barang(
                    r.getString("id_barang"),
                    r.getString("nama_barang"),
                    r.getString("kategori"),
                    r.getString("warna"),
                    r.getString("lokasi")
                ));
            }
        }
        return data;
    }

    public Barang cari(String id) throws SQLException {
        String sql = "SELECT * FROM barang WHERE id_barang = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, id);

            try (ResultSet r = p.executeQuery()) {
                if (r.next()) {
                    return new Barang(
                        r.getString("id_barang"),
                        r.getString("nama_barang"),
                        r.getString("kategori"),
                        r.getString("warna"),
                        r.getString("lokasi")
                    );
                }
            }
        }
        return null;
    }

    public boolean ubah(Barang b) throws SQLException {
        String sql = "UPDATE barang SET nama_barang=?, kategori=?, "
                   + "warna=?, lokasi=? WHERE id_barang=?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, b.getNamaBarang());
            p.setString(2, b.getKategori());
            p.setString(3, b.getWarna());
            p.setString(4, b.getLokasi());
            p.setString(5, b.getIdBarang());
            return p.executeUpdate() > 0;
        }
    }

    public boolean hapus(String id) throws SQLException {
        String sql = "DELETE FROM barang WHERE id_barang=?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, id);
            return p.executeUpdate() > 0;
        }
    }
}