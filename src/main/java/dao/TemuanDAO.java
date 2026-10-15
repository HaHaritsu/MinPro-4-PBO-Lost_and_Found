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
import model.Pelapor;
import model.PelaporPenemu;
import model.Temuan;

/**
 *
 * @author Harits
 */
public class TemuanDAO {

    public boolean tambah(Temuan t) throws SQLException {
        if (t.getPelapor() == null) {
            throw new SQLException("Pelapor penemu wajib diisi.");
        }

        String sql = "INSERT INTO temuan VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, t.getIdTemuan());
            s.setString(2, t.getBarang().getIdBarang());
            s.setString(3, t.getPelapor().getIdPelapor());
            s.setString(4, t.getLokasi());
            s.setString(5, t.getTanggal());
            s.setString(6, t.getStatus());

            try (PreparedStatement cek = c.prepareStatement(
                    "SELECT jenis_pelapor FROM pelapor WHERE id_pelapor=?")) {
                cek.setString(1, t.getPelapor().getIdPelapor());
                try (ResultSet r = cek.executeQuery()) {
                    if (!r.next() || !"PENEMU".equals(r.getString(1))) {
                        throw new SQLException(
                            "ID pelapor harus terdaftar sebagai PENEMU."
                        );
                    }
                }
            }

            return s.executeUpdate() > 0;
        }
    }

    public List<Temuan> semua() throws SQLException {
        List<Temuan> data = new ArrayList<>();
        String sql = "SELECT t.*, p.nama, p.no_hp, p.lokasi_temuan, "
                   + "b.nama_barang, b.kategori, b.warna, b.lokasi AS lokasi_barang "
                   + "FROM temuan t "
                   + "JOIN pelapor p ON t.id_pelapor=p.id_pelapor "
                   + "JOIN barang b ON t.id_barang=b.id_barang "
                   + "WHERE p.jenis_pelapor='PENEMU' "
                   + "ORDER BY t.id_temuan";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement s = c.prepareStatement(sql);
             ResultSet r = s.executeQuery()) {
            while (r.next()) {
                Barang barang = new Barang(
                    r.getString("id_barang"),
                    r.getString("nama_barang"),
                    r.getString("kategori"),
                    r.getString("warna"),
                    r.getString("lokasi_barang")
                );

                PelaporPenemu pelapor = new PelaporPenemu(
                    r.getString("id_pelapor"),
                    r.getString("nama"),
                    r.getString("no_hp"),
                    r.getString("lokasi_temuan")
                );

                data.add(new Temuan(
                    r.getString("id_temuan"), barang, pelapor,
                    r.getString("lokasi"), r.getString("tanggal"),
                    r.getString("status")
                ));
            }
        }
        return data;
    }

    public boolean ubah(Temuan t) throws SQLException {
        String sql = "UPDATE temuan SET id_barang=?, id_pelapor=?, lokasi=?, "
                   + "tanggal=?, status=? WHERE id_temuan=?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, t.getBarang().getIdBarang());
            s.setString(2, t.getPelapor().getIdPelapor());
            s.setString(3, t.getLokasi());
            s.setString(4, t.getTanggal());
            s.setString(5, t.getStatus());
            s.setString(6, t.getIdTemuan());

            try (PreparedStatement cek = c.prepareStatement(
                    "SELECT jenis_pelapor FROM pelapor WHERE id_pelapor=?")) {
                cek.setString(1, t.getPelapor().getIdPelapor());
                try (ResultSet r = cek.executeQuery()) {
                    if (!r.next() || !"PENEMU".equals(r.getString(1))) {
                        throw new SQLException("ID pelapor bukan pelapor penemu.");
                    }
                }
            }

            return s.executeUpdate() > 0;
        }
    }

    public boolean hapus(String id) throws SQLException {
        String sql = "DELETE FROM temuan WHERE id_temuan=?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, id);
            return s.executeUpdate() > 0;
        }
    }
}