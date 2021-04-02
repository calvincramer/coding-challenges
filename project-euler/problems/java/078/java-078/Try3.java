/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problem078;

import mathtools.MF;

/**
 *
 * @author Calvin
 */
public class Try3 {
    
    public static void main(String[] args) {
        for (int n = 1; n < 1000; n++) {
            System.out.println(MF.partition(1, n));
        }
    }
}
