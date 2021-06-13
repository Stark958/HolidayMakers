package com.holidaymakers.controllers;

import java.util.List;

import com.holidaymakers.model.BookingDetails;
import com.holidaymakers.model.BookingResponse;
import com.holidaymakers.model.Bookings;
import com.holidaymakers.model.Feedback;
import com.holidaymakers.model.Image;
import com.holidaymakers.model.Issue;
import com.holidaymakers.model.User;
import com.holidaymakers.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping(path="/api/v1/user")
public class UserController {

    @Autowired
    public UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

     /////////////////// LOGIN ////////////////////////////// 
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        logger.info("Inside login() method of Controller");
        User user1 = userService.login(user); 
        if(user1 == null){
            return new ResponseEntity<String>("Not found", HttpStatus.OK);
        }
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }

      /////////////////// REGISTER //////////////////////////////
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        logger.info("Inside registerUser() method of Controller");
        
        if(userService.register(user)){
            return new ResponseEntity<String>("Registered", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Failed", HttpStatus.EXPECTATION_FAILED);
    }


    /////////////// UPDATE PASSWORD //////////////////'
    @PutMapping("/updatepassword/{user_id}")
    public ResponseEntity<?> updateUser(@PathVariable("user_id") Long id, @RequestParam String password){
        logger.info("Inside updateUser() method of Controller");
        if(userService.updateUser(id,password)){
            return new ResponseEntity<String>("Updated", HttpStatus.OK);
        }

        return new ResponseEntity<String>("Failed", HttpStatus.EXPECTATION_FAILED);
    }

     ///////////////// VIEW ALL TOURS ////////////////////
    @GetMapping(path = "/Tours")
    public ResponseEntity<?> viewAllTours( ) {   
        logger.info("Inside viewAllTours() method of Controller");
        
        if(userService.viewAllTours().size() == 0){
            return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userService.viewAllTours(), HttpStatus.OK);
    }  

     /////////////// RETRIEVE TOUR PACKAGE BY TOUR ID  //////////////////
     @GetMapping(path = "/Tour/{tourid}")
    public ResponseEntity<?> viewTour(@PathVariable("tourid") Long tourid ) {   
        logger.info("Inside viewTour() method of Controller");
        
        if(userService.viewTour(tourid) == null){
            return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userService.viewTour(tourid), HttpStatus.OK);
    }    


      /////////////// BOOKING A PACKAGE //////////////////
    @PostMapping(path = "/Bookings/{userId}")
    public ResponseEntity<?> addBooking(@PathVariable("userId") Long id,@RequestParam Long tour_id,@RequestBody Bookings bookings){
        logger.info("Inside addBookings() method of Controller");
        Bookings bookings1 = userService.addBooking(id,tour_id,bookings);
        if(bookings1 == null){
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bookings1, HttpStatus.OK);
    }

    /////////////// ADD BOOKING DETAILS //////////////////
    @PostMapping(path = "/Details/{booking_id}")
    public ResponseEntity<?> addBookingDetails(@PathVariable("booking_id") Long bookingid,@RequestBody BookingDetails[] bookingDetails){
        logger.info("Inside addBookingDetails() method of Controller");
        if(userService.addBookingDetails(bookingid,bookingDetails)){
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        }

        return new ResponseEntity<>("Failed", HttpStatus.OK);
    }    

    /////////////// GET ALL BOOKINGS BY USER ID //////////////////
    @GetMapping(path = "/Bookings/{user_id}")
    public ResponseEntity<?> getAllBookingByUserId(@PathVariable("user_id") Long id){
        logger.info("Inside getAllBookingByUserId() method of Controller");
        List<BookingResponse> bookings = userService.getAllBookingByUserId(id);
        
        if(bookings.size() == 0){
            return new ResponseEntity<String>("Not Found", HttpStatus.OK);
        }

        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }    

    //////// GET BOOKING DETAILS BY BOOKING ID ////////////
   @GetMapping("/Details/{booking_id}")
    public ResponseEntity<?> getDetailsByBookingId(@PathVariable("booking_id") Long booking_id ){
        logger.info("Inside getDetailsByBookingId() method of Controller");
        List<BookingDetails> detailsList = userService.getDetailsByBookingId(booking_id);
        if(detailsList.size() == 0){
            return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(detailsList, HttpStatus.OK);
    }

    /////////////// UPDATE BOOKING //////////////////
    @PutMapping(path = "/Bookings/{booking_id}")
    public ResponseEntity<?> updateBookings(@PathVariable("booking_id") Long booking_id ,@RequestBody Bookings bookings){
        logger.info("Inside updateBookings() method of Controller");
        if(userService.updateBookings(booking_id,bookings)){
             return new ResponseEntity<>("Updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed", HttpStatus.NOT_FOUND);
    }

    

    /////////////// RAISE ISSUE //////////////////
    @PostMapping(path = "/Issue/{user_id}")
    public ResponseEntity<?> addIssue(@PathVariable("user_id") Long user_id,@RequestBody Issue issue){
        logger.info("Inside addIssue() method of Controller");
         
         if(userService.addIssue(user_id,issue)){
             return new ResponseEntity<>("Updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed", HttpStatus.NOT_FOUND);
    }   


    /////////////// ADD FEEDBACK //////////////////'
    @PostMapping("/Feedback/{user_id}")
    public ResponseEntity<?> addFeedback(@PathVariable("user_id") Long id, @RequestBody Feedback feedback){
        logger.info("Inside addFeedback() method of Controller");
        
         if(userService.addFeedback(id,feedback)){
             return new ResponseEntity<>("Updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed", HttpStatus.EXPECTATION_FAILED);

    }
    

    @GetMapping("/getAllImages/{image_id}")
    public ResponseEntity<byte[]> getFile(@PathVariable("image_id") Long image_id) {
        logger.info("Inside getFile() method of Controller");
        Image fileEntity = userService.getFile(image_id);
        if (fileEntity == null) {
            return ResponseEntity.notFound()
                                 .build();
        }
        return ResponseEntity.ok()
                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
                             .contentType(MediaType.valueOf(fileEntity.getContent_type()))
                             .body(fileEntity.getData());
    }

}
    

