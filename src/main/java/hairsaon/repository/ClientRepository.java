package hairsaon.repository;


import hairsaon.models.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Boris on 06.04.2017.
 * Интерфейс для записи Клиента в базу данных
 */

//TODO создать запросы поиска по отдельным полям

public interface ClientRepository extends MongoRepository<Client, String> {
        Client findClientByClientEmail(String email);

}
