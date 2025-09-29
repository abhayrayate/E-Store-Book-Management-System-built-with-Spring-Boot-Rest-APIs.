package in.abhayit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.abhayit.Entity.FilesEntity;

@Repository
public interface FileRepo extends JpaRepository<FilesEntity, Long>{

	
}
