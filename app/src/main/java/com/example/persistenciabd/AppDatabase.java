package com.example.persistenciabd;

import android.content.Context;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
@Database(entities = {User.class,  Trophy.class}, version = 16, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase
{
    private static AppDatabase INSTANCE;
    public abstract UserDao userDao();
    public abstract TrophyDao trophyDao();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "userdatabase")
            //Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                     // por simplicidad permitimos queries en el hilo main
                    // esto no se hace en una app en produccion!
                            .allowMainThreadQueries()
                            // regenerar la BD s se requiere
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
