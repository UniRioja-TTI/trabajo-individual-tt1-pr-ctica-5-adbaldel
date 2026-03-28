package servicios;

import modelo.DatosSimulation;
import modelo.DatosSolicitud;
import modelo.Entidad;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ContactoSimTest
{
    private ContactoSim contactoSim;

    // --- Arrange Before/After each test -------------------------------------------------------------------

    @BeforeEach
    void setUp()
    {
        contactoSim = new ContactoSim();
    }

    @AfterEach
    void tearDown()
    {
        contactoSim = null;
    }

    // --- --------------- -------------------------------------------------------------------
    // --- TESTS UNITARIOS -------------------------------------------------------------------
    // --- --------------- -------------------------------------------------------------------

    // --- Test solicitarSimulation -------------------------------------------------------------------

    @Test
    void solicitarSimulation()
    {
        Map<Integer, Integer> nums = new HashMap<>(); // Datos para los que se sabría el resultado
        nums.put(1, 2);
        nums.put(2, 3);
        DatosSolicitud sol = new DatosSolicitud(nums);
        int tok;

        tok = contactoSim.solicitarSimulation(sol);

        assertTrue(tok >= 0);
    }

    // --- Test descargarDatos -------------------------------------------------------------------

    @Test
    void descargarDatos()
    {
        int tok = 1;

        DatosSimulation sim;

        sim = contactoSim.descargarDatos(tok);

        assertNotNull(sim);
    }

    // --- Test getEntities -------------------------------------------------------------------

    @Test
    void getEntities()
    {
        List<Entidad> entities;

        entities = contactoSim.getEntities();

        assertEquals(2, entities.size());
    }

    // --- Test isValidEntityId -------------------------------------------------------------------

    @Test
    void isValidEntityId()
    {
        boolean valid1, valid2, valid0, valid3;

        valid1 = contactoSim.isValidEntityId(1);
        valid2 = contactoSim.isValidEntityId(2);
        valid0 = contactoSim.isValidEntityId(0);
        valid3 = contactoSim.isValidEntityId(3);

        assertTrue(valid1);
        assertTrue(valid2);
        assertFalse(valid0);
        assertFalse(valid3);
    }
}