package RecuperacionOrd_Diego_Josue.Diego_Josue_Recuperacion.Services;

import RecuperacionOrd_Diego_Josue.Diego_Josue_Recuperacion.Entity.ActoresEntity;
import RecuperacionOrd_Diego_Josue.Diego_Josue_Recuperacion.Exception.ExceptionCampoNoRegistrado;
import RecuperacionOrd_Diego_Josue.Diego_Josue_Recuperacion.Models.DTO.ActoresDTO;
import RecuperacionOrd_Diego_Josue.Diego_Josue_Recuperacion.Repository.ActoresRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ActoresService {

    @Autowired
    private ActoresRepository repo;

    private ActoresEntity ConvertirEntity (ActoresDTO data){

        ActoresEntity entity = new ActoresEntity();
        entity.setId(data.getID());
        entity.setNombre(data.getNombre());
        entity.setFecha_Nacimiento(data.getFecha_Nacimiento());
        entity.setNacionalidad(data.getNacionalidad());
        entity.setPremios_oscar(data.getPremios_Oscar());
        entity.setSitio_web(data.getSitio_Web());
        return entity;
    }

    private ActoresDTO ConvertirADTO (ActoresEntity entity){
        ActoresDTO dto = new ActoresDTO();
        dto.setID(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setFecha_Nacimiento(entity.getFecha_Nacimiento());
        dto.setNacionalidad(entity.getNacionalidad());
        dto.setPremios_Oscar(entity.getPremios_oscar());
        dto.setSitio_Web(entity.getSitio_web());
        return dto;
    }



    public List <ActoresDTO> ObtenerActor(){
        List<ActoresEntity> list = repo.findAll();
        return list.stream()
                .map(this::ConvertirADTO)
                .collect(Collectors.toList());
    }

    public ActoresDTO AgregarActor(@Valid ActoresDTO data)
    {
        if (data == null || data.getID() == null ){throw new IllegalArgumentException("Campo nulo");}
        try{
            ActoresEntity entity = ConvertirEntity(data);
            ActoresEntity eventoGuardado = repo.save(entity);
            return ConvertirADTO(eventoGuardado);
        } catch (Exception e) {
            log.error("Error al registrar " + e.getMessage());
            throw new ExceptionCampoNoRegistrado("Error al registrar dato" + e.getMessage());
        }
    }

    public ActoresDTO ActualizarActor(Long id, @Valid ActoresDTO json){
        ActoresEntity existente = repo.findAllById(id);
        existente.setNombre(json.getNombre());
        existente.setFecha_Nacimiento(json.getFecha_Nacimiento());
        existente.setNacionalidad(json.getNacionalidad());
        existente.setPremios_oscar(json.getPremios_Oscar());
        existente.setSitio_web(json.getSitio_Web());

        ActoresEntity actualizar = repo.save(existente);
        return ConvertirADTO(actualizar);

    }

    public Boolean BorrarActor(Long id){
        try{

            ActoresEntity existente = repo.findAllById(id);
            if(existente != null)
            {
                repo.deleteById(id);
                return true;
            }else{
                return false;
            }
        }catch (EmptyResultDataAccessException e){
            throw new EmptyResultDataAccessException("Dato no encontrado" + id + "para eliminar",1);
        }
    }
}
