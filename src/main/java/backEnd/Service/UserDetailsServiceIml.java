package backEnd.Service;

import backEnd.Entity.UserDetailsIml;
import backEnd.Entity.User;
import backEnd.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceIml implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserDetailsServiceIml(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new UserDetailsIml(user);
    }


    /**
     * To register a new user and check if the user already exist in the database
     * @param user
     * @return
     */
    public boolean saveUser(User user){

        Optional<User> inputUser = userRepository.findByEmailIgnoreCase(user.getEmail());

        if(inputUser.isPresent()){
            return false;
        } else {
            // No user found with the email, proceed to save the new user
            User newUser = new User();
            newUser.setEmail(user.getEmail()); // Use the email from the parameter
            newUser.setPassword(passwordEncoder.encode(user.getPassword())); // Encode and set the password from the parameter
            userRepository.save(newUser);
        }
        return true;
    }

}
