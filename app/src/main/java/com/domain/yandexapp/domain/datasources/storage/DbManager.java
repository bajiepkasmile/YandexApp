package com.domain.yandexapp.domain.datasources.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.domain.yandexapp.model.Artist;

import java.util.ArrayList;

public class DbManager {

    private Context context;
    private DbHelper dbHelper;

    public DbManager(Context context, DbHelper dbHelper) {
        this.context = context;
        this.dbHelper = dbHelper;
    }

    public void saveArtists(ArrayList<Artist> artists) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        database.delete(DbConstants.TABLE_ARTISTS, null, null);
        database.delete(DbConstants.TABLE_GENRES, null, null);

        ContentValues cvArtist = new ContentValues();
        ContentValues cvGenre = new ContentValues();

        for (Artist artist: artists) {
            cvArtist.put(DbConstants.COLUMN_IDS, artist.getId());
            cvArtist.put(DbConstants.COLUMN_NAMES, artist.getName());
            cvArtist.put(DbConstants.COLUMN_TRACKS, artist.getTracks());
            cvArtist.put(DbConstants.COLUMN_ALBUMS, artist.getAlbum());
            cvArtist.put(DbConstants.COLUMN_LINKS, artist.getLink());
            cvArtist.put(DbConstants.COLUMN_DESCRIPTIONS, artist.getDescription());
            cvArtist.put(DbConstants.COLUMN_SMALL_COVERS_URLS, artist.getSmallCoverUrl());
            cvArtist.put(DbConstants.COLUMN_BIG_COVERS_URLS, artist.getBigCoverUrl());
            database.insert(DbConstants.TABLE_ARTISTS, null, cvArtist);

            for (String genre: artist.getGenres()) {
                cvGenre.put(DbConstants.COLUMN_IDS, artist.getId());
                cvGenre.put(DbConstants.COLUMN_GENRES, genre);
                database.insert(DbConstants.TABLE_GENRES, null, cvGenre);
            }
        }

        dbHelper.close();
    }

    public ArrayList<Artist> loadArtists() {
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        ArrayList<Artist> artists = new ArrayList<>();

        Cursor artistsCursor = database.query(DbConstants.TABLE_ARTISTS, null, null, null, null, null, null);
        Cursor genresCursor = null;

        int columnIds = artistsCursor.getColumnIndex(DbConstants.COLUMN_IDS);
        int columnNames = artistsCursor.getColumnIndex(DbConstants.COLUMN_NAMES);
        int columnTracks = artistsCursor.getColumnIndex(DbConstants.COLUMN_TRACKS);
        int columnAlbums = artistsCursor.getColumnIndex(DbConstants.COLUMN_ALBUMS);
        int columnLinks = artistsCursor.getColumnIndex(DbConstants.COLUMN_LINKS);
        int columnDescriptions = artistsCursor.getColumnIndex(DbConstants.COLUMN_DESCRIPTIONS);
        int columnSmallCovers = artistsCursor.getColumnIndex(DbConstants.COLUMN_SMALL_COVERS_URLS);
        int columnBigCovers = artistsCursor.getColumnIndex(DbConstants.COLUMN_BIG_COVERS_URLS);

        artistsCursor.moveToFirst();
        while (!artistsCursor.isAfterLast()) {
            Artist artist = new Artist();

            artist.setId(artistsCursor.getLong(columnIds));
            artist.setName(artistsCursor.getString(columnNames));
            artist.setTracks(artistsCursor.getInt(columnTracks));
            artist.setAlbum(artistsCursor.getInt(columnAlbums));
            artist.setLink(artistsCursor.getString(columnLinks));
            artist.setDescription(artistsCursor.getString(columnDescriptions));
            artist.setSmallCoverUrl(artistsCursor.getString(columnSmallCovers));
            artist.setBigCoverUrl(artistsCursor.getString(columnBigCovers));

            genresCursor = database.query(DbConstants.TABLE_GENRES,
                    new String[]{DbConstants.COLUMN_GENRES},
                    DbConstants.COLUMN_IDS + " = " + artist.getId(),
                    null,
                    null,
                    null,
                    null);
            String[] genres = new String[genresCursor.getCount()];
            genresCursor.moveToFirst();
            int i = 0;
            while (!genresCursor.isAfterLast()) {
                genres[i] = genresCursor.getString(0);
                genresCursor.moveToNext();
                i++;
            }
            artist.setGenres(genres);

            artists.add(artist);
            artistsCursor.moveToNext();
        }

        if (genresCursor != null) genresCursor.close();
        artistsCursor.close();

        dbHelper.close();

        return artists;
    }

}
