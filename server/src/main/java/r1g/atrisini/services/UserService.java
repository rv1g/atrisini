package r1g.atrisini.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import r1g.atrisini.models.User;
import r1g.atrisini.repos.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

  private static final Logger logger = LoggerFactory.getLogger(UserService.class);
  @Autowired
  private UserRepository userRepository;
  
  public User findByEmail(String email) {
    return userRepository.findByEmail(email);
  }
  
  public User findById(Long id) {
    return userRepository.findOne(id);
  }
  
  public User createUser(String email, String fullName, String password) throws Exception {
    email = email.toLowerCase().trim();
    // first see if user already exists
    User user = findByEmail(email);
    if (user != null)
      throw new Exception("User with this email already exists");
    user = new User();
    user.setEmail(email);
    user.setFullName(fullName);
    user.setPassword(encodePassword(password));
    userRepository.save(user);
    return user;
  }
  
  public void createAdminUser() {
    User adminUser = findByEmail("admin");
    if (adminUser == null) {
      try {
        adminUser = createUser("admin", "Admins", "test123");
      } catch (Exception e) {}
      logger.info("Created ADMIN user");
    }
  }
  
  private String encodePassword(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }
  private boolean validateBCrypt(String password, String encoded) {
    return BCrypt.checkpw(password, encoded);
  }
}
