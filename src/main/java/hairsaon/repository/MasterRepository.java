package hairsaon.repository;


import hairsaon.models.Master;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Boris on 06.04.2017.
 * Интерфейс для записи Мастера в базу данных
 */

//TODO добавить методы для нахождения мастера по отдельным полям

public interface MasterRepository extends MongoRepository<Master,String> {
    Master findByEmail(String email);
}
