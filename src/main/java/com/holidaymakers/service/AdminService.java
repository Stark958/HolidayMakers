package com.holidaymakers.service;

import java.io.IOException;
import java.util.List;

import com.holidaymakers.dao.AdminDao;
import com.holidaymakers.model.BookingDetails;
import com.holidaymakers.model.BookingResponse;
import com.holidaymakers.model.Bookings;
import com.holidaymakers.model.DashboardResponse;
import com.holidaymakers.model.Image;
import com.holidaymakers.model.ImageDownResponse;
import com.holidaymakers.model.ImageUpResponse;
import com.holidaymakers.model.Issue;
import com.holidaymakers.model.IssueResponse;
import com.holidaymakers.model.Tour;
import com.holidaymakers.model.TourResponse;
import com.holidaymakers.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

@Service
public class AdminService {

    @Autowired
    public AdminDao adminDao;

    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    public List<TourResponse> viewAllTours( ) {   
        logger.info("Inside viewAllTours() method of Service");
        return adminDao.viewAllTours();
    }  
    

    public Tour addTour(Tour tour)throws DataAccessException, IOException {   
        logger.info("Inside addTour() method of Service");
        return adminDao.addTour(tour);
    } 

    public Boolean updateTour(Long id ,Tour tour ) {   
        logger.info("Inside updateTour() method of Service");
        return  adminDao.updateTour(id,tour);
    }  
    

    public Tour viewTour(Long tourid ) {   
        logger.info("Inside viewTour() method of Service");
        return adminDao.viewTour(tourid);
    }  


    public List<ImageDownResponse> listImages( Long tour_id) {
        logger.info("Inside listImages() method of Service");
       return adminDao.getAllFiles(tour_id);
    }


    public Boolean deleteTour( Long id ) {  
        logger.info("Inside deleteTour() method of Service");
        return adminDao.deleteTour(id);
    }  

    public List<ImageUpResponse> addTourImages( Long tourid, MultipartFile[] file) throws DataAccessException, IOException {   
        logger.info("Inside addTourImages() method of Service");
        return adminDao.addTourImages(tourid,file);
    } 

    public Boolean deleteImagesById(Long id ) {  
        logger.info("Inside deleteImagesById() method of Service");
        return adminDao.deleteImagesById(id);
    }

    public List<BookingResponse> getAllBooking(){
        logger.info("Inside getAllBooking() method of Service");
        List<BookingResponse> bookings = adminDao.getAllBooking();
        return bookings;
    }  

    public Boolean updateBookings(Long booking_id , Bookings bookings){
        logger.info("Inside updateBookings() method of Service");
        return adminDao.updateBookings(booking_id,bookings);
    }

    public List<BookingDetails> getDetailsByBookingId(Long booking_id ){
        logger.info("Inside getDetailsByBookingId() method of Service");
        List<BookingDetails> users = adminDao.getDetailsByBookingId(booking_id);
        return users;
    }

    public List<User> listUsers(){
        logger.info("Inside listUsers() method of Service");
        List<User> users = adminDao.listUsers();
        return users;
    }

    public Boolean deleteUser(Long id){
        logger.info("Inside deleteUser() method of Service");
        return  adminDao.DeleteUser(id);
    }


    public List<IssueResponse> listIssue( ) {   
        logger.info("Inside listIssue() method of Service");
        return adminDao.listIssue();
    }  


    public Boolean replyIssue( Long issue_id, Issue issue){
        logger.info("Inside replyIssue() method of Service");
        return adminDao.replyIssue(issue_id,issue);
    }  


    public Boolean deleteIssue(Long issue_id){
        logger.info("Inside deleteIssue() method of Service");
        return adminDao.deleteIssue(issue_id);
    }  

    public Boolean updatePassword( Long id, User user){
        logger.info("Inside updatePassword() method of Service");
        return adminDao.updatePassword(id,user);
    }

    public  Image getFile( Long tour_id) {
        logger.info("Inside getFile() method of Service");
        return adminDao.getFile(tour_id);
    }

    public Issue getIssuebyId(Long id) {
		logger.info("Inside getIssuebyId() method of Service");
		return adminDao.getIssuebyId(id);
	}


    public DashboardResponse getInfo() {
        logger.info("Inside getInfo() method of Service");
        return adminDao.getInfo();
    }
    
}
