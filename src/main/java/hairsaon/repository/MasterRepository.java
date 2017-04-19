package hairsaon.repository;

import hairsaon.models.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Boris on 06.04.2017.
 * Интерфейс для записи Мастера в базу данных
 */

//TODO добавить методы для нахождения мастера по отдельным полям
@Transactional
@Repository
public interface MasterRepository extends JpaRepository<Master, String> {
    Master findByEmail(String email);
}
