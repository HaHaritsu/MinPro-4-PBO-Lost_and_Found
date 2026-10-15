/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

/**
 *
 * @author Harits
 */
public class PelaporKehilangan extends Pelapor {

    private String nik;

    public PelaporKehilangan(String idPelapor, String nama,
                             String noHp, String nik) {

        super(idPelapor, nama, noHp);

        this.nik = nik;
    }

    // OVERLOADING CONSTRUCTOR
    public PelaporKehilangan(String idPelapor, String nama,
                             String noHp) {

        this(idPelapor, nama, noHp, "-");
    }

    public String getNik() {

        return nik;
    }

    public void setNik(String nik) {

        this.nik = nik;
    }

    // POLYMORPHISM - OVERRIDING
    @Override
    public String getJenisPelapor() {

        return "Pelapor Kehilangan";
    }
}