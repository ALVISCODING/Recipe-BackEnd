package backEnd.Controller;

import backEnd.Service.UserDetailsServiceIml;
import backEnd.Entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing user-related actions such as registration.
 * This class handles HTTP requests for user management.
 */
@RestController
public class UserController {

        //private static final Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);

        @Autowired
        private UserDetailsServiceIml userDetailsServiceIml;

        public UserController(UserDetailsServiceIml userDetailsServiceIml) {
            this.userDetailsServiceIml = userDetailsServiceIml;
        }

    /**
     * Endpoint for registering a new user. The user's data is validated before saving.
     * If the user is successfully saved, it returns a 200 OK response.
     * Otherwise, it returns a 400 Bad Request response.
     *
     * @param user The user data to register. Must pass validation constraints.
     * @return ResponseEntity indicating the result of the registration operation.
     */

        @PostMapping("/api/register")
        public ResponseEntity<?> registerUser(@RequestBody @Validated User user) {
            return userDetailsServiceIml.saveUser(user) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
    }