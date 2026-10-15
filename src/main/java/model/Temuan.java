/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import interfaces.Identifiable;

/**
 *
 * @author Harits
 */
public class Temuan implements Identifiable {

    private String idTemuan;
    private Barang barang;
    private PelaporPenemu pelapor;
    private String lokasi;
    private String tanggal;
    private String status;

    public Temuan(String idTemuan, Barang barang,
                  PelaporPenemu pelapor, String lokasi,
                  String tanggal, String status) {

        this.idTemuan = idTemuan;
        this.barang = barang;
        this.pelapor = pelapor;
        this.lokasi = lokasi;
        this.tanggal = tanggal;
        this.status = status;
    }

    // OVERLOADING CONSTRUCTOR
    public Temuan(String idTemuan, Barang barang,
                  PelaporPenemu pelapor, String lokasi,
                  String tanggal) {

        this(idTemuan, barang, pelapor, lokasi, tanggal,
                "Belum diklaim");
    }

    @Override
    public String getId() {

        return idTemuan;
    }

    public String getIdTemuan() {

        return idTemuan;
    }

    public void setIdTemuan(String idTemuan) {

        this.idTemuan = idTemuan;
    }

    public Barang getBarang() {

        return barang;
    }

    public void setBarang(Barang barang) {

        this.barang = barang;
    }

    public PelaporPenemu getPelapor() {

        return pelapor;
    }

    public void setPelapor(PelaporPenemu pelapor) {

        this.pelapor = pelapor;
    }

    public String getLokasi() {

        return lokasi;
    }

    public void setLokasi(String lokasi) {

        this.lokasi = lokasi;
    }

    public String getTanggal() {

        return tanggal;
    }

    public void setTanggal(String tanggal) {

        this.tanggal = tanggal;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }
}