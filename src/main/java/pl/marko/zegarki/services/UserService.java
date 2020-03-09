package pl.marko.zegarki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.marko.zegarki.entity.Role;
import pl.marko.zegarki.entity.User;
import pl.marko.zegarki.entity.UserJoinInterface;
import pl.marko.zegarki.repository.RoleRepository;
import pl.marko.zegarki.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.*;

import static java.lang.Integer.valueOf;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(@Qualifier("userRepository") UserRepository userRepository,
                       @Qualifier("roleRepository" +
                               "") RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public ArrayList<UserJoinInterface> getUsersAndRoles() {
        return (ArrayList<UserJoinInterface>) userRepository.findAUsersAndRole();
    }

    public void updateUserActive(int active, int id) {
        userRepository.setUserActive(active, id);
    }

}
