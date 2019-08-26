/*

 */

package com.spring.cc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.cc.logic.ConvertLogic;

/**
 *
 * @author Tamil
 */
@Controller
public class HomeController {

	public static final String URL_HOME = "/";
	public static final String URL_CONVERT = "/convert";

	@Autowired
    ConvertLogic convertLogic;

	@RequestMapping(value = URL_HOME, method = RequestMethod.GET)
	public String show(Model model) {

		return "home";
	}

	/**
	 * get notification details by Active Id and search message </br>
	 * AJAX request
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = URL_CONVERT, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String convertAmount(@RequestParam String convertFrom, @RequestParam String convertTo,
			@RequestParam Long amount, HttpSession session, Model model) throws Exception {
		return convertLogic.getConvertionAmount(convertFrom, convertTo, amount);
	}
}
