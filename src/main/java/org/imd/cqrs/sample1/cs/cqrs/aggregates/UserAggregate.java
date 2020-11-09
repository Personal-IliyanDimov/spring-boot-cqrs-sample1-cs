package org.imd.cqrs.sample1.cs.cqrs.aggregates;

import org.imd.cqrs.sample1.cs.cqrs.commands.CreateUserCommand;
import org.imd.cqrs.sample1.cs.cqrs.commands.UpdateUserCommand;
import org.imd.cqrs.sample1.cs.cqrs.repository.UserWriteRepository;
import org.imd.cqrs.sample1.cs.model.User;

public class UserAggregate {

    private UserWriteRepository writeRepository;

    public UserAggregate(UserWriteRepository repository) {
        this.writeRepository = repository;
    }

    public User handleCreateUserCommand(CreateUserCommand command) {
        User user = new User(command.getUserId(), command.getFirstName(), command.getLastName());
        writeRepository.addUser(user.getUserid(), user);
        return user;
    }

    public User handleUpdateUserCommand(UpdateUserCommand command) {
        User user = writeRepository.getUser(command.getUserId());
        user.setAddresses(command.getAddresses());
        user.setContacts(command.getContacts());
        writeRepository.addUser(user.getUserid(), user);
        return user;
    }

}
