package com.mycompany.infornel.logica;

import com.mycompany.infornel.logica.Usuario;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-09-29T23:26:14", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(FechaHora.class)
public class FechaHora_ { 

    public static volatile SingularAttribute<FechaHora, Integer> fecha;
    public static volatile SingularAttribute<FechaHora, Integer> horaEntrada;
    public static volatile SingularAttribute<FechaHora, Usuario> usuario;
    public static volatile SingularAttribute<FechaHora, Integer> id;
    public static volatile SingularAttribute<FechaHora, String> dia;
    public static volatile SingularAttribute<FechaHora, Integer> horaSalida;

}