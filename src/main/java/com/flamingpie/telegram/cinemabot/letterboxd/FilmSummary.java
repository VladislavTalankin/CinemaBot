/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.flamingpie.telegram.cinemabot.letterboxd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmSummary implements Serializable {

    private String name;
    private BigDecimal rating;
    private Integer releaseYear;
    private Image poster;

    public String getRatingStars() {
        // ✬★☆
        if (rating == null) {
            return "☆☆☆☆☆";
        }
        StringBuilder builder = new StringBuilder();
        double round = (double) Math.round(rating.doubleValue() * 100) / 100;
        double mod = round % 1;
        int val = (int) (round - mod);
        boolean needsHalf = mod >= 0.5d;
        int diff = needsHalf ? 4 - val : 5 - val;
        for (int i = 0; i < val; i++) {
            builder.append("★");
        }
        if (needsHalf) {
            builder.append("✬");
        }
        for (int i = 0; i < diff; i++) {
            builder.append("☆");
        }
        return builder.append("(").append(round).append(")").toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Image getPoster() {
        return poster;
    }

    public void setPoster(Image poster) {
        this.poster = poster;
    }

}
