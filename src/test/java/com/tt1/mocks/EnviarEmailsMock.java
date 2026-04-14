package com.tt1.mocks;

import com.tt1.simwebapp.logica.InterfazEnviarEmails;
import com.tt1.simwebapp.modelo.Destinatario;
import org.slf4j.Logger;

public class EnviarEmailsMock implements InterfazEnviarEmails
{
    private Logger logger;

    public EnviarEmailsMock(Logger logger)
    {
        this.logger = logger;
    }

    @Override
    public boolean enviarEmail(Destinatario dest, String email)
    {
        logger.info("Solicitud de envío de email: - destinatario: {} - mensaje: {}", dest, email);
        return true;
    }
}
