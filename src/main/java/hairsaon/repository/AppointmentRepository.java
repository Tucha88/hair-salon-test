package hairsaon.repository;

import hairsaon.models.personal_models_for_schedule.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Boris on 28.05.2017.
 */
public interface AppointmentRepository extends MongoRepository<Appointment, String> {
}
