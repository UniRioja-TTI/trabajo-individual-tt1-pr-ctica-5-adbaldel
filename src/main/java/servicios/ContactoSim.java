package servicios;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import interfaces.InterfazContactoSim;
import modelo.DatosSimulation;
import modelo.DatosSolicitud;
import modelo.Entidad;

@Service
public class ContactoSim implements InterfazContactoSim
{
    private List<Entidad> entidades;

    private Map<Integer, DatosSolicitud> solicitudes;
    private Random random;

    public ContactoSim()
    {
        solicitudes = new HashMap<>();
        random = new Random(); // Le insertaria una semilla en el constructor pero IntellIJ se queja de que

        entidades = new ArrayList<>();

        Entidad entidad = new Entidad();
        entidad.setId(1);
        entidad.setName("gatos");
        entidad.setDescripcion("Gatos salvajes");
        entidades.add(entidad);

        entidad = new Entidad();
        entidad.setId(2);
        entidad.setName("perros");
        entidad.setDescripcion("Perros domésticos");
        entidades.add(entidad);

        // Probablemente necesite una conexion a un simulador pasada como parametro pero no existe dicha clase
        // en mi proyecto entonces lo dejo vacio
    }

    @Override
    public int solicitarSimulation(DatosSolicitud sol)
    {
        int token = random.nextInt(10000);

        solicitudes.put(token, sol);

        return token;
    }

    @Override
    public List<Entidad> getEntities()
    {
        return entidades;
    }

    @Override
    public boolean isValidEntityId(int id) {
        boolean valid = false;
        int i = 0;

        while (!valid && i < entidades.size())
        {
            valid = (id == entidades.get(i).getId());
            i++;
        }

        return valid;
    }

    @Override
    public DatosSimulation descargarDatos(int ticket)
    {
        DatosSimulation sim = new DatosSimulation();

        return sim;
    }
}