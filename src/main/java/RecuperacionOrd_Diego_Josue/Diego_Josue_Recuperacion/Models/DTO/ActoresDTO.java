package RecuperacionOrd_Diego_Josue.Diego_Josue_Recuperacion.Models.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ActoresDTO {

    private Long ID;

    @NotNull(message = "El nombre no debe de ser nulo")
    private String Nombre;

    @Past(message = "La fecha debe de ser en pasado")
    private Date Fecha_Nacimiento;

    @NotNull(message = "La nacionalidad no debe de ser nula")
    private String Nacionalidad;

    @Min(message = "Los premios deben de ser un numero positivo",value = 0)
    private Number Premios_Oscar;


    private String Sitio_Web;


}