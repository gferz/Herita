package com.example.herita.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user LIMIT 1")
    fun getUser(): Flow<UserEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    /**
     * 🔥 Dipakai saat user mengubah profil (nama / umur)
     * Karena single user, id selalu = 1
     */
    @Query("""
        UPDATE user
        SET name = :name,
            age = :age
        WHERE id = 1
    """)
    suspend fun updateUser(
        name: String,
        age: Int
    )

    @Query("DELETE FROM user")
    suspend fun clearUser()
}
