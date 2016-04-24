package com.domain.yandexapp.domain.datasources.storage;

public class DbConstants {
    //Db
    public static final String DB_NAME = "YandexAppDb";
    public static final int DB_VERSION = 1;

    //Tables
    public static final String TABLE_ARTISTS = "table_artists";
    public static final String TABLE_GENRES = "table_genres";

    //Columns
    public static final String COLUMN_IDS = "ids";
    public static final String COLUMN_NAMES = "names";
    public static final String COLUMN_GENRES = "genres";
    public static final String COLUMN_TRACKS = "tracks";
    public static final String COLUMN_ALBUMS = "albums";
    public static final String COLUMN_LINKS = "links";
    public static final String COLUMN_DESCRIPTIONS = "description";
    public static final String COLUMN_SMALL_COVERS_URLS = "small_covers_urls";
    public static final String COLUMN_BIG_COVERS_URLS = "big_covers_urls";

    //Sql commands
    public static final String TYPE_TEXT = " TEXT";
    public static final String TYPE_INT = " INTEGER";
    public static final String COMMA_SEP = ", ";

    public static final String SQL_CREATE_TABLE_ARTISTS = "CREATE TABLE " + TABLE_ARTISTS + "(" +
            DbConstants.COLUMN_IDS + TYPE_INT + COMMA_SEP +
            DbConstants.COLUMN_NAMES + TYPE_TEXT + COMMA_SEP +
            DbConstants.COLUMN_TRACKS + TYPE_INT + COMMA_SEP +
            DbConstants.COLUMN_ALBUMS + TYPE_INT + COMMA_SEP +
            DbConstants.COLUMN_LINKS + TYPE_TEXT + COMMA_SEP +
            DbConstants.COLUMN_DESCRIPTIONS + TYPE_TEXT + COMMA_SEP +
            DbConstants.COLUMN_SMALL_COVERS_URLS + TYPE_TEXT + COMMA_SEP +
            DbConstants.COLUMN_BIG_COVERS_URLS + TYPE_TEXT +
            ");";

    public static final String SQL_CREATE_TABLE_GENRES = "CREATE TABLE " + TABLE_GENRES + "(" +
            DbConstants.COLUMN_IDS + TYPE_INT + COMMA_SEP +
            DbConstants.COLUMN_GENRES + TYPE_TEXT +
            ");";

    public static final String SQL_DROP_TABLE_ARTISTS = "DROP TABLE IF EXISTS " + TABLE_ARTISTS;

    public static final String SQL_DROP_TABLE_GENRES = "DROP TABLE IF EXISTS " + TABLE_GENRES;

}
