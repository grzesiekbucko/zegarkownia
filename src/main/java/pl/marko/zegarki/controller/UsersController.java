package pl.marko.zegarki.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.marko.zegarki.entity.User;
import pl.marko.zegarki.repository.UserRepository;
import pl.marko.zegarki.services.RoleService;
import pl.marko.zegarki.services.UserService;

import java.io.IOException;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = {"/admin/users"}, method = RequestMethod.GET)
    public ModelAndView users() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        ModelAndView model = new ModelAndView();
        model.setViewName("admin/users");
        model.addObject("userName", user.getName());
        model.addObject("users", userService.getUsersAndRoles());
        model.addObject("dropdownRole", roleService.getAllRole());

        return model;
    }

    @RequestMapping(value = {"/admin/update"}, method = RequestMethod.POST)
    public String updateUsers(@RequestParam int id, int active, String option_role) throws IOException {
        userService.updateUserActive(active, id);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = {"/admin/update"}, method = RequestMethod.POST, params = "action=save")
    public String updateRole(@RequestParam int id, String option_role) throws IOException {
        userService.updateUserRole(id, option_role);
        return "redirect:/admin/users";
    }


}
