package com.socialmedia.socialapp.Exceptions.UsersExceptions;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists(String message) {
        super(message);
    }
}
