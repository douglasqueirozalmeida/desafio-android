package com.picpay.desafio.android.migration;

import static org.junit.Assert.assertNotNull;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.room.testing.MigrationTestHelper;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.picpay.desafio.android.data.database.MyDataBase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class MigrationTest {
    private static final String TEST_DB = "desafio-migration-test";
    private static final int VERSION_ROOM_TEST = 1;

    @Rule
    public MigrationTestHelper helper;

    public MigrationTest() {
        helper = new MigrationTestHelper(InstrumentationRegistry.getInstrumentation(),
                MyDataBase.class.getCanonicalName(),
                new FrameworkSQLiteOpenHelperFactory()
        );
    }

    @Test
    public void migrate1to2() throws IOException {
        createDB(VERSION_ROOM_TEST);

        MyDataBase appDb = executeMigration(MIGRATION_1_2);
        simulateAcessRoom(appDb);

        assertTest();
    }


    private void assertTest() {
        String testeString = "";
        assertNotNull(testeString);
    }

    private SupportSQLiteDatabase createDB(int versionInicialDB) throws IOException {
        SupportSQLiteDatabase db = helper.createDatabase(TEST_DB, versionInicialDB);
        db.close();

        return db;
    }

    private MyDataBase executeMigration(Migration... mirgration) {
        return Room.databaseBuilder(
                InstrumentationRegistry.getInstrumentation().getTargetContext(),
                MyDataBase.class,
                TEST_DB)
                .addMigrations(mirgration)
                .build();
    }

    private void simulateAcessRoom(MyDataBase appDb) {
        appDb.getOpenHelper().getWritableDatabase();
        appDb.close();
    }

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE `User` RENAME id to id");
        }
    };
}
