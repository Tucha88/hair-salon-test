package hairsaon.repository.client;

import hairsaon.models.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Boris on 06.04.2017.
 */
@Transactional
@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
        Client findClientByClientEmail(String email);

}
