@Dao
interface ColorDao {
    @Insert
    fun insertColor(color: Color)

    @Query("SELECT * FROM colors")
    fun getAllColors(): List<Color>

    @Query("DELETE FROM colors")
    fun deleteAllColors()
}