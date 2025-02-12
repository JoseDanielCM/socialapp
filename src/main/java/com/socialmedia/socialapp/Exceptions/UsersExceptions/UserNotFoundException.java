package com.socialmedia.socialapp.Exceptions.UsersExceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
