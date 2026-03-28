package com.tt1.trabajo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import interfaces.InterfazContactoSim;
import modelo.DatosSolicitud;

/**
 * Gestiona las solicitudes de simulaciones usando Java Spring. Además, registra las solicitudes realizadas y los
 * errores en un que suceden en un logger.
 */
@Controller
public class SolicitudController
{
    private final InterfazContactoSim ics;
    private final Logger logger;

    /**
     * Crea un gestor de solicitudes con la conexión al simulador y logger pasados cómo parámetros.
     *
     * @param ics gestor de las comunicaciones (conexión) con el simulador.
     * @param logger registrador de las llamadas realizadas y los errores.
     */
    public SolicitudController(InterfazContactoSim ics, Logger logger)
    {
        this.ics = ics;
        this.logger = logger;
    }

    /**
     * Gestiona las solicitudes GET realizadas al servidor Java Spring en la dirección /solicitud. Añade al modelo de
     * Java Spring las entidades dadas por el ics (InterfazContactoSim) y devuelve el nombre de la plantilla html
     * que se pide al usuario las cantidades iniciales de cada entidad en la simulación.
     *
     * @param model el modelo de Java Spring.
     * @return nombre de la plantilla html que se muestra al usuario al realizar la solicitud.
     */
    @GetMapping("/solicitud")
    public String solicitud(Model model)
    {
        model.addAttribute("entities", ics.getEntities());
        return "solicitud";
    }

    /**
     * Gestiona las solicitudes POST realizadas al servidor de Java Spring en la dirección /solicitud. Si los datos
     * recibidos en el POST son correctos, se solicita la simulación al simulador a través del ics (InterfazContactoSim)
     * y se muestra el token recibido al usuario (añadiéndolo al modelo de JavaSpring y devolviendo el nombre de la
     * plantilla html que muestra el token recibido al usuario).
     *
     * @param formData los datos recibidos en el POST solicitado por el html devuelto en el método solicitud.
     * @param model el modelo de Java Spring.
     * @return nombre de la plantilla html que se muestra al usuario tras rellenar la solicitud.
     */
    @PostMapping("/solicitud")
    public String handleSolicitud(@RequestParam Map<String, String> formData, Model model)
    {
        Map<Integer, Integer> validData = new HashMap<>();
        List<String> errors = new ArrayList<>();

        for (Map.Entry<String, String> entry : formData.entrySet())
        {
            String key = entry.getKey();
            String value = entry.getValue();
            try
            {
                int num = Integer.parseInt(value);
                if (num < 0)
                {
                    errors.add(key + " no puede ser negativo");
                }
                int id = Integer.parseInt(key);
                if (ics.isValidEntityId(id))
                {
                    validData.put(id, num);
                }
                else
                {
                    errors.add(key + "no se corresponde con una entidad");
                }
            }
            catch (NumberFormatException e)
            {
                errors.add(key + " debe ser un número entero");
            }
        }
        if (!errors.isEmpty())
        {
            model.addAttribute("errors", errors);
            logger.warn("Atendida petición con errores");
        }
        else
        {
            logger.info("Atendida petición");
            DatosSolicitud ds = new DatosSolicitud(validData);
            int tok = ics.solicitarSimulation(ds);
            if (tok != -1)
            {
                model.addAttribute("token", tok);
            }
            else
            {
                logger.error("Error en comunicación con servidor de simulación");
            }
        }
        return "formResult";
    }
}