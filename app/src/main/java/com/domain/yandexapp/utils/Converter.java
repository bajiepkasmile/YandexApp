package com.domain.yandexapp.utils;

public class Converter {

    public static String genresToString(String[] genres) {
        StringBuilder genresString = new StringBuilder();
        for (int i = 0; i < genres.length-1; i++) {
            genresString.append(genres[i]);
            genresString.append(", ");
        }
        genresString.append(genres[genres.length - 1]);

        return genresString.toString();
    }

    public static String albumsToString(int albumsCount) {
        if (albumsCount >= 11 && albumsCount <= 19) {
            return albumsCount + " альбомов";
        } else if (albumsCount % 10 == 1) {
            return albumsCount + " альбом";
        } else if (albumsCount % 10 >= 2 && albumsCount % 10 <= 4) {
            return albumsCount + " альбома";
        } else {
            return albumsCount + " альбомов";
        }
    }

    public static String tracksToString(int tracksCount) {
        if (tracksCount >= 11 && tracksCount <= 19) {
            return tracksCount + " песен";
        } else if (tracksCount % 10 == 1) {
            return tracksCount + " песня";
        } else if (tracksCount % 10 >= 2 && tracksCount % 10 <= 4) {
            return tracksCount + " песни";
        } else {
            return tracksCount + " песен";
        }
    }

}
