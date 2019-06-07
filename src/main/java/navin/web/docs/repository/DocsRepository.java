package navin.web.docs.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import navin.web.docs.model.MenuDetails;

@Repository
public interface DocsRepository extends JpaRepository<MenuDetails, Serializable>{

	@Query(value = "select * from table_of_contents where active=true order by id", nativeQuery = true)
    public List<MenuDetails> getMenuDetails();

    @Query(value = "select file_name from table_of_contents where title=?", nativeQuery = true)
    public String getDocFileName(String title);

    // Gets all from TableOfContents order by id
    public List<MenuDetails> findAllByOrderById();

    public MenuDetails findByTitle(String title); 
}
