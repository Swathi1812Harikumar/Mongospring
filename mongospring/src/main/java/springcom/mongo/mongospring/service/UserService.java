package springcom.mongo.mongospring.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import springcom.mongo.mongospring.aop.InvalidInputException;
import springcom.mongo.mongospring.dto.UserPatch;
import springcom.mongo.mongospring.model.User;
import springcom.mongo.mongospring.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;




@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public Page<User> getAllUsers(Optional<Integer> Page, Optional<Integer> limit) {
        return userRepository.findAll(PageRequest.of(Page.orElse(1), limit.orElse(5)));
    }


    public Optional<User> getUser(String id) {
        return userRepository.findById("id");
    }

    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }


    public User updateUser(User user, String id) {
        return userRepository.save(user);
    }

    public Optional<User> deleteUserById(String id) {
        Optional<User> userUserOptional = userRepository.findById(id);
        userRepository.deleteById(id);
        return userUserOptional;
    }

    public void patchAction(String action, UserPatch patch, User update) {
        if (action.equals("replace") || action.equals("add")) {
            if (patch.getFieldName().equals("username")) {
                update.setName(patch.getValue());
            } else if (patch.getFieldName().equals("email")) {
                update.setEmail(patch.getValue());
            } else if (action.equals("delete")) {
                if (patch.getFieldName().equals("username")) {
                    update.setName(" ");
                } else if (patch.getFieldName().equals("email")) {
                    update.setEmail(" ");
                }
            }
            userRepository.save(update);
        }
    }


    public void updateUserByPatch (ArrayList< UserPatch > userPatch, String id)throws Exception {
        if (userPatch.isEmpty()) {
            throw new InvalidInputException("Input not Sufficient.");
        }
        Optional<User> userUpdate = userRepository.findById(id);
        if (userUpdate.isEmpty()) {
            throw new InvalidInputException("User not Found.");
        }
        for (UserPatch user : userPatch) {
            if (user.getAction().equals("replace") || user.getAction().equals("add") || user.getAction().equals("delete")) {
                patchAction(user.getAction(), user, userUpdate.get());
            } else {
                throw new InvalidInputException("Input is invalid");
            }
        }
    }
}

