/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.flamingpie.telegram.cinemabot.letterboxd;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Image implements Serializable {

    private List<ImageSize> sizes;

    public List<ImageSize> getSizes() {
        return sizes;
    }

    public void setSizes(List<ImageSize> sizes) {
        this.sizes = sizes;
    }

}
