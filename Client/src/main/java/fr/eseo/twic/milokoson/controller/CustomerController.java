package fr.eseo.twic.milokoson.controller;

import fr.eseo.twic.milokoson.dto.CustomerDto;
import fr.eseo.twic.milokoson.dto.OrderDto;
import fr.eseo.twic.milokoson.services.CustomerService;
import fr.eseo.twic.milokoson.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/v{apiversion}")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    OrderService orderService;

    @GetMapping("/search")
    public ModelAndView searchCustomer() {
        ModelAndView modelAndView = new ModelAndView("search");
        return modelAndView;
    }

    @GetMapping("/customers/{accountNo}")
    public ModelAndView displayCustomerDetails(@PathVariable("accountNo") String accountNo) {
        CustomerDto customerViewModel = customerService.getCustomerByAccountNo(accountNo);
        List<OrderDto> orderViewModel = orderService.getOrdersByAccountNo(accountNo);
        ModelAndView modelAndView = new ModelAndView("customerPage");
        modelAndView.addObject("id", customerViewModel.getId());
        modelAndView.addObject("accountNo", customerViewModel.getAccountNo());
        modelAndView.addObject("firstName", customerViewModel.getFirstName());
        modelAndView.addObject("lastName", customerViewModel.getLastName());
        modelAndView.addObject("registrationTimestamp", customerViewModel.getRegistrationTimestamp());
        modelAndView.addObject("email", customerViewModel.getEmail());
        modelAndView.addObject("orderId", orderViewModel);
        modelAndView.addObject("deliveredTimestamp", orderViewModel);
        modelAndView.addObject("orderStatusId", orderViewModel);
        return modelAndView;
    }

    @GetMapping("/customers/{accountNo}/newOrder")
        public ModelAndView displayNewOrder(@PathVariable("accountNo") String accountNo) {
        CustomerDto customerViewModel = customerService.getCustomerByAccountNo(accountNo);
        List<OrderDto> orderViewModel = orderService.getOrdersByAccountNo(accountNo);
        ModelAndView modelAndView = new ModelAndView("newOrder");
        modelAndView.addObject("id", customerViewModel.getId());
        modelAndView.addObject("accountNo", customerViewModel.getAccountNo());
        modelAndView.addObject("firstName", customerViewModel.getFirstName());
        modelAndView.addObject("lastName", customerViewModel.getLastName());
        modelAndView.addObject("registrationTimestamp", customerViewModel.getRegistrationTimestamp());
        modelAndView.addObject("email", customerViewModel.getEmail());
        modelAndView.addObject("orderId", orderViewModel);
        modelAndView.addObject("deliveredTimestamp", orderViewModel);
        modelAndView.addObject("orderStatusId", orderViewModel);
        return modelAndView;
    }

    @GetMapping("/customers/{accountNo}/{orderId}/existingOrder")
    public ModelAndView displayExistingOrderByOrderId(@PathVariable("accountNo") String accountNo, @PathVariable("orderId") String orderId) {
        ModelAndView modelAndView = new ModelAndView("existingOrder");
        return modelAndView;
    }

}
