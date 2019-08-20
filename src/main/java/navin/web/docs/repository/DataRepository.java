package navin.web.docs.repository;

import navin.web.docs.model.DocsData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface DataRepository extends JpaRepository<DocsData, Serializable> {

    public DocsData findByFileName(String fileName);
}
