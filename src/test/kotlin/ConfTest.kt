import com.appdecay.kotlin.libenigma.ENIGMA_FAMILY
import org.junit.Test
import kotlin.test.assertEquals


class ConfTest {
    @Test
    fun fromString() {
        with (ENIGMA_FAMILY.MIL) {
            assertEquals(true, isRotorValid('1'))
            assertEquals(true, isRotorValid('5'))
            assertEquals(false, isRotorValid('a'))
        }

        with(ENIGMA_FAMILY.SWISS) {
            assertEquals(true, isRotorValid('1'))
            assertEquals(false, isRotorValid('4'))
            assertEquals(false, isRotorValid('a'))
        }
    }
}