import com.appdecay.kotlin.libenigma.ENIGMA_FAMILY
import com.appdecay.kotlin.libenigma.utils.backward
import com.appdecay.kotlin.libenigma.utils.forward
import com.appdecay.kotlin.libenigma.utils.genString
import org.junit.Test
import kotlin.test.assertEquals

class EtwTest {

    @Test
    fun etwEncode() = assertEquals("JWULC",
            genString { "ABCDE".forEach { append(ENIGMA_FAMILY.RAIL.inputOrder()?.backward(it) ?: it) } })

    @Test
    fun etwDecode() = assertEquals("FQJEZ",
            genString { "MAPCF".forEach { append(ENIGMA_FAMILY.RAIL.inputOrder()?.forward(it)) ?: it } })
}