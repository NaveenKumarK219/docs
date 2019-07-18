package navin.web.docs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import navin.web.docs.model.UserInfo;

@Repository
@Transactional
public interface UserInfoRepository extends CrudRepository<UserInfo, String>{

	public UserInfo findByEmailAndActive(String email, boolean active);
	
	public List<UserInfo> findAllByActive(boolean active);
	
	public UserInfo findById(int id);
	
	@SuppressWarnings("unchecked")
	public UserInfo save(UserInfo userInfo);
	
	public void deleteById(int id);

}
