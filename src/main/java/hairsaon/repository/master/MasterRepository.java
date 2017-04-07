package hairsaon.repository.master;

import hairsaon.models.master.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Boris on 06.04.2017.
 */
@Transactional
@Repository
public interface MasterRepository extends JpaRepository<Master, String> {
    Master findByEmail(String email);
    Master save(Master master);
}
