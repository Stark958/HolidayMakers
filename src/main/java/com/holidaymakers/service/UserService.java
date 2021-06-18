package com.holidaymakers.service;

import java.util.List;

import com.holidaymakers.dao.UserDao;
import com.holidaymakers.model.BookingDetails;
import com.holidaymakers.model.BookingResponse;
import com.holidaymakers.model.Bookings;
import com.holidaymakers.model.Feedback;
import com.holidaymakers.model.Image;
import com.holidaymakers.model.Issue;
import com.holidaymakers.model.TourResponse;
import com.holidaymakers.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public UserDao userDao;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    public User login(User user){     
        logger.info("Inside login() method of Service");
        return userDao.login(user); 
    }

    public Boolean register( User user){
        logger.info("Inside register() method of Service");
        return userDao.register(user);
    }

    public Boolean updateUser(Long id,  User user){
        logger.info("Inside updateUser() method of Service");
        return userDao.updateUser(id,user);
    }

     ///////////////// VIEW ALL TOURS ////////////////////
    public List<TourResponse> viewAllTours( ) {   
        logger.info("Inside viewAllTours() method of Service");
        return userDao.viewAllTours();
    }  

     /////////////// RETRIEVE TOUR PACKAGE BY TOUR ID  //////////////////
    public TourResponse viewTour( Long tourid ) {   
        logger.info("Inside viewTour() method of Service");
        return userDao.viewTour(tourid);
    }    


      /////////////// BOOKING A PACKAGE //////////////////
    public Bookings addBooking( Long id,Long tour_id,Bookings bookings){
        logger.info("Inside addBooking() method of Service");
        return userDao.addBookings(id,tour_id,bookings);
    }

    /////////////// ADD BOOKING DETAILS //////////////////
    public Boolean addBookingDetails( Long bookingid, BookingDetails[] bookingDetails){
        logger.info("Inside addBookingDetails() method of Service");
        return userDao.addBookingDetails(bookingid,bookingDetails);
    }    

    /////////////// GET ALL BOOKINGS BY USER ID //////////////////
    public List<BookingResponse> getAllBookingByUserId( Long id){
        logger.info("Inside getAllBookingByUserId() method of Service");
        return userDao.getAllBookingByUserId(id);
    }    

    /////////////// UPDATE BOOKING //////////////////
    public Boolean updateBookings( Long booking_id , Bookings bookings){
        logger.info("Inside updateBookings() method of Service");
        return userDao.updateBookings(booking_id,bookings);
    }

    /////////////// RAISE ISSUE //////////////////
    public Boolean addIssue( Long user_id, Issue issue){
        logger.info("Inside addIssue() method of Service");
        return userDao.addIssue(user_id,issue);
    }   


    /////////////// ADD FEEDBACK //////////////////'
    public Boolean addFeedback( Long id,  Feedback feedback){
        logger.info("Inside addFeedback() method of Service");
       return userDao.addFeedback(id,feedback);
    }

    //////// GET BOOKING DETAILS BY BOOKING ID ////////////
    public List<BookingDetails> getDetailsByBookingId( Long booking_id ){
        logger.info("Inside getDetailsByBookingId() method of Service");
        return userDao.getDetailsByBookingId(booking_id); 
    }

    public Image getFile( Long image_id) {
       logger.info("Inside getFile() method of Service");
       return userDao.getFile(image_id);
    }
}
