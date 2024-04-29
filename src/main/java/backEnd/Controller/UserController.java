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

@RestController
public class UserController {

        private static final Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);

        @Autowired
        private UserDetailsServiceIml userDetailsServiceIml;

        public UserController(UserDetailsServiceIml userDetailsServiceIml) {
            this.userDetailsServiceIml = userDetailsServiceIml;
        }

        @PostMapping("/api/register")
        public ResponseEntity<?> registerUser(@RequestBody @Validated User user) {
            return userDetailsServiceIml.saveUser(user) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
    }