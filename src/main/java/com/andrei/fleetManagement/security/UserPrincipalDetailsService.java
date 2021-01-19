package com.andrei.fleetManagement.security;

import com.andrei.fleetManagement.domain.User;
import com.andrei.fleetManagement.persistance.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("This user " + s + " does not exist"));

        return new UserPrincipal(user);
    }
}
