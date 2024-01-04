package com.example.connectionApi.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUpUser(@Validated @RequestBody  UserSignUpDTO userSignUpDTO) {
        User isSignedUp = userService.signUp(userSignUpDTO);
        if (isSignedUp != null) {
            return new ResponseEntity<>("User signed up successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to sign up user", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/signIn")
    public ResponseEntity<String> signInUser(@RequestBody UserSignInDTO userSignInDTO) {
        Authentication authentication = userService.authenticate(userSignInDTO);
        if (authentication != null) {
            return new ResponseEntity<>("User signed in successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to sign in user", HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateUserProfile(@PathVariable("id") Long userId, @RequestBody UserUpdateDTO userUpdateDTO) {
        System.out.println("received DTO : " + userUpdateDTO.toString());
        String existingUserType = userService.getTypeOfUser(userId);

        if (userUpdateDTO.getTypeOfUser() == null || userUpdateDTO.getTypeOfUser().isEmpty()) {
            userUpdateDTO.setTypeOfUser(existingUserType);
        }

        boolean isUserUpdated = userService.updateUserProfile(userId, userUpdateDTO);

        if (!userUpdateDTO.getTypeOfUser().equalsIgnoreCase("employee") &&
                !userUpdateDTO.getTypeOfUser().equalsIgnoreCase("employer")) {
            if (isUserUpdated) {
                return new ResponseEntity<>("User profile updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to update user profile", HttpStatus.BAD_REQUEST);
            }
        }

        String userType = userUpdateDTO.getTypeOfUser();

        if ("employee".equalsIgnoreCase(userType)) {
            boolean isUpdated = userService.updateEmployeeProfile(userId, userUpdateDTO);
            if (isUpdated) {
                return new ResponseEntity<>("Employee profile updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to update employee profile", HttpStatus.BAD_REQUEST);
            }
        } else if ("employer".equalsIgnoreCase(userType)) {
            boolean isUpdated = userService.updateEmployerProfile(userId, userUpdateDTO);
            if (isUpdated) {
                return new ResponseEntity<>("Employer profile updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to update employer profile", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Invalid user type or no type specified", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus>
    deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

@ResponseStatus(HttpStatus.BAD_REQUEST)
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
    BindingResult result = ex.getBindingResult();
    List<String> errorList = new ArrayList<>();
    for (FieldError fieldError : result.getFieldErrors()) {
        errorList.add(fieldError.getDefaultMessage());
    }
    return new ResponseEntity<>("Validation failed: " + errorList, HttpStatus.BAD_REQUEST);
}
}
