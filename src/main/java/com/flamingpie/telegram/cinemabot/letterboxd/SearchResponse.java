/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.flamingpie.telegram.cinemabot.letterboxd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Admin
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResponse implements Serializable {

    private List<? extends AbstractSearchItem> items;

    public List<? extends AbstractSearchItem> getItems() {
        return items;
    }

    public void setItems(List<? extends AbstractSearchItem> items) {
        this.items = items;
    }

}
