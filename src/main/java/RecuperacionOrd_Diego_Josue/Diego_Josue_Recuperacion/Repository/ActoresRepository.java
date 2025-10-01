package RecuperacionOrd_Diego_Josue.Diego_Josue_Recuperacion.Repository;

import RecuperacionOrd_Diego_Josue.Diego_Josue_Recuperacion.Entity.ActoresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActoresRepository extends JpaRepository<ActoresEntity, Long> {

    ActoresEntity findAllById(Long id);
}
