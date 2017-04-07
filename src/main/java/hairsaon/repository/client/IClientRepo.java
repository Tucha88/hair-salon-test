package hairsaon.repository.client;

import hairsaon.models.client.Client;
import hairsaon.models.client.ClientAuthType;

import java.util.List;

/**
 * Created by Boris on 06.04.2017.
 */
public interface IClientRepo {
    public Client registerClient(Client addClient);
    public Client getClientInfo(Client client);
    public Client loginClient(ClientAuthType updateClient);
    public String deliteClient(Client removeClient);
    public List<Client> getAllClientsInfo();
    public Client updateClientInfo(Client Client);
  
}
