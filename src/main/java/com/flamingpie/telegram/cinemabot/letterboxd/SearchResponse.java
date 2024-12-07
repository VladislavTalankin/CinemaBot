/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.flamingpie.telegram.cinemabot.letterboxd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 * @param <T>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResponse<T  extends AbstractSearchItem> implements Serializable {

    private List<T> items = new ArrayList();

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

}
