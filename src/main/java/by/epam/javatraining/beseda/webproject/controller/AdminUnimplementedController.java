package by.epam.javatraining.beseda.webproject.controller;

import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.CAR_LIST_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.CLIENT_LIST_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.DRIVER_LIST_PAGE;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ResponseBody
public class AdminUnimplementedController {

	
	@PostMapping(value = { "/admin/car_list" })
	public ModelAndView getCarList() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(CAR_LIST_PAGE);
		return mav;
	}
	
	
	@PostMapping(value = { "/admin/drivers" })
	public ModelAndView getDrivers() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(DRIVER_LIST_PAGE);
		return mav;
	}
	
	
	@PostMapping(value = { "/admin/customers" })
	public ModelAndView getCustomers() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(CLIENT_LIST_PAGE);
		return mav;
	}
}
