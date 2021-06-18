package com.holidaymakers.controllers;

import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.holidaymakers.model.BookingDetails;
import com.holidaymakers.model.BookingResponse;
import com.holidaymakers.model.Bookings;
import com.holidaymakers.model.DashboardResponse;
import com.holidaymakers.model.Image;
import com.holidaymakers.model.ImageUpResponse;
import com.holidaymakers.model.Issue;
import com.holidaymakers.model.IssueResponse;
import com.holidaymakers.model.Tour;
import com.holidaymakers.model.TourResponse;
import com.holidaymakers.model.User;
import com.holidaymakers.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
//@CrossOrigin(origins="http://localhost:3000")
@CrossOrigin(origins="https://holiday-makers-ef667.web.app/")
@RequestMapping(path="/api/v1/admin")
public class AdminController {

    @Autowired
    public AdminService adminService;

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

     //////// DASHBOARD INFO T/////////
    @GetMapping(path = "/Dashboard")
    public ResponseEntity<?> getInfo( ) {   
        logger.info("Inside getInfo() method of Controller");       
         DashboardResponse tourList = adminService.getInfo();  
        if( tourList == null ){
            return new ResponseEntity<String>("No Tour found", HttpStatus.OK);
        }
        return new ResponseEntity<>(tourList, HttpStatus.OK);
    }  


     //////// VIEW LIST OF TOUR PACKAGES T/////////
    @GetMapping(path = "/Tours")
    public ResponseEntity<?> viewAllTours( ) {   
        logger.info("Inside viewAllTours() method of Controller");       
         List<TourResponse> tourList = adminService.viewAllTours();  
        if( tourList.size() == 0 ){
            return new ResponseEntity<String>("No Tour found", HttpStatus.OK);
        }
        return new ResponseEntity<>(tourList, HttpStatus.OK);
    }  
    
    //////// ADD TOUR T/////////
    @PostMapping(path = "/Tour")
    public ResponseEntity<?> addTour(@RequestBody Tour tour ) throws DataAccessException, IOException {   
        logger.info("Inside addTour() method of Controller");
        Tour tour1 = adminService.addTour(tour);       
        if( tour1 == null){
            return new ResponseEntity<String>("Something went wong try again later !!!", HttpStatus.OK);
        }
        return new ResponseEntity<>(tour1, HttpStatus.OK);
    } 

    //////// UPDATE TOUR T/////////
    @PutMapping(path = "/Tour/{tour_id}")
    public ResponseEntity<?> updateTour(@PathVariable("tour_id") Long id , @RequestBody Tour tour ) {   
        logger.info("Inside updateTour() method of Controller");
        if(adminService.updateTour(id,tour)){
           return new ResponseEntity<String>("Updated", HttpStatus.OK);
        }     
         return new ResponseEntity<String>("Failed", HttpStatus.OK);
    }  
    
     //////// GET TOUR BY TOUR ID T /////////
    @GetMapping(path = "/Tour/{tour_id}")
    public ResponseEntity<?> viewTour(@PathVariable("tour_id") Long tourid ) {   
        logger.info("Inside viewTour() method of Controller");       
        if(adminService.viewTour(tourid) == null ){
            return new ResponseEntity<String>("No Tour Found", HttpStatus.OK);
        }
        return new ResponseEntity<>(adminService.viewTour(tourid), HttpStatus.OK);
    }  

     //////// GET IMAGES BY TOUR ID T /////////
    @GetMapping("/Images/{tour_id}")
    public ResponseEntity<?> getImagesByTourId(@PathVariable("tour_id") Long tour_id) {
        logger.info("Inside getImagesByTourId() method of Controller");       
        if(adminService.listImages(tour_id).size() == 0){
            return new ResponseEntity<String>("Not Found", HttpStatus.OK);
        }
       return new ResponseEntity<>(adminService.listImages(tour_id), HttpStatus.OK);
    }

    //////// DELETE TOUR N /////////
    @DeleteMapping(path = "/Tour/{tour_id}")
    public ResponseEntity<?> deleteTour(@PathVariable("tour_id") Long id ) {  
        logger.info("Inside deleteTour() method of Controller");         
         if(adminService.deleteTour(id)){
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
         }    
        return new ResponseEntity<String>("Failed", HttpStatus.OK);
    }  

    //////// ADD OR UPDATE TOUR IMAGES BY TOUR ID /////////
    @PostMapping(path = "/Images/{tour_id}")
    public ResponseEntity<?> addTourImages(@PathVariable("tour_id") Long tourid,@RequestParam("file") MultipartFile[] file) throws DataAccessException, IOException { 
        logger.info("Inside addTourImages() method of Controller");        
         
        List<ImageUpResponse> responseList = adminService.addTourImages(tourid,file);

        if(responseList.size() == 0){ 
           return new ResponseEntity<String>("Failed", HttpStatus.OK);
        }
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    } 

    //////// DELETE INDIVIDUAL TOUR IMAGES BY IMAGE ID /////////
    @DeleteMapping(path = "/Image/{image_id}")
    public ResponseEntity<?> deleteImagesById(@PathVariable("image_id") Long id ) {  
        logger.info("Inside deleteImagesById() method of Controller");        
        if(adminService.deleteImagesById(id)){
            return new ResponseEntity<>("Deleted", HttpStatus.OK);           
        }
        return new ResponseEntity<String>("Failed", HttpStatus.OK);
    }

    //////// GET ALL BOOKINGS ////////////
    @GetMapping(path = "/Bookings")
    public ResponseEntity<?> getAllBooking(){
        logger.info("Inside getAllBooking() method of Controller");
        List<BookingResponse> bookingResponse = adminService.getAllBooking();      
        if(bookingResponse.size() == 0){
            return new ResponseEntity<String>("Not found", HttpStatus.OK);
        }
        return new ResponseEntity<>(bookingResponse, HttpStatus.OK);
    }  

    //////// UPDATE BOOKING ////////////
    @PutMapping(path = "/Bookings/{booking_id}")
    public ResponseEntity<?> updateBookings(@PathVariable("booking_id") Long booking_id ,@RequestBody Bookings bookings){
        logger.info("Inside updateBookings() method of Controller");
        if(adminService.updateBookings(booking_id, bookings)){
            return new ResponseEntity<>("Updated", HttpStatus.OK);   
        }
        return new ResponseEntity<String>("Failed", HttpStatus.OK);
    }

    //////// GET BOOKING DETAILS BY BOOKING ID ////////////
   @GetMapping("/Details/{booking_id}")
    public ResponseEntity<?> getDetailsByBookingId(@PathVariable("booking_id") Long booking_id ){
        logger.info("Inside getDetailsByBookingId() method of Controller");
        List<BookingDetails> detailsList = adminService.getDetailsByBookingId(booking_id);        
        if(detailsList.size() == 0){
            return new ResponseEntity<String>("Not found", HttpStatus.OK);
        }
         return new ResponseEntity<>(detailsList, HttpStatus.OK);
    }

    //////// GET LIST OF ALL USERS ////////////
    @GetMapping("/Users")
    public ResponseEntity<?> getUsers(){
        logger.info("Inside getUsers() method of Controller");
        List<User> users = adminService.listUsers();       
        if(users.size() == 0){
            return new ResponseEntity<String>("Not found", HttpStatus.OK);
        }
         return new ResponseEntity<>(users, HttpStatus.OK);
    }

     //////// DELETE USER BY ID ////////////
     @DeleteMapping("/User/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        logger.info("Inside deleteUser() method of Controller");        
        if(adminService.deleteUser(id)){
            return new ResponseEntity<>("Deleted", HttpStatus.OK);   
        }
        return new ResponseEntity<String>("Failed", HttpStatus.OK);
    }


    //////// GET LIST OF ALL ISSUES ////////////
    @GetMapping(path = "/Issues")
    public ResponseEntity<?> listIssue( ) {   
        logger.info("Inside listIssue() method of Controller");     
        List<IssueResponse> issueList = adminService.listIssue();
        if(issueList.size() == 0){
             return new ResponseEntity<String>("Not found", HttpStatus.OK);
        }
        return new ResponseEntity<>(issueList, HttpStatus.OK);
    }  

    //////// VIEW ISSUE BY ID ////////////
    @GetMapping("/Issue/{id}")
	public ResponseEntity<?> getIssueById(@PathVariable("id") Long id){
		logger.info("Inside getIssueById() method of Controller");	
		Issue issue= adminService.getIssuebyId(id);
		if(issue == null) {
			logger.warn("Cannot find issue with id: "+id);
			return new ResponseEntity<String>("No Issue found with this id: "+id, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Issue>(issue, HttpStatus.OK);
		}
	}


    //////// REPLY ISSUE BY ID ////////////
    @PutMapping(path = "/Issue/{issue_id}")
    public ResponseEntity<?> replyIssue(@PathVariable("issue_id") Long issue_id,@RequestBody Issue issue){
        logger.info("Inside replyIssue() method of Controller");       
        if(adminService.replyIssue(issue_id,issue)){
            return new ResponseEntity<String>("Updated", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Failed", HttpStatus.OK);
    }  


    //////// DELETE ISSUE BY ID //////////
   @DeleteMapping(path = "/Issue/{issue_id}")
    public ResponseEntity<?> deleteIssue(@PathVariable("issue_id") Long issue_id){
        logger.info("Inside deleteIssue() method of Controller");        
         if(adminService.deleteIssue(issue_id)){
            return new ResponseEntity<String>("Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Failed", HttpStatus.OK);
    }  

     /////////////// UPDATE PASSWORD //////////////////'
    @PutMapping("/updatepassword/{id}")
    public ResponseEntity<?> updatePassword(@PathVariable Long id, @RequestBody User user){
        logger.info("Inside updatePassword() method of Controller");      
         if(adminService.updatePassword(id,user)){
            return new ResponseEntity<String>("Updated", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Failed", HttpStatus.OK);
    }


    @GetMapping("/getAllImages/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable("id") Long id) {
        logger.info("Inside getFile() method of Controller");
        Image fileEntity = adminService.getFile(id);
        if (fileEntity == null) {
            return ResponseEntity.notFound()
                                 .build();
        }
        return ResponseEntity.ok()
                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
                             .contentType(MediaType.valueOf(fileEntity.getContent_type()))
                             .body(fileEntity.getData());
    }

    ///Content-Disposition Indicates if the resource transmitted should be displayed inline (default behavior without the header), 
         //or if it should be handled like a download and the browser should present a “Save As” dialog
    

    ///// Reference : https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Content-Disposition
    
}
