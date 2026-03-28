package interfaces;

import java.util.List;

import modelo.DatosSimulation;
import modelo.DatosSolicitud;
import modelo.Entidad;

/**
 * Gestor de las comunicaciones con el servidor de simulaciones.
 */
public interface InterfazContactoSim
{
    /**
     * Dados los datos de una solicitud a una simulación, realiza la solicitud al servidor de simulaciones y devuelve
     * el token recibido del servidor.
     *
     * @param sol datos de la solicitud a realizar.
     * @return el número del token asociado a la solicitud.
     */
    public int solicitarSimulation(DatosSolicitud sol);

    /**
     * Dado el token asociado a una solicitud de una simulación, devuelve los datos del resultado de dicha simulación.
     *
     * @param token el token del asociado a la simulación de la cual se quieren obtener los datos.
     * @return los datos del resultado de la simulación asociada al token.
     */
    public DatosSimulation descargarDatos(int token);

    /**
     * Devuelve la lista de entidades que se pueden introducir en la simulación.
     *
     * @return la lista de entidades que se pueden introducir en la simulación.
     */
    public List<Entidad> getEntities();

    /**
     * Dado un id devuelve cierto si existe una entidad que se pueda simular con ese id. En caso contrario devuelve
     * falso.
     *
     * @param id el id del que se quiere saber si existe una entidad con dicho id.
     * @return cierto si existe una entidad con el id pasado, falso en caso contrario.
     */
    public boolean isValidEntityId(int id);
}
