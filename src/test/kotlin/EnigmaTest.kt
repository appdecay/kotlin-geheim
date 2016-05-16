import com.appdecay.kotlin.libenigma.Enigma
import org.junit.Test
import kotlin.test.assertEquals


class EnigmaTest {

    @Test
    fun simpleTest() = assertEquals("BJELR",
            Enigma().processString("ABCDE"))

    @Test
    fun ringstellungTest() = assertEquals("GTBHU",
            Enigma.fromString(rotorsId = "123").apply { ringstellung("ABC") }.processString("ABCDE"))

    @Test
    fun steckerTest() = assertEquals("BCJAR",
            Enigma().apply { stecker("ABCD") }.processString("ABCDE"))

}