package cooksys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Validated
@RequestMapping("validate")
public class ValidateController {
	
	//private 
	
	ValidateController()
	{
		
		
	}
	
	@GetMapping("tag/exists/{label}")
	@ResponseBody
	public Boolean tagExists(@PathVariable String label)
	{
		return true;
	}
	
	@GetMapping("username/exists/@{username}")
	@ResponseBody
	public Boolean usernameExists(@PathVariable String username)
	{
		return true;
	}
	
	@GetMapping("username/available/@{username}")
	@ResponseBody
	public Boolean usernameAvailable(@PathVariable String username)
	{
		return true;
	}
}
