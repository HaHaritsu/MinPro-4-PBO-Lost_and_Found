/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Pelapor;
import model.PelaporKehilangan;
import model.PelaporPenemu;

/**
 *
 * @author Harits
 */
public class PelaporDAO {

    public boolean tambah(Pelapor p) throws SQLException {
        String sql = "INSERT INTO pelapor "
                   + "(id_pelapor, nama, no_hp, jenis_pelapor, nik, lokasi_temuan) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, p.getIdPelapor());
            s.setString(2, p.getNama());
            s.setString(3, p.getNoHp());

            if (p instanceof PelaporKehilangan) {
                PelaporKehilangan pk = (PelaporKehilangan) p;
                s.setString(4, "KEHILANGAN");
                s.setString(5, pk.getNik());
                s.setNull(6, Types.VARCHAR);
            } else if (p instanceof PelaporPenemu) {
                PelaporPenemu pp = (PelaporPenemu) p;
                s.setString(4, "PENEMU");
                s.setNull(5, Types.VARCHAR);
                s.setString(6, pp.getLokasiTemuan());
            } else {
                throw new SQLException("Jenis pelapor tidak valid.");
            }

            return s.executeUpdate() > 0;
        }
    }

    public List<Pelapor> semua() throws SQLException {
        List<Pelapor> data = new ArrayList<>();
        String sql = "SELECT * FROM pelapor ORDER BY id_pelapor";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement s = c.prepareStatement(sql);
             ResultSet r = s.executeQuery()) {
            while (r.next()) {
                data.add(buatObjek(r));
            }
        }
        return data;
    }

    public Pelapor cari(String id) throws SQLException {
        String sql = "SELECT * FROM pelapor WHERE id_pelapor=?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    return buatObjek(r);
                }
            }
        }
        return null;
    }

    private Pelapor buatObjek(ResultSet r) throws SQLException {
        String id = r.getString("id_pelapor");
        String nama = r.getString("nama");
        String hp = r.getString("no_hp");
        String jenis = r.getString("jenis_pelapor");

        if ("KEHILANGAN".equals(jenis)) {
            return new PelaporKehilangan(
                id, nama, hp, r.getString("nik")
            );
        }

        return new PelaporPenemu(
            id, nama, hp, r.getString("lokasi_temuan")
        );
    }

    public boolean ubah(Pelapor p) throws SQLException {
        String sql = "UPDATE pelapor SET nama=?, no_hp=?, nik=?, "
                   + "lokasi_temuan=? WHERE id_pelapor=? AND jenis_pelapor=?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, p.getNama());
            s.setString(2, p.getNoHp());

            String jenis;
            if (p instanceof PelaporKehilangan) {
                s.setString(3, ((PelaporKehilangan) p).getNik());
                s.setNull(4, Types.VARCHAR);
                jenis = "KEHILANGAN";
            } else if (p instanceof PelaporPenemu) {
                s.setNull(3, Types.VARCHAR);
                s.setString(4, ((PelaporPenemu) p).getLokasiTemuan());
                jenis = "PENEMU";
            } else {
                throw new SQLException("Jenis pelapor tidak valid.");
            }

            s.setString(5, p.getIdPelapor());
            s.setString(6, jenis);
            return s.executeUpdate() > 0;
        }
    }

    public boolean hapus(String id) throws SQLException {
        String sql = "DELETE FROM pelapor WHERE id_pelapor=?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, id);
            return s.executeUpdate() > 0;
        }
    }
}