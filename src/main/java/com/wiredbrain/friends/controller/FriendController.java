package com.wiredbrain.friends.controller;

import com.wiredbrain.friends.model.Friend;
import com.wiredbrain.friends.service.FriendService;
import com.wiredbrain.friends.util.ErrorMessage;
import com.wiredbrain.friends.util.FieldErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class FriendController {

    @Autowired
    FriendService friendService;

    @PostMapping("/friend")
    Friend create(@Valid @RequestBody Friend friend) {
        return friendService.save(friend);
    }

    @GetMapping("/friend")
    Iterable<Friend> read(){
        return friendService.findAll();
    }

    @GetMapping("/friend/{id}")
    Friend readOne(@Valid @PathVariable Integer id){
        if(friendService.findById(id).isPresent())
            return friendService.findById(id).get();
        else
            throw new NoSuchElementException();
    }

    @GetMapping("/friend/search")
    Iterable<Friend> findByQuery(@RequestParam(value = "first", required = false) String firstName,
                                 @RequestParam(value = "last", required = false) String lastName) {
        if(firstName != null && lastName != null)
            return friendService.findByFirstNameAndLastName(firstName, lastName);
        else if(firstName != null)
            return friendService.findByFirstName(firstName);
        else if(lastName != null)
            return friendService.findByLastName(lastName);
        else
            return friendService.findAll();
    }

    @PutMapping("/friend")
    ResponseEntity<Friend> update(@RequestBody Friend friend){
        if(friendService.findById(friend.getId()).isPresent())
            return new ResponseEntity(friendService.save(friend), HttpStatus.OK);
        else
            return new ResponseEntity(friend, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/friend/{id}")
    void delete(@PathVariable Integer id){
        friendService.deleteById(id);
    }

    /**
     * For test validation
     * @return
     * @throws ValidationException
     */
    @GetMapping("/wrong")
    Friend somethingIsWrong() throws ValidationException {
        throw new ValidationException("Something is wrong");
    }

    /**
     * For test validation
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    String exceptionHandler(ValidationException e){
        return e.getMessage();
    }
}
