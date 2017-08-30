package r1g.atrisini.controllers;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import r1g.atrisini.models.User;
import r1g.atrisini.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/users")
public class UserController {

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;
  
  @RequestMapping(method = RequestMethod.GET)
  public User findByEmail(@RequestParam("email") String email) {
    logger.debug("Looking up user with email: " + email);
    return userService.findByEmail(email);
  }

  @RequestMapping(method = RequestMethod.GET, value="/{id}")
  public User findById(@PathVariable Long id) {
    logger.debug("Looking up user with id: " + id);
    return userService.findById(id);
  }

  @RequestMapping(method = RequestMethod.POST, value="/create")
  public User createUser(@RequestBody User user) throws Exception {
    logger.debug("Creating a new user with email: " + user.getEmail());
    return userService.createUser(user.getEmail(), user.getFullName(), user.getPassword());
  }

  @ExceptionHandler(Exception.class)
  public HashMap exception(HttpServletRequest req, Throwable ex) {
    HashMap error = new HashMap();
    error.put("error", ex.getMessage());
    return error;
  }
}
