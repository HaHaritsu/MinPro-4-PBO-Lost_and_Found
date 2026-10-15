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
public abstract class Pelapor implements Identifiable {

    private String idPelapor;
    private String nama;
    private String noHp;

    public Pelapor(String idPelapor, String nama, String noHp) {

        this.idPelapor = idPelapor;
        this.nama = nama;
        this.noHp = noHp;
    }

    @Override
    public String getId() {

        return idPelapor;
    }

    public String getIdPelapor() {

        return idPelapor;
    }

    public void setIdPelapor(String idPelapor) {

        this.idPelapor = idPelapor;
    }

    public String getNama() {

        return nama;
    }

    public void setNama(String nama) {

        this.nama = nama;
    }

    public String getNoHp() {

        return noHp;
    }

    public void setNoHp(String noHp) {

        this.noHp = noHp;
    }

    // ABSTRACT METHOD
    public abstract String getJenisPelapor();
}