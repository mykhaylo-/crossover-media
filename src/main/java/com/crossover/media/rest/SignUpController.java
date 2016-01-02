package com.crossover.media.rest;

import java.security.Principal;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crossover.media.facade.UserFacade;
import com.crossover.media.resource.UserResource;

@Controller
public class SignUpController {

	@Resource
	private UserFacade userFacade;

	@RequestMapping(path = "/signup", method = RequestMethod.POST)
	public ResponseEntity<UserResource> signUp(@Valid @RequestBody UserResource user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(user);
		}
		UserResource createdUser = userFacade.createUser(user);
		return ResponseEntity.ok(createdUser);
	}

	@RequestMapping("/user")
	@ResponseBody
	public Principal user(Principal user) {
		return user;
	}
}
