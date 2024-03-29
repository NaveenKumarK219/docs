package navin.web.docs.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import navin.web.docs.model.UserInfo;
import navin.web.docs.repository.UserInfoRepository;

@Repository
@Transactional
public class UserInfoService {
	
	@Autowired
	private UserInfoRepository usersRepository;
	
	public UserInfo getUserInfoByEmail(String email) {
		return usersRepository.findByEmailAndActive(email, true);
	}
	
	public List<UserInfo> getAllActiveUsers(){
		return usersRepository.findAllByActive(true);
	}

	public List<UserInfo> getAllUsers(){
		return usersRepository.findAll();
	}
	
	public UserInfo getUserInfoById(int id) {
		return usersRepository.findById(id);
	}
	
	public UserInfo addUser(UserInfo userInfo) {
		userInfo.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword()));
		return usersRepository.save(userInfo);
	}
	
	public UserInfo updateUser(UserInfo userInfo) {
		return usersRepository.save(userInfo);
	}
	
	public void deleteUser(int id) {
		usersRepository.deleteById(id);
	}

	public UserInfo blockUser(int id){
		UserInfo userInfo = usersRepository.findById(id);
		userInfo.setActive(false);
		return usersRepository.save(userInfo);
	}

	public UserInfo unBlockUser(int id){
		UserInfo userInfo = usersRepository.findById(id);
		userInfo.setActive(true);
		return usersRepository.save(userInfo);
	}

}
