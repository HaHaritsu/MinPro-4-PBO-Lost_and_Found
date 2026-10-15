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
import model.Laporan;
import model.Pelapor;
import model.PelaporKehilangan;

/**
 *
 * @author Harits
 */
public class LaporanDAO {

    public boolean tambah(Laporan l) throws SQLException {
        if (!(l.getPelapor() instanceof PelaporKehilangan)) {
            throw new SQLException(
                "Laporan kehilangan harus menggunakan PelaporKehilangan."
            );
        }

        String sql = "INSERT INTO laporan VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, l.getIdLaporan());
            s.setString(2, l.getPelapor().getIdPelapor());
            s.setString(3, l.getBarang().getIdBarang());
            s.setString(4, l.getTanggal());
            s.setString(5, l.getLokasi());
            s.setString(6, l.getKeterangan());
            s.setString(7, l.getStatus());
            return s.executeUpdate() > 0;
        }
    }

    public List<Laporan> semua() throws SQLException {
        List<Laporan> data = new ArrayList<>();
        String sql = "SELECT l.*, p.nama, p.no_hp, p.nik, "
                   + "b.nama_barang, b.kategori, b.warna, b.lokasi AS lokasi_barang "
                   + "FROM laporan l "
                   + "JOIN pelapor p ON l.id_pelapor=p.id_pelapor "
                   + "JOIN barang b ON l.id_barang=b.id_barang "
                   + "ORDER BY l.id_laporan";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement s = c.prepareStatement(sql);
             ResultSet r = s.executeQuery()) {
            while (r.next()) {
                Pelapor pelapor = new PelaporKehilangan(
                    r.getString("id_pelapor"),
                    r.getString("nama"),
                    r.getString("no_hp"),
                    r.getString("nik")
                );

                Barang barang = new Barang(
                    r.getString("id_barang"),
                    r.getString("nama_barang"),
                    r.getString("kategori"),
                    r.getString("warna"),
                    r.getString("lokasi_barang")
                );

                data.add(new Laporan(
                    r.getString("id_laporan"), pelapor, barang,
                    r.getString("tanggal"), r.getString("lokasi"),
                    r.getString("keterangan"), r.getString("status")
                ));
            }
        }
        return data;
    }

    public boolean ubah(Laporan l) throws SQLException {
        if (!(l.getPelapor() instanceof PelaporKehilangan)) {
            throw new SQLException("Pelapor laporan harus bertipe kehilangan.");
        }

        String sql = "UPDATE laporan SET id_pelapor=?, id_barang=?, tanggal=?, "
                   + "lokasi=?, keterangan=?, status=? WHERE id_laporan=?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, l.getPelapor().getIdPelapor());
            s.setString(2, l.getBarang().getIdBarang());
            s.setString(3, l.getTanggal());
            s.setString(4, l.getLokasi());
            s.setString(5, l.getKeterangan());
            s.setString(6, l.getStatus());
            s.setString(7, l.getIdLaporan());
            return s.executeUpdate() > 0;
        }
    }

    public boolean hapus(String id) throws SQLException {
        String sql = "DELETE FROM laporan WHERE id_laporan=?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, id);
            return s.executeUpdate() > 0;
        }
    }
}