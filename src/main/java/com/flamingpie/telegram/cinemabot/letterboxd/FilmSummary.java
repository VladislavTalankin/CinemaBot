/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.flamingpie.telegram.cinemabot.letterboxd;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Admin
 */
public class FilmSummary extends BaseObject {

    private String name;
    private BigDecimal rating;
    private Integer releaseYear;
    private Integer runTime;
    private Image poster;
    private List<Genre> genres;

    public String getGenresString() {
        if (genres == null || genres.isEmpty()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        Iterator<Genre> iterator = genres.iterator();
        while (iterator.hasNext()) {
            builder.append(iterator.next().getName());
            if (iterator.hasNext())
                builder.append(", ");
        }
        return builder.toString();
    }

    public String getRunTimeString() {
        if (runTime == null || runTime == 0) {
            return "--:--";
        }

        int hours = runTime / 60;
        int minutes = runTime - 60 * hours;
        return new StringBuilder().append(hours).append(":").append(minutes).toString();
    }

    public String getRatingStars() {
        // ✬★☆
        if (rating == null) {
            return "☆☆☆☆☆";
        }
        StringBuilder builder = new StringBuilder();
        double round = (double) Math.round(rating.doubleValue() * 100) / 100;
        double mod = round % 1;
        int val = (int) (round - mod);
        for (int i = 0; i < val; i++) {
            builder.append("★");
        }
        if (mod >= 0.5d) {
            builder.append("✬");
        }
        while (builder.length() < 5) {
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

    public Integer getRunTime() {
        return runTime;
    }

    public void setRunTime(Integer runTime) {
        this.runTime = runTime;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

}
