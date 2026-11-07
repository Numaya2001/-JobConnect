package com.jobconnect.repo;
import com.jobconnect.model.Job; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface JobRepository extends JpaRepository<Job,Long>{
 List<Job> findByTitleContainingIgnoreCaseOrCompanyNameContainingIgnoreCaseOrLocationContainingIgnoreCase(String title,String company,String location);
 List<Job> findByEmployerId(Long employerId);
}
