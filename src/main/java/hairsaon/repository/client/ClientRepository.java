package hairsaon.repository.client;

import hairsaon.models.client.Client;
import hairsaon.models.master.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Boris on 06.04.2017.
 */

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT a FROM Client a WHERE a.clientEmail = :clientEmail")
    Master findClientByClientEmail(@Param("clientEmail") String email);

}
