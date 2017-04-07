package hairsaon.repository.master;

import hairsaon.models.master.Master;
import hairsaon.models.master.MasterAuthType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Boris on 01.04.2017.
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
@Repository
public class MasterRepo implements IMasterRepo {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private MasterRepository masterRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Master registerMaster(Master addMaster) {
        if (entityManager.find(Master.class, addMaster.getEmail()) != null) {
            return null;
        }
        entityManager.persist(addMaster);

        return addMaster;

//        return masterRepository.save(addMaster);

    }

    @Override
    public Master getMasterInfo(Master master) {
        if (entityManager.find(Master.class, master.getEmail()) == null) {
            return null;
        }
        return master;

    }

    @Override
    public Master loginMaster(MasterAuthType updateMaster) {
        Master master = new Master();
        if((master = entityManager.find(Master.class, updateMaster.getEmail()))!=null){
            if(master.getPassword().equals(updateMaster.getPassword())){
                return master;
            }
        }
        return null;
    }

    @Override
    public String deliteMaster(Master removeMaster) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Master> getAllMastersInfo() {
        return null;
    }

    @Override
    public Master updateMasterInfo(Master master) {
        Master master1 = entityManager.find(Master.class,master.getEmail());
        if (master1 == null){
            return null;
        }
        master1.setPhoneNumber(master.getPhoneNumber());
        master1.setName(master.getName());
        master1.setLastName(master.getLastName());
        entityManager.flush();
        return master1;
    }

}
