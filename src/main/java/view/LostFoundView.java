/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package view;

import controller.LnfController;

import java.util.Scanner;

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
public class LostFoundView {

    private Scanner input;

    private LnfController controller;

    public LostFoundView(LnfController controller) {

        input = new Scanner(System.in);

        this.controller = controller;
    }

    // =====================================================
    // PROGRAM UTAMA
    // =====================================================

    public void mulaiProgram() {

        int pilihan;

        do {

            tampilkanMenuUtama();

            pilihan = inputInt("Pilih menu: ");

            System.out.println();

            switch (pilihan) {

                case 1:
                    menuBarang();
                    break;

                case 2:
                    menuPelapor();
                    break;

                case 3:
                    menuLaporan();
                    break;

                case 4:
                    menuTemuan();
                    break;

                case 0:
                    System.out.println("Program selesai.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }

            System.out.println();

        } while (pilihan != 0);
    }

    // =====================================================
    // MENU UTAMA
    // =====================================================

    private void tampilkanMenuUtama() {

        System.out.println("======================================");
        System.out.println("       SISTEM LOST AND FOUND");
        System.out.println("======================================");
        System.out.println("1. Kelola Barang");
        System.out.println("2. Kelola Pelapor");
        System.out.println("3. Kelola Laporan Kehilangan");
        System.out.println("4. Kelola Temuan");
        System.out.println("0. Keluar");
        System.out.println("======================================");
    }

    // =====================================================
    // MENU BARANG
    // =====================================================

    private void menuBarang() {

        int pilihan;

        do {

            System.out.println();
            System.out.println("======================================");
            System.out.println("          MENU DATA BARANG");
            System.out.println("======================================");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Lihat Barang");
            System.out.println("3. Cari Barang");
            System.out.println("4. Hapus Barang");
            System.out.println("0. Kembali");
            System.out.println("======================================");

            pilihan = inputInt("Pilih menu: ");

            switch (pilihan) {

                case 1:
                    tambahBarang();
                    break;

                case 2:
                    tampilkanBarang();
                    break;

                case 3:
                    cariBarang();
                    break;

                case 4:
                    hapusBarang();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }

        } while (pilihan != 0);
    }

    // =====================================================
    // TAMBAH BARANG
    // =====================================================

    private void tambahBarang() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("           TAMBAH BARANG");
        System.out.println("======================================");

        String id = controller.generateIdBarang();

        System.out.println("ID Barang : " + id);

        String nama = inputWajib("Nama Barang : ");
        String kategori = inputWajib("Kategori    : ");
        String warna = inputWajib("Warna       : ");
        String lokasi = inputWajib("Lokasi      : ");

        Barang barang = new Barang(
                id,
                nama,
                kategori,
                warna,
                lokasi
        );

        controller.tambahBarang(barang);

        System.out.println();
        System.out.println("Barang berhasil ditambahkan.");
    }

    // =====================================================
    // TAMPILKAN BARANG
    // =====================================================

    private void tampilkanBarang() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("             DATA BARANG");
        System.out.println("======================================");

        if (controller.getDaftarBarang().isEmpty()) {

            System.out.println("Belum ada data barang.");
            return;
        }

        for (Barang barang : controller.getDaftarBarang()) {

            System.out.println("--------------------------------------");
            System.out.println("ID       : " + barang.getIdBarang());
            System.out.println("Nama     : " + barang.getNamaBarang());
            System.out.println("Kategori : " + barang.getKategori());
            System.out.println("Warna    : " + barang.getWarna());
            System.out.println("Lokasi   : " + barang.getLokasi());
        }

        System.out.println("--------------------------------------");
    }

    // =====================================================
    // CARI BARANG
    // =====================================================

    private void cariBarang() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("             CARI BARANG");
        System.out.println("======================================");

        String id = inputWajib("Masukkan ID Barang: ");

        Barang barang = controller.cariBarang(id);

        if (barang == null) {

            System.out.println("Barang tidak ditemukan.");
            return;
        }

        System.out.println();
        System.out.println("Data Barang ditemukan:");
        System.out.println("ID       : " + barang.getIdBarang());
        System.out.println("Nama     : " + barang.getNamaBarang());
        System.out.println("Kategori : " + barang.getKategori());
        System.out.println("Warna    : " + barang.getWarna());
        System.out.println("Lokasi   : " + barang.getLokasi());
    }

    // =====================================================
    // HAPUS BARANG
    // =====================================================

    private void hapusBarang() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("            HAPUS BARANG");
        System.out.println("======================================");

        String id = inputWajib("Masukkan ID Barang: ");

        Barang barang = controller.cariBarang(id);

        if (barang == null) {

            System.out.println("Barang tidak ditemukan.");
            return;
        }

        System.out.println(
                "Barang: " + barang.getNamaBarang()
        );

        String konfirmasi =
                input("Yakin ingin menghapus? (Y/N): ");

        if (konfirmasi.equalsIgnoreCase("Y")) {

            if (controller.hapusBarang(id)) {

                System.out.println("Barang berhasil dihapus.");
            }

        } else {

            System.out.println("Penghapusan dibatalkan.");
        }
    }

    // =====================================================
    // MENU PELAPOR
    // =====================================================

    private void menuPelapor() {

        int pilihan;

        do {

            System.out.println();
            System.out.println("======================================");
            System.out.println("          MENU DATA PELAPOR");
            System.out.println("======================================");
            System.out.println("1. Tambah Pelapor");
            System.out.println("2. Lihat Pelapor");
            System.out.println("3. Cari Pelapor");
            System.out.println("4. Hapus Pelapor");
            System.out.println("0. Kembali");
            System.out.println("======================================");

            pilihan = inputInt("Pilih menu: ");

            switch (pilihan) {

                case 1:
                    tambahPelapor();
                    break;

                case 2:
                    tampilkanPelapor();
                    break;

                case 3:
                    cariPelapor();
                    break;

                case 4:
                    hapusPelapor();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }

        } while (pilihan != 0);
    }

    // =====================================================
    // TAMBAH PELAPOR
    // =====================================================

    private void tambahPelapor() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("            TAMBAH PELAPOR");
        System.out.println("======================================");

        String id = controller.generateIdPelapor();

        System.out.println("ID Pelapor : " + id);

        String nama = inputWajib("Nama       : ");
        String noHp = inputWajib("No. HP     : ");

        System.out.println();
        System.out.println("Jenis Pelapor:");
        System.out.println("1. Pelapor Kehilangan");
        System.out.println("2. Pelapor Penemu");

        int jenis = inputInt("Pilih jenis: ");

        if (jenis == 1) {

            String nik = inputWajib("NIK        : ");

            PelaporKehilangan pelapor =
                    new PelaporKehilangan(
                            id,
                            nama,
                            noHp,
                            nik
                    );

            controller.tambahPelapor(pelapor);

            System.out.println();
            System.out.println(
                    "Pelapor kehilangan berhasil ditambahkan."
            );

        } else if (jenis == 2) {

            String lokasiTemuan =
                    inputWajib("Lokasi Temuan : ");

            PelaporPenemu pelapor =
                    new PelaporPenemu(
                            id,
                            nama,
                            noHp,
                            lokasiTemuan
                    );

            controller.tambahPelapor(pelapor);

            System.out.println();
            System.out.println(
                    "Pelapor penemu berhasil ditambahkan."
            );

        } else {

            System.out.println("Jenis pelapor tidak valid.");
        }
    }

    // =====================================================
    // TAMPILKAN PELAPOR
    // =====================================================

    private void tampilkanPelapor() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("             DATA PELAPOR");
        System.out.println("======================================");

        if (controller.getDaftarPelapor().isEmpty()) {

            System.out.println("Belum ada data pelapor.");
            return;
        }

        for (Pelapor pelapor :
                controller.getDaftarPelapor()) {

            System.out.println("--------------------------------------");

            System.out.println(
                    "ID       : " + pelapor.getIdPelapor()
            );

            System.out.println(
                    "Nama     : " + pelapor.getNama()
            );

            System.out.println(
                    "No. HP   : " + pelapor.getNoHp()
            );

            // POLYMORPHISM - OVERRIDING
            System.out.println(
                    "Jenis    : " + pelapor.getJenisPelapor()
            );

            if (pelapor instanceof PelaporKehilangan) {

                PelaporKehilangan p =
                        (PelaporKehilangan) pelapor;

                System.out.println(
                        "NIK      : " + p.getNik()
                );

            } else if (pelapor instanceof PelaporPenemu) {

                PelaporPenemu p =
                        (PelaporPenemu) pelapor;

                System.out.println(
                        "Lokasi Temuan : "
                        + p.getLokasiTemuan()
                );
            }
        }

        System.out.println("--------------------------------------");
    }

    // =====================================================
    // CARI PELAPOR
    // =====================================================

    private void cariPelapor() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("             CARI PELAPOR");
        System.out.println("======================================");

        String id = inputWajib("Masukkan ID Pelapor: ");

        Pelapor pelapor =
                controller.cariPelapor(id);

        if (pelapor == null) {

            System.out.println("Pelapor tidak ditemukan.");
            return;
        }

        System.out.println();
        System.out.println("Data Pelapor ditemukan:");

        System.out.println(
                "ID     : " + pelapor.getIdPelapor()
        );

        System.out.println(
                "Nama   : " + pelapor.getNama()
        );

        System.out.println(
                "No. HP : " + pelapor.getNoHp()
        );

        System.out.println(
                "Jenis  : " + pelapor.getJenisPelapor()
        );
    }

    // =====================================================
    // HAPUS PELAPOR
    // =====================================================

    private void hapusPelapor() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("            HAPUS PELAPOR");
        System.out.println("======================================");

        String id =
                inputWajib("Masukkan ID Pelapor: ");

        Pelapor pelapor =
                controller.cariPelapor(id);

        if (pelapor == null) {

            System.out.println(
                    "Pelapor tidak ditemukan."
            );

            return;
        }

        System.out.println(
                "Nama: " + pelapor.getNama()
        );

        String konfirmasi =
                input("Yakin ingin menghapus? (Y/N): ");

        if (konfirmasi.equalsIgnoreCase("Y")) {

            if (controller.hapusPelapor(id)) {

                System.out.println(
                        "Pelapor berhasil dihapus."
                );
            }

        } else {

            System.out.println(
                    "Penghapusan dibatalkan."
            );
        }
    }

    // =====================================================
    // MENU LAPORAN
    // =====================================================

    private void menuLaporan() {

        int pilihan;

        do {

            System.out.println();
            System.out.println("======================================");
            System.out.println("       MENU LAPORAN KEHILANGAN");
            System.out.println("======================================");
            System.out.println("1. Tambah Laporan");
            System.out.println("2. Lihat Laporan");
            System.out.println("3. Cari Laporan");
            System.out.println("4. Ubah Status Laporan");
            System.out.println("5. Hapus Laporan");
            System.out.println("0. Kembali");
            System.out.println("======================================");

            pilihan = inputInt("Pilih menu: ");

            switch (pilihan) {

                case 1:
                    tambahLaporan();
                    break;

                case 2:
                    tampilkanLaporan();
                    break;

                case 3:
                    cariLaporan();
                    break;

                case 4:
                    ubahStatusLaporan();
                    break;

                case 5:
                    hapusLaporan();
                    break;

                case 0:
                    break;

                default:
                    System.out.println(
                            "Pilihan tidak valid."
                    );
            }

        } while (pilihan != 0);
    }

    // =====================================================
    // TAMBAH LAPORAN
    // =====================================================

    private void tambahLaporan() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("           TAMBAH LAPORAN");
        System.out.println("======================================");

        String id =
                controller.generateIdLaporan();

        System.out.println(
                "ID Laporan : " + id
        );

        if (controller.getDaftarPelapor().isEmpty()) {

            System.out.println(
                    "Belum ada data pelapor."
            );

            return;
        }

        tampilkanPelapor();

        String idPelapor =
                inputWajib("ID Pelapor: ");

        Pelapor pelapor =
                controller.cariPelapor(idPelapor);

        if (pelapor == null) {

            System.out.println(
                    "Pelapor tidak ditemukan."
            );

            return;
        }

        if (controller.getDaftarBarang().isEmpty()) {

            System.out.println(
                    "Belum ada data barang."
            );

            return;
        }

        tampilkanBarang();

        String idBarang =
                inputWajib("ID Barang: ");

        Barang barang =
                controller.cariBarang(idBarang);

        if (barang == null) {

            System.out.println(
                    "Barang tidak ditemukan."
            );

            return;
        }

        String tanggal =
                inputWajib("Tanggal Kehilangan: ");

        String lokasi =
                inputWajib("Lokasi Kehilangan: ");

        String keterangan =
                inputWajib("Keterangan: ");

        Laporan laporan =
                new Laporan(
                        id,
                        pelapor,
                        barang,
                        tanggal,
                        lokasi,
                        keterangan,
                        "Belum ditemukan"
                );

        controller.tambahLaporan(laporan);

        System.out.println();
        System.out.println(
                "Laporan berhasil ditambahkan."
        );
    }

    // =====================================================
    // TAMPILKAN LAPORAN
    // =====================================================

    private void tampilkanLaporan() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("          DATA LAPORAN");
        System.out.println("======================================");

        if (controller.getDaftarLaporan().isEmpty()) {

            System.out.println(
                    "Belum ada data laporan."
            );

            return;
        }

        for (Laporan laporan :
                controller.getDaftarLaporan()) {

            System.out.println("--------------------------------------");

            System.out.println(
                    "ID Laporan : "
                    + laporan.getIdLaporan()
            );

            System.out.println(
                    "Pelapor    : "
                    + laporan.getPelapor().getNama()
            );

            System.out.println(
                    "Barang     : "
                    + laporan.getBarang().getNamaBarang()
            );

            System.out.println(
                    "Tanggal    : "
                    + laporan.getTanggal()
            );

            System.out.println(
                    "Lokasi     : "
                    + laporan.getLokasi()
            );

            System.out.println(
                    "Keterangan : "
                    + laporan.getKeterangan()
            );

            System.out.println(
                    "Status     : "
                    + laporan.getStatus()
            );
        }

        System.out.println("--------------------------------------");
    }

    // =====================================================
    // CARI LAPORAN
    // =====================================================

    private void cariLaporan() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("            CARI LAPORAN");
        System.out.println("======================================");

        String id =
                inputWajib("Masukkan ID Laporan: ");

        Laporan laporan =
                controller.cariLaporan(id);

        if (laporan == null) {

            System.out.println(
                    "Laporan tidak ditemukan."
            );

            return;
        }

        System.out.println();
        System.out.println("Data Laporan ditemukan:");

        System.out.println(
                "ID Laporan : "
                + laporan.getIdLaporan()
        );

        System.out.println(
                "Pelapor    : "
                + laporan.getPelapor().getNama()
        );

        System.out.println(
                "Barang     : "
                + laporan.getBarang().getNamaBarang()
        );

        System.out.println(
                "Tanggal    : "
                + laporan.getTanggal()
        );

        System.out.println(
                "Lokasi     : "
                + laporan.getLokasi()
        );

        System.out.println(
                "Keterangan : "
                + laporan.getKeterangan()
        );

        System.out.println(
                "Status     : "
                + laporan.getStatus()
        );
    }

    // =====================================================
    // UBAH STATUS LAPORAN
    // =====================================================

    private void ubahStatusLaporan() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("        UBAH STATUS LAPORAN");
        System.out.println("======================================");

        String id =
                inputWajib("Masukkan ID Laporan: ");

        Laporan laporan =
                controller.cariLaporan(id);

        if (laporan == null) {

            System.out.println(
                    "Laporan tidak ditemukan."
            );

            return;
        }

        System.out.println();
        System.out.println("Status:");
        System.out.println("1. Belum ditemukan");
        System.out.println("2. Ditemukan");
        System.out.println("3. Sudah dikembalikan");

        int pilihan =
                inputInt("Pilih status: ");

        String status;

        switch (pilihan) {

            case 1:
                status = "Belum ditemukan";
                break;

            case 2:
                status = "Ditemukan";
                break;

            case 3:
                status = "Sudah dikembalikan";
                break;

            default:
                System.out.println(
                        "Pilihan status tidak valid."
                );

                return;
        }

        controller.ubahStatusLaporan(
                laporan,
                status
        );

        System.out.println(
                "Status laporan berhasil diubah."
        );
    }

    // =====================================================
    // HAPUS LAPORAN
    // =====================================================

    private void hapusLaporan() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("           HAPUS LAPORAN");
        System.out.println("======================================");

        String id =
                inputWajib("Masukkan ID Laporan: ");

        Laporan laporan =
                controller.cariLaporan(id);

        if (laporan == null) {

            System.out.println(
                    "Laporan tidak ditemukan."
            );

            return;
        }

        String konfirmasi =
                input("Yakin ingin menghapus? (Y/N): ");

        if (konfirmasi.equalsIgnoreCase("Y")) {

            if (controller.hapusLaporan(id)) {

                System.out.println(
                        "Laporan berhasil dihapus."
                );
            }

        } else {

            System.out.println(
                    "Penghapusan dibatalkan."
            );
        }
    }

    // =====================================================
    // MENU TEMUAN
    // =====================================================

    private void menuTemuan() {

        int pilihan;

        do {

            System.out.println();
            System.out.println("======================================");
            System.out.println("             MENU TEMUAN");
            System.out.println("======================================");
            System.out.println("1. Tambah Temuan");
            System.out.println("2. Lihat Temuan");
            System.out.println("3. Cari Temuan");
            System.out.println("4. Ubah Status Temuan");
            System.out.println("5. Hapus Temuan");
            System.out.println("0. Kembali");
            System.out.println("======================================");

            pilihan = inputInt("Pilih menu: ");

            switch (pilihan) {

                case 1:
                    tambahTemuan();
                    break;

                case 2:
                    tampilkanTemuan();
                    break;

                case 3:
                    cariTemuan();
                    break;

                case 4:
                    ubahStatusTemuan();
                    break;

                case 5:
                    hapusTemuan();
                    break;

                case 0:
                    break;

                default:
                    System.out.println(
                            "Pilihan tidak valid."
                    );
            }

        } while (pilihan != 0);
    }

    // =====================================================
    // TAMBAH TEMUAN
    // =====================================================

private void tambahTemuan() {

    System.out.println();
    System.out.println("======================================");
    System.out.println("            TAMBAH TEMUAN");
    System.out.println("======================================");

    String id = controller.generateIdTemuan();

    System.out.println("ID Temuan : " + id);

    if (controller.getDaftarBarang().isEmpty()) {
        System.out.println("Belum ada data barang.");
        return;
    }

    tampilkanBarang();

    String idBarang = inputWajib("ID Barang: ");
    Barang barang = controller.cariBarang(idBarang);

    if (barang == null) {
        System.out.println("Barang tidak ditemukan.");
        return;
    }

    java.util.ArrayList<PelaporPenemu> daftarPenemu =
            new java.util.ArrayList<>();

    for (Pelapor pelapor : controller.getDaftarPelapor()) {
        if (pelapor instanceof PelaporPenemu) {
            daftarPenemu.add((PelaporPenemu) pelapor);
        }
    }

    if (daftarPenemu.isEmpty()) {
        System.out.println("Belum ada data pelapor penemu.");
        return;
    }

    System.out.println();
    System.out.println("======================================");
    System.out.println("         DAFTAR PELAPOR PENEMU");
    System.out.println("======================================");

    for (PelaporPenemu penemu : daftarPenemu) {

        System.out.println("--------------------------------------");

        System.out.println(
                "ID       : " + penemu.getIdPelapor()
        );

        System.out.println(
                "Nama     : " + penemu.getNama()
        );

        System.out.println(
                "No. HP   : " + penemu.getNoHp()
        );

        System.out.println(
                "Lokasi   : " + penemu.getLokasiTemuan()
        );
    }

    System.out.println("--------------------------------------");

    PelaporPenemu pelaporPenemu = null;

    while (pelaporPenemu == null) {

        String idPelapor =
                inputWajib("Pilih ID Pelapor Penemu: ");

        for (PelaporPenemu penemu : daftarPenemu) {

            if (penemu.getIdPelapor().equalsIgnoreCase(idPelapor)) {
                pelaporPenemu = penemu;
                break;
            }
        }

        if (pelaporPenemu == null) {
            System.out.println(
                    "ID tidak ditemukan. Pilih ID dari daftar penemu."
            );
        }
    }

    String lokasi = pelaporPenemu.getLokasiTemuan();

    if (lokasi == null || lokasi.trim().isEmpty()
            || lokasi.equals("-")) {
        System.out.println(
                "Lokasi penemu belum tersedia. "
                + "Periksa data pelapor penemu terlebih dahulu."
        );
        return;
    }

    System.out.println();
    System.out.println("Penemu : " + pelaporPenemu.getNama());
    System.out.println("Lokasi Temuan Otomatis : " + lokasi);

    String tanggal;

    while (true) {

        tanggal = inputWajib("Tanggal Temuan (yyyy-MM-dd): ");

        try {
            java.time.LocalDate.parse(tanggal);
            break;
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println(
                    "Format tanggal tidak valid. Contoh: 2026-10-15."
            );
        }
    }

    Temuan temuan = new Temuan(
            id,
            barang,
            pelaporPenemu,
            lokasi,
            tanggal,
            "Belum diklaim"
    );

    controller.tambahTemuan(temuan);
}

    // =====================================================
    // TAMPILKAN TEMUAN
    // =====================================================

    private void tampilkanTemuan() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("             DATA TEMUAN");
        System.out.println("======================================");

        if (controller.getDaftarTemuan().isEmpty()) {

            System.out.println(
                    "Belum ada data temuan."
            );

            return;
        }

        for (Temuan temuan :
                controller.getDaftarTemuan()) {

            System.out.println("--------------------------------------");

            System.out.println(
                    "ID Temuan : "
                    + temuan.getIdTemuan()
            );

            System.out.println(
                    "Barang    : "
                    + temuan.getBarang().getNamaBarang()
            );

            System.out.println(
                    "Penemu    : "
                    + temuan.getPelapor().getNama()
            );

            System.out.println(
                    "Lokasi    : "
                    + temuan.getLokasi()
            );

            System.out.println(
                    "Tanggal   : "
                    + temuan.getTanggal()
            );

            System.out.println(
                    "Status    : "
                    + temuan.getStatus()
            );
        }

        System.out.println("--------------------------------------");
    }

    // =====================================================
    // CARI TEMUAN
    // =====================================================

    private void cariTemuan() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("             CARI TEMUAN");
        System.out.println("======================================");

        String id =
                inputWajib("Masukkan ID Temuan: ");

        Temuan temuan =
                controller.cariTemuan(id);

        if (temuan == null) {

            System.out.println(
                    "Temuan tidak ditemukan."
            );

            return;
        }

        System.out.println();
        System.out.println("Data Temuan ditemukan:");

        System.out.println(
                "ID Temuan : "
                + temuan.getIdTemuan()
        );

        System.out.println(
                "Barang    : "
                + temuan.getBarang().getNamaBarang()
        );

        System.out.println(
                "Penemu    : "
                + temuan.getPelapor().getNama()
        );

        System.out.println(
                "Lokasi    : "
                + temuan.getLokasi()
        );

        System.out.println(
                "Tanggal   : "
                + temuan.getTanggal()
        );

        System.out.println(
                "Status    : "
                + temuan.getStatus()
        );
    }

    // =====================================================
    // UBAH STATUS TEMUAN
    // =====================================================

    private void ubahStatusTemuan() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("          UBAH STATUS TEMUAN");
        System.out.println("======================================");

        String id =
                inputWajib("Masukkan ID Temuan: ");

        Temuan temuan =
                controller.cariTemuan(id);

        if (temuan == null) {

            System.out.println(
                    "Temuan tidak ditemukan."
            );

            return;
        }

        System.out.println();
        System.out.println("Status:");
        System.out.println("1. Belum diklaim");
        System.out.println("2. Sudah diklaim");
        System.out.println("3. Diserahkan");

        int pilihan =
                inputInt("Pilih status: ");

        String status;

        switch (pilihan) {

            case 1:
                status = "Belum diklaim";
                break;

            case 2:
                status = "Sudah diklaim";
                break;

            case 3:
                status = "Diserahkan";
                break;

            default:
                System.out.println(
                        "Pilihan status tidak valid."
                );

                return;
        }

        controller.ubahStatusTemuan(
                temuan,
                status
        );

        System.out.println(
                "Status temuan berhasil diubah."
        );
    }

    // =====================================================
    // HAPUS TEMUAN
    // =====================================================

    private void hapusTemuan() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("             HAPUS TEMUAN");
        System.out.println("======================================");

        String id =
                inputWajib("Masukkan ID Temuan: ");

        Temuan temuan =
                controller.cariTemuan(id);

        if (temuan == null) {

            System.out.println(
                    "Temuan tidak ditemukan."
            );

            return;
        }

        String konfirmasi =
                input("Yakin ingin menghapus? (Y/N): ");

        if (konfirmasi.equalsIgnoreCase("Y")) {

            if (controller.hapusTemuan(id)) {

                System.out.println(
                        "Temuan berhasil dihapus."
                );
            }

        } else {

            System.out.println(
                    "Penghapusan dibatalkan."
            );
        }
    }

    // =====================================================
    // INPUT STRING
    // =====================================================

    private String input(String pesan) {

        System.out.print(pesan);

        return input.nextLine().trim();
    }

    // =====================================================
    // INPUT WAJIB
    // =====================================================

    private String inputWajib(String pesan) {

        String hasil;

        do {

            hasil = input(pesan);

            if (hasil.isEmpty()) {

                System.out.println(
                        "Input tidak boleh kosong."
                );
            }

        } while (hasil.isEmpty());

        return hasil;
    }

    // =====================================================
    // INPUT INTEGER
    // =====================================================

    private int inputInt(String pesan) {

        while (true) {

            try {

                return Integer.parseInt(
                        input(pesan)
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Input harus berupa angka."
                );
            }
        }
    }
}