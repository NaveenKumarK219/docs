package navin.web.docs.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import navin.web.docs.model.UserInfo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserInfoService userInfoDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo = userInfoDao.getUserInfoByEmail(username);
		GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole());
		return new User(userInfo.getEmail(), userInfo.getPassword(), Arrays.asList(authority));
	}

}
