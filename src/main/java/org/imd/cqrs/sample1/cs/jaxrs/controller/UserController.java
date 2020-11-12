package org.imd.cqrs.sample1.cs.jaxrs.controller;

import lombok.RequiredArgsConstructor;
import org.imd.cqrs.sample1.cs.jaxrs.exception.UserAlreadyExistsException;
import org.imd.cqrs.sample1.cs.jaxrs.exception.UserNotFoundException;
import org.imd.cqrs.sample1.cs.jaxrs.model.dto.UserAddressDto;

import org.imd.cqrs.sample1.cs.jaxrs.model.dto.UserDto;
import org.imd.cqrs.sample1.cs.jaxrs.model.mapper.UserMapper;
import org.imd.cqrs.sample1.cs.jaxrs.service.UserService;
import org.imd.cqrs.sample1.cs.model.Address;
import org.imd.cqrs.sample1.cs.model.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) throws UserAlreadyExistsException {
        final User user = userMapper.toUser(userDto);
        final User createdUser = userService.createUser(user);
        return ResponseEntity.ok(userMapper.toUserDto(createdUser));
    }

    @PostMapping(value = "/{userid}/address")
    ResponseEntity<UserAddressDto> addUserAddress(@PathVariable(name = "userid") @NotNull Long userId,
                                                  @RequestBody UserAddressDto userAddressDto) throws UserNotFoundException {
        final Address address = userMapper.toUserAddress(userAddressDto);
        final Address storedAddress = userService.addUserAddress(userId, address);
        return ResponseEntity.ok(userMapper.toUserAddressDto(storedAddress));
    }

    @PutMapping(value = "/{userid}/address/{addressid}")
    ResponseEntity<UserAddressDto> changeUserAddress(@PathVariable(name = "userid") @NotNull Long userId,
                                                     @PathVariable(name = "addressid") @NotNull Long addressId,
                                                     @RequestBody UserAddressDto userAddressDto) throws UserNotFoundException {
        final Address address = userMapper.toUserAddress(userAddressDto);
        final Address storedAddress = userService.changeUserAddress(userId, addressId,address);
        return ResponseEntity.ok(userMapper.toUserAddressDto(storedAddress));
    }
}
