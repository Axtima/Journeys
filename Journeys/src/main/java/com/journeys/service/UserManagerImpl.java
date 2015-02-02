package com.journeys.service;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.journeys.dao.UserDAO;
import com.journeys.entity.User;
import com.journeys.util.MailService;
import com.journeys.util.PermissionUtil;
import com.journeys.util.Validator;

@Service
public class UserManagerImpl implements UserManager {
	
	private static final String VALIDATION_KEY_SALT = "&;rM_4nQ7!";

	@Autowired
	private MailService mailService;
	
	@Autowired
    private UserDAO userDAO;

	@Transactional
	public void addUser(User user) throws ServiceException {
		
		validate(user, true, true);
		user.setCreationDate(Calendar.getInstance().getTime());
		
		// Encrypt password
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		
		userDAO.addUser(user);
	}

    @Transactional
    public User getUserById(Integer userId) {
        return userDAO.getUserById(userId);
    }
    
    @Transactional
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }
	   
	@Transactional
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

    @Transactional
    public void editUser(User user) throws ServiceException {
    	
    	editUser(user, true);
    }
    
    @Transactional
    public void editUser(User user, boolean checkPassword) throws ServiceException {
    	
    	validate(user, false, checkPassword);
		
		user.setLastConnectionDate(Calendar.getInstance().getTime());

		// Encrypt password
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		
        userDAO.editUser(user);
    }
    
    @Transactional
    public void sendValidationLink(User user) throws MessagingException {
    	
    	User oldUser = userDAO.getUserById(user.getId());
    	oldUser.setValidationKey(PermissionUtil.encodeSha256(user.getEmail() + VALIDATION_KEY_SALT));
        mailService.sendValidationLink(oldUser);
        userDAO.editUser(oldUser);
    }
    
    @Transactional
    public void activateUser(User user) {
    	
    	User oldUser = userDAO.getUserById(user.getId());
    	oldUser.setEnabled(true);
    	oldUser.setValidationKey(null);
    	userDAO.editUser(oldUser);
    }
    
    @Transactional
    public void refreshLastConnectionDate(User user) {
    	
    	User oldUser = userDAO.getUserById(user.getId());
    	oldUser.setLastConnectionDate(Calendar.getInstance().getTime());
    	userDAO.editUser(oldUser);
    }
    
    @Transactional
    public void subscribeUser(Integer subscribedUserId, Integer followedUserId) {
    	
    	User subscribedUser = userDAO.getUserById(subscribedUserId);
    	User followedUser = userDAO.getUserById(followedUserId);
    	
    	Set<User> alreadySubscribedUsers = subscribedUser.getSubscribedUsers();
    	
    	if (!alreadySubscribedUsers.contains(subscribedUser)) {
    		alreadySubscribedUsers.add(followedUser);
    	}
    	
    	subscribedUser.setSubscribedUsers(alreadySubscribedUsers);

    	userDAO.editUser(subscribedUser);
    }
    
	@Transactional
	public void deleteUser(Integer userId) {
		userDAO.deleteUser(userId);
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	private boolean validate(User user, boolean add, boolean checkPassword) throws ServiceException {
		
		if (!Validator.isEmail(user.getEmail())) {
			throw new ServiceException("Veuillez saisir une adresse email valide");
		}
		
		if (add) {
			User userWithSameEmail = getUserByEmail(user.getEmail());
			if (userWithSameEmail != null) {
				throw new ServiceException("Un compte avec cet email existe déjà");
			}
		}
		
		if (Validator.isNull(user.getFirstName())) {
			throw new ServiceException("Veuillez saisir votre prénom");
		}
		
		if (checkPassword && !Validator.checkPassword(user.getPassword())) {
			throw new ServiceException("Votre mot de passe ne respecte pas les critères minimum. Il doit faire entre 6 et 30 caractères et contenir au moins une minuscule, une majuscule ainsi qu'un chiffre");
		}
		
		return true;
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
	
}
