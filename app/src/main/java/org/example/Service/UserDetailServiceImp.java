package org.example.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.Model.UserInfoDto;
import org.example.entities.UsersInfo;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
@Data
public class UserDetailServiceImp implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersInfo user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new CustomeUserDetail(user);
    }

    public UsersInfo checkIfUserExists(UserInfoDto userInfoDto) {
        return userRepository.findByUsername(userInfoDto.getUsername());
    }

    public Boolean signUpUser(UserInfoDto userInfoDto) {
//        validate the user info
       userInfoDto.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));
       if(Objects.nonNull(checkIfUserExists(userInfoDto))) {
           return false;
       }
       String userId = UUID.randomUUID().toString();
        userRepository.save(new UsersInfo(userId, userInfoDto.getUsername(), userInfoDto.getPassword(),new HashSet<>()));
        return true;
    }
}
