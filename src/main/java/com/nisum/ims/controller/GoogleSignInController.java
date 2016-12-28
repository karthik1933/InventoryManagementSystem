package com.nisum.ims.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class GoogleSignInController {

	@GetMapping()
	public String welcomePage() {
		return "redirect:index.html";
	}
	@GetMapping
	@RequestMapping("/OAuth2Callback")
	public String callback(@RequestParam String code) {
		if (StringUtils.isNotBlank(code)) {
			return "redirect:product.html";
		}
		return "redirecting code value is not authenticated";
	}
}