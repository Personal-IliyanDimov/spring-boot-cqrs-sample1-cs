package org.imd.cqrs.sample1.cs.jaxrs.model.mapper;

import org.imd.cqrs.sample1.cs.jaxrs.model.dto.UserAddressDto;
import org.imd.cqrs.sample1.cs.jaxrs.model.dto.UserContactDto;
import org.imd.cqrs.sample1.cs.jaxrs.model.dto.UserDto;
import org.imd.cqrs.sample1.cs.model.Address;
import org.imd.cqrs.sample1.cs.model.Contact;
import org.imd.cqrs.sample1.cs.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public User toUser(UserDto userDto);
    UserDto toUserDto(User createdUser);

    Address toUserAddress(UserAddressDto userAddressDto);
    UserAddressDto toUserAddressDto(Address storedAddress);

    Contact toUserContact(UserContactDto userContactDto);
    UserContactDto toUserContactDto(Contact storedAddress);
}
