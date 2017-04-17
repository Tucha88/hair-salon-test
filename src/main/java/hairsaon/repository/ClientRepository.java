package hairsaon.repository;

import hairsaon.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Boris on 06.04.2017.
 * Интерфейс для записи Клиента в базу данных
 */

//TODO создать запросы поиска по отдельным полям
@Transactional
@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
        Client findClientByClientEmail(String email);

        Client findClientByToken(String token);

}
