import com.appdecay.kotlin.libenigma.ENIGMA_DEFINITION
import com.appdecay.kotlin.libenigma.Enigma
import org.junit.Test
import kotlin.test.assertEquals


class MachineTest {

    @Test fun default() =
            assertEquals("WIHDDE", ENIGMA_DEFINITION.machine(0).processString("KOTLIN"))

    @Test fun token() =
            assertEquals("WIHDDE", Enigma.fromToken("M:123:B").processString("KOTLIN"))

    @Test fun doenitz() =
            assertEquals("VTECI", ENIGMA_DEFINITION.machine(1).processString("ABCDE"))

    @Test fun barba() =
            assertEquals("VLEBN", ENIGMA_DEFINITION.machine(2).processString("ABCDE"))

    @Test fun u264() =
            assertEquals("EQOUD", ENIGMA_DEFINITION.machine(3).processString("ABCDE"))

    @Test fun schnar() =
            assertEquals("CTZMR", ENIGMA_DEFINITION.machine(4).processString("ABCDE"))

    @Test fun turing() =
            assertEquals("VVKFL", ENIGMA_DEFINITION.machine(5).processString("ABCDE"))

}