package hairsaon.repository.client;

import hairsaon.models.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Boris on 06.04.2017.
 */

public interface ClientRepository extends JpaRepository<Client, String> {
    Client findClientByClientEmail(String email);

}
