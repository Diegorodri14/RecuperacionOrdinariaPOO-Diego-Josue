package RecuperacionOrd_Diego_Josue.Diego_Josue_Recuperacion.Controllers;


import RecuperacionOrd_Diego_Josue.Diego_Josue_Recuperacion.Models.DTO.ActoresDTO;
import RecuperacionOrd_Diego_Josue.Diego_Josue_Recuperacion.Services.ActoresService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.hibernate.engine.jdbc.mutation.spi.BindingGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/Autores")
public class ActoresController {

    @Autowired
    private ActoresService service;
    @GetMapping("/VerActores")
    public ResponseEntity<List<ActoresDTO>> ObtenerActor(){
        return ResponseEntity.ok(service.ObtenerActor());
    }




    @PostMapping("/InsertarActor")
    public ResponseEntity <?>AgregarActor(@Valid @RequestBody ActoresDTO json, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()){
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errores.put(error.getField(),error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);


        }try {
            ActoresDTO respuesta = service.AgregarActor(json);
            return ResponseEntity.status(HttpStatus. CREATED)
                    .body(Map.of(
                            "status", "Registrado",
                            "data", respuesta
                    ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "status", "Error",
                            "message", "Error al registrar",
                            "detail", e.getMessage()
                    ));
        }
    }


    @PutMapping("/EditarActor/{id}")
    public ResponseEntity<?> ActualizarActor(@PathVariable Long id, @Valid @RequestBody ActoresDTO data,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            Map<String ,String > errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error->errores.put(error.getField(),error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }
        try{
            ActoresDTO dto = service.ActualizarActor(id, data);
            return ResponseEntity.ok(Map.of(
                    "Mensaje", "Registro Actualizado",
                    "data", dto
            ));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(Map.of(
                    "status", "Error",
                    "message", "Error al actualizar",
                    "details", e.getMessage()
            ));
        }
    }
}
