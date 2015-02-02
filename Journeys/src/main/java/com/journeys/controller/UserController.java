package com.journeys.controller;

import java.util.HashSet;
import java.util.Set;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.journeys.entity.Journey;
import com.journeys.entity.Role;
import com.journeys.entity.User;
import com.journeys.service.DayManager;
import com.journeys.service.JourneyManager;
import com.journeys.service.RoleManager;
import com.journeys.service.ServiceException;
import com.journeys.service.UserManager;
import com.journeys.util.RequestUtil;
import com.journeys.util.SessionUtil;

@Controller
public class UserController {
	
    @Autowired
    private UserManager userManager;
    
    @Autowired
    private JourneyManager journeyManager;
    
    @Autowired
    private RoleManager roleManager;
    
    @Autowired
    private DayManager dayManager;
	
	@RequestMapping(value = {"/admin/user", "/admin/user/edit"}, method = RequestMethod.GET)
	public String listJourneysOfUser(ModelMap map) 
	{
		map.addAttribute("user", new User());
		map.addAttribute("userList", userManager.getAllUsers());
		
		return "admin/users";
	}
	
	@RequestMapping(value = {"/admin/user/{userId}"}, method = RequestMethod.GET)
	public String listUsers(ModelMap map, @PathVariable(value="userId") Integer userId) 
	{
		User user = userManager.getUserById(userId);
		
		map.addAttribute("journey", new Journey());
		map.addAttribute("journeyList", user.getJourneys());
		map.addAttribute("user", user);
		
		return "admin/user";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.GET)
	public String displayAddUser(HttpServletRequest request, ModelMap map, @ModelAttribute(value="user") User user, BindingResult result) 
	{
		SessionUtil.refreshAuthenticatedUser(request, userManager);
		
		map.addAttribute("user", new User());
		return "admin/user_edit";
	}

    @RequestMapping(value = "/user/edit/{userId}", method = RequestMethod.GET)
    public String displayEditUser(HttpServletRequest request, ModelMap map, @PathVariable(value="userId") Integer userId) 
    {
    	SessionUtil.refreshAuthenticatedUser(request, userManager);
    	
    	User currentUser = SessionUtil.getAuthenticatedUser(request);
    	
        User user = userManager.getUserById(userId);
        
        map.addAttribute("user", user);
        
        if (currentUser != null) {
        	if (currentUser.isAdmin()) {
        		return "admin/user_edit";
        	}
        	// My Account
        	if (currentUser.getEmail().equals(user.getEmail())) {
        		return "admin/user_edit";
        	}
	        return "403";
        } else {
        	return "login";
        }
                
    }
    
    @RequestMapping(value = {"/user/edit/", "/user/edit/{userId}"}, method = RequestMethod.POST)
    public String editUser(HttpServletRequest request, ModelMap map, @ModelAttribute(value="user") User newUser, BindingResult result, @RequestParam(value = "valid", required=false) String valid, @RequestParam(value = "cancel", required=false) String cancel, @RequestParam(value = "confirmPassword", required=false) String confirmPassword) 
    {
        if ((valid != null) && (valid != "")) {
        	
            String returnJsp = "admin/user_edit";
        	User user = null; 
        	
        	if (newUser.getId() != null) {
        		
        		if (SessionUtil.isAdminUser(request)) {

	        		// Edit
	        		user = userManager.getUserById(newUser.getId());
	                user.setEmail(newUser.getEmail());
	                user.setFirstName(newUser.getFirstName());
	                user.setLastName(newUser.getLastName());
	                if ((newUser.getPassword() != null) && (newUser.getPassword() != "")) {
	                    user.setPassword(newUser.getPassword());
	                }
	                user.setEnabled(true);
	                
	                try {
						userManager.editUser(user);
						RequestUtil.setSuccessMessage(map, "Modification effectuée avec succès");
					} catch (ServiceException e) {
						RequestUtil.setErrorMessage(map, e.getMessage());
					}
	                
        		} else {
        			RequestUtil.setErrorMessage(map, "Permission refusée");
        		}
                
        	} else {
        		
        		// Add
        		user = newUser;
        		
        		// Roles
        		Set<Role> roles = new HashSet<Role>();
        		Role adminRole = roleManager.getRoleByName("ROLE_USER");
        		roles.add(adminRole);
        		user.setRoles(roles);
        		
        		if (newUser.getPassword().equals(confirmPassword)) {
                    user.setPassword(newUser.getPassword());

                    user.setEnabled(false);
                    
            		try {
    					userManager.addUser(user);
    					
                        userManager.sendValidationLink(user);
                        
    					RequestUtil.setSuccessMessage(map, "Ajout effectué avec succès. Vous allez recevoir un email pour valider votre compte.");
    					
    					returnJsp = "login";
    				} catch (ServiceException e) {
    					RequestUtil.setErrorMessage(map, e.getMessage());
    				} catch (MessagingException me) {
    					userManager.deleteUser(user.getId());
    					RequestUtil.setErrorMessage(map, "Erreur lors de l'envoi de l'email de validation. Veuillez vous réinscrire.");
    				}
        		} else {
        			RequestUtil.setErrorMessage(map, "La confirmation du mot de passe est différente du mot de passe saisi");
        		}

        	}
            
        	map.addAttribute("user", user);
        	        	
        	return returnJsp;
        }
        
        return "redirect:/app/admin/user";
        
    }
    
    @RequestMapping(value = {"/user/validate/{userId}"}, method = RequestMethod.GET)
    public String validateUser(HttpServletRequest request, ModelMap map, @PathVariable("userId") Integer userId, @RequestParam(value = "key", required=true) String validationKey) 
    {
        User user = userManager.getUserById(userId);
        String realKey = user.getValidationKey();
        
        if (realKey.equals(validationKey)) {
        	
        	userManager.activateUser(user);
            RequestUtil.setSuccessMessage(map, "Votre compte a été bien activé. Vous pouvez maintenant vous connecter !");
        } else {
        	RequestUtil.setErrorMessage(map, "Erreur lors de l'activation de votre compte");
        }

        return "login";
        
    }

    @RequestMapping(value = {"/user/subscribe/{userId}"}, method = RequestMethod.GET)
    public String subscribeUser(HttpServletRequest request, ModelMap map, @PathVariable("userId") Integer userId) 
    {
        User user = userManager.getUserById(userId);
        User currentUser = SessionUtil.getAuthenticatedUser(request);
        
        if (currentUser != null && user != currentUser) {
        	
        	userManager.subscribeUser(currentUser.getId(), user.getId());
            RequestUtil.setSuccessMessage(map, "Vous suivez maintenant les voyages de " + user.getFullName());
            
            return "home";
        } else {
        	return "403";
        }
        
    }
    
	@RequestMapping("/admin/user/delete/{userId}")
	public String deleteUser(ModelMap map, @PathVariable("userId") Integer userId)
	{
		userManager.deleteUser(userId);
		
		map.addAttribute("user", new User());
		map.addAttribute("userList", userManager.getAllUsers());
		
		return "admin/users";
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
}
