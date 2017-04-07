package hairsaon.repository.master;

import hairsaon.models.master.Master;
import hairsaon.models.master.MasterAuthType;

import java.util.List;

/**
 * Created by Boris on 01.04.2017.
 */
public interface IMasterRepo {
    public Master registerMaster(Master addMaster);
    public Master getMasterInfo(Master master);
    public Master loginMaster(MasterAuthType updateMaster);
    public String deliteMaster(Master removeMaster);
    public List<Master> getAllMastersInfo();
    public Master updateMasterInfo(Master master);
}
