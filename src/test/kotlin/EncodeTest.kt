import com.appdecay.kotlin.libenigma.Enigma
import org.junit.Test
import kotlin.test.assertEquals


class EncodeTest {
    @Test
    fun testProccess() {

        assertEquals(Enigma.fromString(familyId = 'M').encodeMachine('A'),
                Enigma.fromString(familyId = 'M').processString("A").first())

        assertEquals(Enigma.fromString(familyId = 'R').encodeMachine('A'),
                Enigma.fromString(familyId = 'R').processString("A").first())

        assertEquals(Enigma.fromString(familyId = 'D').encodeMachine('A'),
                Enigma.fromString(familyId = 'D').processString("A").first())

        assertEquals(Enigma.fromString(familyId = 'S').encodeMachine('A')
                , Enigma.fromString(familyId = 'S').processString("A").first())

    }
}
