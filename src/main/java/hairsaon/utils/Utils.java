package hairsaon.utils;

import hairsaon.models.client.Client;
import hairsaon.models.master.Master;
import org.springframework.stereotype.Service;

/**
 * Created by Boris on 06.04.2017.
 */
@Service
public class Utils implements IUtils {
    @Override
    public Boolean isLoginInfoExist(Object obj) {
        if (obj instanceof Master){
            Master master = (Master) obj;
            if (master.getEmail().equals("") || master.getEmail() == null || master.getPassword().equals("") || master.getPassword() == null){
                return true;// Wrong login or password
            }
        }else if (obj instanceof Client){
            Client client = (Client) obj;
            if (client.getClientEmail().equals("") || client.getClientPassword().equals("") || client.getClientEmail() == null || client.getClientPassword() == null) {
                return true;
            }
        }
        return false;
    }
}
