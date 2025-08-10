package au.com.telstra.simcardactivator.repository;

import au.com.telstra.simcardactivator.model.SimCardRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimCardRecordRepository extends JpaRepository<SimCardRecord, Long> {

}
