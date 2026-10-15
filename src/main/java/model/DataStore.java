/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.util.ArrayList;

/**
 *
 * @author Harits
 */
public class DataStore {

    private ArrayList<Barang> daftarBarang;
    private ArrayList<Pelapor> daftarPelapor;
    private ArrayList<Laporan> daftarLaporan;
    private ArrayList<Temuan> daftarTemuan;

    public DataStore() {

        daftarBarang = new ArrayList<>();
        daftarPelapor = new ArrayList<>();
        daftarLaporan = new ArrayList<>();
        daftarTemuan = new ArrayList<>();
    }

    public ArrayList<Barang> getDaftarBarang() {

        return daftarBarang;
    }

    public ArrayList<Pelapor> getDaftarPelapor() {

        return daftarPelapor;
    }

    public ArrayList<Laporan> getDaftarLaporan() {

        return daftarLaporan;
    }

    public ArrayList<Temuan> getDaftarTemuan() {

        return daftarTemuan;
    }
}