
import com.mangabit.domain.manga.Parser
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.nio.file.Paths

class ParseMangaTest {

    @Test
    fun `should return a manga`() {
        val manga = Parser.parseManga(response_mock)
        assertEquals("72231b89-3934-439b-9d83-0f82e45f01d6", manga.id)
        assertEquals("Kanchigai no Atelier Meister", manga.title)
        assertEquals("4deef1dd-1f4d-4064-9879-75de95397964", manga.authorId)
    }

    companion object {
        val response_mock = Paths.get("src/test/resources/response_mock.json").toFile().readText()
    }
}