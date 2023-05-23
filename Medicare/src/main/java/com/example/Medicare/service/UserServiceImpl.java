package com.example.Medicare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Medicare.entity.User;
import com.example.Medicare.exception.DuplicateEmailException;
import com.example.Medicare.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public User create(User user) throws DuplicateEmailException {

		User users = userRepository.findByEmail(user.getEmail());
		
		System.out.println("this is list of users " +users);

		if (users!=null) {
			throw new DuplicateEmailException(user.getEmail());
		} else {
			userRepository.save(user);
			return user;
		}

	}

	@Override
	public User login(String email, String password) {
		User user = userRepository.findByEmail(email);
		
		if(user!=null && user.getEmail().equals(email) && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

	@Override
	public User findbyemail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

}
