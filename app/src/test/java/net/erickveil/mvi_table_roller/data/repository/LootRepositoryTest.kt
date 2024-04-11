package net.erickveil.mvi_table_roller.data.repository

import android.content.Context
import android.content.res.AssetManager
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.io.ByteArrayInputStream

@RunWith(MockitoJUnitRunner::class)
class LootRepositoryTest {
    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockAssets: AssetManager

    private lateinit var repository: LootRepository

    @Before
    fun setUp() {

        // Set up to use our mockAssets to load files from instead of actual files.
        `when`(mockContext.assets).thenReturn(mockAssets)

        // We set up "assets.open(fileName)" to return a byte stream based on this test string:
        val testJsonString =  """
            {
                "tableName":"Test Table",
                "description":"A test description",
                "results":["Gold","Magic Sword"]
            }""".trimIndent()

        `when`(mockAssets.open("lootTable.json")).
            thenReturn(ByteArrayInputStream(testJsonString.toByteArray()))

        repository = LootRepository(mockContext)
    }

    @Test
    fun getLootTable_returnsExpectedData() {
        val result = repository.getLootTable()

        assertNotNull(result)
        assertEquals("Test Table", result?.tableName)
        assertEquals("A test description", result?.description)
        assertTrue(result?.results?.contains("Gold") == true)
        assertTrue(result?.results?.contains("Magic Sword") == true)


    }
}