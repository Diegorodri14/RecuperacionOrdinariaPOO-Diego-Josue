package RecuperacionOrd_Diego_Josue.Diego_Josue_Recuperacion.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "ACTORES")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ActoresEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = " seq_actores_id")
    @SequenceGenerator(name = " seq_actores_id",sequenceName = " seq_actores_id", allocationSize = 1)
    private Long id;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_NACIMIENTO")
    private Date fecha_Nacimiento;

    @Column(name = "NACIONALIDAD", nullable = false)
    private String nacionalidad;

    @Column(name = "PREMIOS_OSCAR", nullable = false)
    private Number premios_oscar;

    @Column(name = "SITIO_WEB", unique = true)
    private String sitio_web;
}
