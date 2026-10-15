/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

/**
 *
 * @author Harits
 */
public class PelaporPenemu extends Pelapor {

    private String lokasiTemuan;

    public PelaporPenemu(String idPelapor, String nama,
                         String noHp, String lokasiTemuan) {

        super(idPelapor, nama, noHp);

        this.lokasiTemuan = lokasiTemuan;
    }

    // OVERLOADING CONSTRUCTOR
    public PelaporPenemu(String idPelapor, String nama,
                         String noHp) {

        this(idPelapor, nama, noHp, "-");
    }

    public String getLokasiTemuan() {

        return lokasiTemuan;
    }

    public void setLokasiTemuan(String lokasiTemuan) {

        this.lokasiTemuan = lokasiTemuan;
    }

    // POLYMORPHISM - OVERRIDING
    @Override
    public String getJenisPelapor() {

        return "Pelapor Penemu";
    }
}