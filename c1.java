package com.stackroute.keepnote.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.keepnote.dao.UserDAO;
import com.stackroute.keepnote.model.User;


@RestController
@RequestMapping("api/v1/user")
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	
	
	@GetMapping
	public ResponseEntity<?> getAllUsers(HttpSession session){
		
		String loggeduser = (String) session.getAttribute("loggeduser");
		if(loggeduser != null ) {
			if(loggeduser.equalsIgnoreCase("admin")) {
				List<User> userlist = userDAO.getAllUsers();
				return new ResponseEntity<List<User>>(userlist, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}
	

	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody User user){+
		userDAO.save(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<User> updateUser(@RequestBody User user){
		userDAO.updateUser(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<?> removeUser(@RequestBody User user){
		userDAO.save(user);
		return new ResponseEntity<String>("user is removed", HttpStatus.OK);
	}
	
	
	
	
	
	
}
