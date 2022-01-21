package recipes.businesslayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipes.persistence.UserRepository;
import recipes.SecurityConfiguration;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

//    public boolean existsById(String email) {
//        return this.userRepository.existsById(email);
//    }

    public void save(User user) {
        if (this.userRepository.existsById(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        user.setPassword(securityConfiguration.getEncoder().encode(user.getPassword()));
        this.userRepository.save(user);
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findById(email);
        user.orElseThrow(() -> new UsernameNotFoundException(email + " not found."));
        return user.get();
    }
}
