package com.mz.sge.auth.service;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.mz.sge.auth.repository.UserRepository;
import com.mz.sge.auth.dto.UserRegisterDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.mz.sge.auth.user.CustomUser;
import com.mz.sge.auth.user.UserRole;
import com.mz.sge.auth.exception.UserAlreadyExistsException;


@Service
public class CustomUserDetailsService implements UserDetailsService{


private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;

public CustomUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder){
this.userRepository=userRepository;
this.passwordEncoder=passwordEncoder;
}

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
}


public void registerUser(UserRegisterDTO data){
if(userRepository.existsByUsername(data.username())){
	throw new UserAlreadyExistsException();
}

String encodedPassword= passwordEncoder.encode(data.password());
CustomUser newUser= new CustomUser(data.username(),encodedPassword,UserRole.USER);
userRepository.save(newUser);
}



}
