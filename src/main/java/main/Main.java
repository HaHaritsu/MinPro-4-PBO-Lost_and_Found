/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import controller.LnfController;
import view.LostFoundView;

/**
 *
 * @author Harits
 */
public class Main {

    public static void main(String[] args) {
        try {
            LnfController controller = new LnfController();
            LostFoundView view = new LostFoundView(controller);
            view.mulaiProgram();
        } catch (RuntimeException e) {
            System.out.println("Program gagal dijalankan: " + e.getMessage());
        }
    }
}