
import com.mangabit.domain.manga.Parser
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.nio.file.Paths

class ParseMangaTest {

    @Test
    fun `should return a manga`() {
        val manga = Parser.parseManga(response_mock)
        assertEquals(13, manga.id)
        assertEquals("One Piece", manga.name)
        assertEquals("Oda, Eiichiro", manga.author)
    }

    companion object {
        val response_mock = Paths.get("src/test/resources/response_mock.json").toFile().readText()
    }
}