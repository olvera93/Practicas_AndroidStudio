package org.bedu.roomvehicles.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Vehicle::class), version = 1)
abstract class VehicleDataBase: RoomDatabase() {

    companion object{
        private var dbInstance: VehicleDataBase? = null

        const val DB_NAME = "Vehicles_DB"



        fun getInstance(context: Context): VehicleDataBase?{
            if (dbInstance == null){
                synchronized(VehicleDataBase::class) {
                    dbInstance = Room.databaseBuilder(
                        context.applicationContext,
                        VehicleDataBase::class.java,
                        DB_NAME)
                        .fallbackToDestructiveMigration() // al cambiar de version, destruir info en vez de migrar
                        .build()
                }
            }
            return dbInstance
        }
    }

    abstract fun vehicleDao(): VehicleDao

}