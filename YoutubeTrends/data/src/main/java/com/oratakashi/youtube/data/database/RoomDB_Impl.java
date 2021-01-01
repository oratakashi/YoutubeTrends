package com.oratakashi.youtube.data.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class RoomDB_Impl extends RoomDB {
  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Items` (`kind` TEXT NOT NULL, `etag` TEXT NOT NULL, `id` TEXT NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Medium` (`id` TEXT NOT NULL, `url` TEXT NOT NULL, `width` INTEGER NOT NULL, `height` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Snippet` (`id` TEXT NOT NULL, `publishedAt` TEXT NOT NULL, `channelId` TEXT NOT NULL, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `channelTitle` TEXT NOT NULL, `categoryId` TEXT NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Standard` (`id` TEXT NOT NULL, `url` TEXT NOT NULL, `width` INTEGER NOT NULL, `height` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Statistics` (`id` TEXT NOT NULL, `viewCount` TEXT NOT NULL, `likeCount` TEXT NOT NULL, `dislikeCount` TEXT NOT NULL, `favoriteCount` TEXT NOT NULL, `commentCount` TEXT NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'b1b05400c5b0cde1be79311e69b73851')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Items`");
        _db.execSQL("DROP TABLE IF EXISTS `Medium`");
        _db.execSQL("DROP TABLE IF EXISTS `Snippet`");
        _db.execSQL("DROP TABLE IF EXISTS `Standard`");
        _db.execSQL("DROP TABLE IF EXISTS `Statistics`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsItems = new HashMap<String, TableInfo.Column>(3);
        _columnsItems.put("kind", new TableInfo.Column("kind", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("etag", new TableInfo.Column("etag", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysItems = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesItems = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoItems = new TableInfo("Items", _columnsItems, _foreignKeysItems, _indicesItems);
        final TableInfo _existingItems = TableInfo.read(_db, "Items");
        if (! _infoItems.equals(_existingItems)) {
          return new RoomOpenHelper.ValidationResult(false, "Items(com.oratakashi.youtube.data.model.fav.Items).\n"
                  + " Expected:\n" + _infoItems + "\n"
                  + " Found:\n" + _existingItems);
        }
        final HashMap<String, TableInfo.Column> _columnsMedium = new HashMap<String, TableInfo.Column>(4);
        _columnsMedium.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedium.put("url", new TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedium.put("width", new TableInfo.Column("width", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMedium.put("height", new TableInfo.Column("height", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMedium = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMedium = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMedium = new TableInfo("Medium", _columnsMedium, _foreignKeysMedium, _indicesMedium);
        final TableInfo _existingMedium = TableInfo.read(_db, "Medium");
        if (! _infoMedium.equals(_existingMedium)) {
          return new RoomOpenHelper.ValidationResult(false, "Medium(com.oratakashi.youtube.data.model.fav.Medium).\n"
                  + " Expected:\n" + _infoMedium + "\n"
                  + " Found:\n" + _existingMedium);
        }
        final HashMap<String, TableInfo.Column> _columnsSnippet = new HashMap<String, TableInfo.Column>(7);
        _columnsSnippet.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSnippet.put("publishedAt", new TableInfo.Column("publishedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSnippet.put("channelId", new TableInfo.Column("channelId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSnippet.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSnippet.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSnippet.put("channelTitle", new TableInfo.Column("channelTitle", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSnippet.put("categoryId", new TableInfo.Column("categoryId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSnippet = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSnippet = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSnippet = new TableInfo("Snippet", _columnsSnippet, _foreignKeysSnippet, _indicesSnippet);
        final TableInfo _existingSnippet = TableInfo.read(_db, "Snippet");
        if (! _infoSnippet.equals(_existingSnippet)) {
          return new RoomOpenHelper.ValidationResult(false, "Snippet(com.oratakashi.youtube.data.model.fav.Snippet).\n"
                  + " Expected:\n" + _infoSnippet + "\n"
                  + " Found:\n" + _existingSnippet);
        }
        final HashMap<String, TableInfo.Column> _columnsStandard = new HashMap<String, TableInfo.Column>(4);
        _columnsStandard.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStandard.put("url", new TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStandard.put("width", new TableInfo.Column("width", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStandard.put("height", new TableInfo.Column("height", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStandard = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStandard = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStandard = new TableInfo("Standard", _columnsStandard, _foreignKeysStandard, _indicesStandard);
        final TableInfo _existingStandard = TableInfo.read(_db, "Standard");
        if (! _infoStandard.equals(_existingStandard)) {
          return new RoomOpenHelper.ValidationResult(false, "Standard(com.oratakashi.youtube.data.model.fav.Standard).\n"
                  + " Expected:\n" + _infoStandard + "\n"
                  + " Found:\n" + _existingStandard);
        }
        final HashMap<String, TableInfo.Column> _columnsStatistics = new HashMap<String, TableInfo.Column>(6);
        _columnsStatistics.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStatistics.put("viewCount", new TableInfo.Column("viewCount", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStatistics.put("likeCount", new TableInfo.Column("likeCount", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStatistics.put("dislikeCount", new TableInfo.Column("dislikeCount", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStatistics.put("favoriteCount", new TableInfo.Column("favoriteCount", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStatistics.put("commentCount", new TableInfo.Column("commentCount", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStatistics = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStatistics = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStatistics = new TableInfo("Statistics", _columnsStatistics, _foreignKeysStatistics, _indicesStatistics);
        final TableInfo _existingStatistics = TableInfo.read(_db, "Statistics");
        if (! _infoStatistics.equals(_existingStatistics)) {
          return new RoomOpenHelper.ValidationResult(false, "Statistics(com.oratakashi.youtube.data.model.fav.Statistics).\n"
                  + " Expected:\n" + _infoStatistics + "\n"
                  + " Found:\n" + _existingStatistics);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "b1b05400c5b0cde1be79311e69b73851", "d9fcf6fdd03fa6ed10ab624c0b6b9ffe");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Items","Medium","Snippet","Standard","Statistics");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Items`");
      _db.execSQL("DELETE FROM `Medium`");
      _db.execSQL("DELETE FROM `Snippet`");
      _db.execSQL("DELETE FROM `Standard`");
      _db.execSQL("DELETE FROM `Statistics`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }
}
