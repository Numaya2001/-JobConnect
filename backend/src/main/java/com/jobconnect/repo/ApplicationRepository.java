package com.jobconnect.repo;
import com.jobconnect.model.JobApplication; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface ApplicationRepository extends JpaRepository<JobApplication,Long>{
 boolean existsByJobIdAndApplicantId(Long jobId,Long applicantId);
 List<JobApplication> findByApplicantId(Long applicantId);
 List<JobApplication> findByJobEmployerId(Long employerId);
}
