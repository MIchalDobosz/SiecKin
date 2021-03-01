package com.michal.SiecKin.serviceImpl;

import com.michal.SiecKin.entity.LoadedUser;
import com.michal.SiecKin.entity.User;
import com.michal.SiecKin.form.UserForm;
import com.michal.SiecKin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new LoadedUser(user);
    }

    public User userFormToNewUser(UserForm userForm) {
        return new User(userForm.getUsername(), passwordEncoder.encode(userForm.getPassword()), userForm.getRole());
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
