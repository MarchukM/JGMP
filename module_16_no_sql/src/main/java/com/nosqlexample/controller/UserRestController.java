package com.nosqlexample.controller;

import com.nosqlexample.model.Sport;
import com.nosqlexample.model.User;
import com.nosqlexample.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable String id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public User findByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @RequestMapping(value = "/sport/{sportName}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public User findBySportName(@PathVariable String sportName) {
        return userService.findBySportName(sportName);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public User findById(@RequestBody User user) {
        return userService.create(user);
    }

    @RequestMapping(value = "{id}/sport", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public User updateUserWithSport(@PathVariable String id, @RequestBody Sport sport) {
        return userService.updateWithSport(id, sport);
    }
}
