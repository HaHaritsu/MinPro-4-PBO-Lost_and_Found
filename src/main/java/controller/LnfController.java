/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import config.DatabaseConnection;
import dao.BarangDAO;
import dao.PelaporDAO;
import dao.LaporanDAO;
import dao.TemuanDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Barang;
import model.Laporan;
import model.Pelapor;
import model.PelaporKehilangan;
import model.PelaporPenemu;
import model.Temuan;

/**
 *
 * @author Harits
 */
public class LnfController {

    private final BarangDAO barangDAO = new BarangDAO();
    private final PelaporDAO pelaporDAO = new PelaporDAO();
    private final LaporanDAO laporanDAO = new LaporanDAO();
    private final TemuanDAO temuanDAO = new TemuanDAO();

    public boolean tesKoneksi() {
        try (Connection c = DatabaseConnection.getConnection()) {
            return c != null && !c.isClosed();
        } catch (SQLException e) {
            System.out.println("Koneksi database gagal: " + e.getMessage());
            return false;
        }
    }

    private void tampilkanError(SQLException e) {
        System.out.println("Operasi database gagal: " + e.getMessage());
    }

    public String generateIdBarang() {
        int nomor = 1;

        while (true) {
            String id = String.format("B%03d", nomor);

            if (cariBarang(id) == null) {
                return id;
            }

            nomor++;
        }
    }

    public void tambahBarang(Barang barang) {
        try {
            if (barangDAO.tambah(barang)) {
                System.out.println("Barang berhasil disimpan ke database.");
            }
        } catch (SQLException e) {
            tampilkanError(e);
        }
    }

    public ArrayList<Barang> getDaftarBarang() {
        try {
            return new ArrayList<>(barangDAO.semua());
        } catch (SQLException e) {
            tampilkanError(e);
            return new ArrayList<>();
        }
    }

    public Barang cariBarang(String id) {
        try {
            return barangDAO.cari(id);
        } catch (SQLException e) {
            tampilkanError(e);
            return null;
        }
    }

    public Barang cariBarang(String nama, String kategori) {
        try {
            for (Barang barang : barangDAO.semua()) {
                if (barang.getNamaBarang().equalsIgnoreCase(nama)
                        && barang.getKategori().equalsIgnoreCase(kategori)) {
                    return barang;
                }
            }
        } catch (SQLException e) {
            tampilkanError(e);
        }

        return null;
    }

    public boolean hapusBarang(String id) {
        try {
            return barangDAO.hapus(id);
        } catch (SQLException e) {
            tampilkanError(e);
            return false;
        }
    }

    public String generateIdPelapor() {
        int nomor = 1;

        while (true) {
            String id = String.format("P%03d", nomor);

            if (cariPelapor(id) == null) {
                return id;
            }

            nomor++;
        }
    }

    public void tambahPelapor(Pelapor pelapor) {
        try {
            if (pelaporDAO.tambah(pelapor)) {
                System.out.println("Pelapor berhasil disimpan ke database.");
            }
        } catch (SQLException e) {
            tampilkanError(e);
        }
    }

    public ArrayList<Pelapor> getDaftarPelapor() {
        try {
            return new ArrayList<>(pelaporDAO.semua());
        } catch (SQLException e) {
            tampilkanError(e);
            return new ArrayList<>();
        }
    }

    public Pelapor cariPelapor(String id) {
        try {
            return pelaporDAO.cari(id);
        } catch (SQLException e) {
            tampilkanError(e);
            return null;
        }
    }

    public boolean hapusPelapor(String id) {
        try {
            return pelaporDAO.hapus(id);
        } catch (SQLException e) {
            tampilkanError(e);
            return false;
        }
    }

    public String generateIdLaporan() {
        int nomor = 1;

        while (true) {
            String id = String.format("L%03d", nomor);

            if (cariLaporan(id) == null) {
                return id;
            }

            nomor++;
        }
    }

    public void tambahLaporan(Laporan laporan) {
        if (!(laporan.getPelapor() instanceof PelaporKehilangan)) {
            System.out.println(
                "Laporan hanya dapat dibuat oleh pelapor kehilangan."
            );
            return;
        }

        try {
            if (laporanDAO.tambah(laporan)) {
                System.out.println("Laporan berhasil disimpan ke database.");
            }
        } catch (SQLException e) {
            tampilkanError(e);
        }
    }

    public ArrayList<Laporan> getDaftarLaporan() {
        try {
            return new ArrayList<>(laporanDAO.semua());
        } catch (SQLException e) {
            tampilkanError(e);
            return new ArrayList<>();
        }
    }

    public Laporan cariLaporan(String id) {
        try {
            for (Laporan laporan : laporanDAO.semua()) {
                if (laporan.getIdLaporan().equalsIgnoreCase(id)) {
                    return laporan;
                }
            }
        } catch (SQLException e) {
            tampilkanError(e);
        }

        return null;
    }

    public boolean hapusLaporan(String id) {
        try {
            return laporanDAO.hapus(id);
        } catch (SQLException e) {
            tampilkanError(e);
            return false;
        }
    }

    public void ubahStatusLaporan(Laporan laporan, String status) {
        laporan.setStatus(status);

        try {
            if (laporanDAO.ubah(laporan)) {
                System.out.println("Status laporan tersimpan di database.");
            } else {
                System.out.println("Data laporan tidak ditemukan.");
            }
        } catch (SQLException e) {
            tampilkanError(e);
        }
    }

    public String generateIdTemuan() {
        int nomor = 1;

        while (true) {
            String id = String.format("T%03d", nomor);

            if (cariTemuan(id) == null) {
                return id;
            }

            nomor++;
        }
    }

    public void tambahTemuan(Temuan temuan) {
        try {
            if (temuanDAO.tambah(temuan)) {
                System.out.println("Temuan berhasil disimpan ke database.");
            }
        } catch (SQLException e) {
            tampilkanError(e);
        }
    }

    public ArrayList<Temuan> getDaftarTemuan() {
        try {
            return new ArrayList<>(temuanDAO.semua());
        } catch (SQLException e) {
            tampilkanError(e);
            return new ArrayList<>();
        }
    }

    public Temuan cariTemuan(String id) {
        try {
            for (Temuan temuan : temuanDAO.semua()) {
                if (temuan.getIdTemuan().equalsIgnoreCase(id)) {
                    return temuan;
                }
            }
        } catch (SQLException e) {
            tampilkanError(e);
        }

        return null;
    }

    public boolean hapusTemuan(String id) {
        try {
            return temuanDAO.hapus(id);
        } catch (SQLException e) {
            tampilkanError(e);
            return false;
        }
    }

    public void ubahStatusTemuan(Temuan temuan, String status) {
        temuan.setStatus(status);

        try {
            if (temuanDAO.ubah(temuan)) {
                System.out.println("Status temuan tersimpan di database.");
            } else {
                System.out.println("Data temuan tidak ditemukan.");
            }
        } catch (SQLException e) {
            tampilkanError(e);
        }
    }
}