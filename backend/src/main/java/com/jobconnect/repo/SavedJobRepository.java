package com.jobconnect.repo;
import com.jobconnect.model.SavedJob; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List; import java.util.Optional;
public interface SavedJobRepository extends JpaRepository<SavedJob,Long>{
 List<SavedJob> findByUserId(Long userId); Optional<SavedJob> findByJobIdAndUserId(Long jobId,Long userId);
}
