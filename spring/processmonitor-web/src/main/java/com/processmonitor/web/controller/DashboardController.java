package com.processmonitor.web.controller;

import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("**/dashboard")
public class DashboardController {

	@RequestMapping(method = RequestMethod.GET)
	public String show(ModelMap modelMap) {

		System.err.println(">>>>>>>>>>>>>" + getClass().getSimpleName() + ".show()");

		return "dashboard";
	}

	/*
	 * @RequestMapping(value = "/{mode_id}", method = RequestMethod.GET) public String
	 * showForMode(@PathVariable("mode_id") Long modeId, ModelMap modelMap) { return "showDashboard"; }
	 */
}
