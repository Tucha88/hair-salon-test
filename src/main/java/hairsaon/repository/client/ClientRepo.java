package hairsaon.repository.client;

import hairsaon.models.client.Client;
import hairsaon.models.client.ClientAuthType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Boris on 06.04.2017.
 */
@Transactional
@Repository
public class ClientRepo implements IClientRepo {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Client registerClient(Client addClient) {
        if(entityManager.find(addClient.getClass(), addClient.getClientEmail())!=null){
            return null;
        }

        entityManager.persist(addClient);

        return addClient;
    }

    @Override
    public Client getClientInfo(Client client) {
        return null;
    }

    @Override
    public Client loginClient(ClientAuthType updateClient) {
        Client client = new Client();
        if((client = entityManager.find(Client.class, updateClient.getClientEmail()))!=null){
//			Master master = em.find(Master.class, updateMaster.getEmail());
            if(updateClient.getClientPassword().equals(client.getClientPassword())){
                return client;
            }
        }
        return null;
    }

    @Override
    public String deliteClient(Client removeClient) {
        return null;
    }

    @Override
    public List<Client> getAllClientsInfo() {
        return null;
    }

    @Override
    public Client updateClientInfo(Client Client) {
        return null;
    }
}
