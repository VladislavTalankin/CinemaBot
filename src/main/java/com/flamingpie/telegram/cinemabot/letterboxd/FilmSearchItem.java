/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.flamingpie.telegram.cinemabot.letterboxd;

/**
 *
 * @author Admin
 */
public class FilmSearchItem extends AbstractSearchItem {

    private FilmSummary film;

    public FilmSummary getFilm() {
        return film;
    }

    public void setFilm(FilmSummary film) {
        this.film = film;
    }
}
