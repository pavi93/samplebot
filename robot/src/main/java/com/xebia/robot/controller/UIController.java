package com.xebia.robot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("bot")
public class UIController {

	@GetMapping("/home")
	public String home(){
		return "index";
	}
}
