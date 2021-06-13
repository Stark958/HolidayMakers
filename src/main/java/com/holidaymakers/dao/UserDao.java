package com.holidaymakers.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.holidaymakers.dao.rowmapper.BookingDetailsRowMapper;
import com.holidaymakers.dao.rowmapper.BookingsRowMapper;
import com.holidaymakers.dao.rowmapper.ImageRowMapper;
import com.holidaymakers.dao.rowmapper.TourRowMapper;
import com.holidaymakers.dao.rowmapper.UserRowMapper;
import com.holidaymakers.model.BookingDetails;
import com.holidaymakers.model.BookingResponse;
import com.holidaymakers.model.Bookings;
import com.holidaymakers.model.Feedback;
import com.holidaymakers.model.Image;
import com.holidaymakers.model.ImageDownResponse;
import com.holidaymakers.model.Issue;
import com.holidaymakers.model.Tour;
import com.holidaymakers.model.TourResponse;
import com.holidaymakers.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(AdminDao.class);


    /////////////////// LOGIN //////////////////////////////
     public User login(User user) {
         logger.info("Inside login() method in DAO");
          String sqlQuery = "select * from user where email = ? and password =?";
          UserRowMapper userRowMapper = new UserRowMapper();
           try {
            return (User)jdbcTemplate.queryForObject(sqlQuery, userRowMapper, user.getEmail(), user.getPassword());
        }
        catch(EmptyResultDataAccessException ex) {
            return null;
        }
     }


     /////////////////// REGISTER //////////////////////////////
     public Boolean register(User user){
         logger.info("Inside register() method in DAO");
         Date date = new Date();
         String sqlQuery = "INSERT Into user(email, name, password,phone,registered_date,role ) values(?,?,?,?,?,?);"; 
          if(jdbcTemplate.update(sqlQuery,user.getEmail(), user.getName(), user.getPassword(),user.getPhone(),date, user.getRole()) > 0){
              return true;
          }
        return false;
     }


    /////////////// UPDATE PASSWORD //////////////////'
    public Boolean updateUser(Long id, String password) {
        logger.info("Inside updateUser() method in DAO");
        String sqlQuery = "UPDATE user SET password= ?  WHERE id = ?;";
          if(jdbcTemplate.update(sqlQuery, password, id) > 0){
              return true;
          }
          return false;
    }

    ///////////////// VIEW ALL TOURS ////////////////////
    public List<TourResponse> viewAllTours() {
        logger.info("Inside viewAllTours() method in DAO");
        String sqlQuery = "SELECT * FROM tour;"; 
        List<TourResponse> tours1 = new ArrayList<TourResponse>();
          List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlQuery);
          for (Map<String, Object> rs : rows) 
          {
                TourResponse tour = new TourResponse();
                tour.setId((Long)rs.get("id"));
                tour.setName((String)rs.get("name"));
                tour.setDetails((String)rs.get("details"));
                tour.setPlaces((String)rs.get("places"));
                tour.setNo_of_days((Integer)rs.get("no_of_days"));
                tour.setPrice((Double)rs.get("price"));
                tour.setImage(getAllFiles((Long)rs.get("id")));

                tours1.add(tour);
           }
        return  tours1;
    }


    /////////////// RETRIEVE TOUR PACKAGE BY TOUR ID  //////////////////
    public TourResponse viewTour(Long tour_id) {
        logger.info("Inside viewTour() method in DAO");
        String sqlQuery = "SELECT * from tour where id = ?";
        TourRowMapper tourRowMapper = new TourRowMapper();


        Tour tour = (Tour)jdbcTemplate.queryForObject(sqlQuery, tourRowMapper, tour_id);

        if( tour == null ){
            return null;
        }
        
        TourResponse tourResponse = new TourResponse();

        tourResponse.setId(tour.getId());
        tourResponse.setName(tour.getName());
        tourResponse.setDetails(tour.getDetails());
        tourResponse.setPlaces(tour.getPlaces());
        tourResponse.setNo_of_days(tour.getNo_of_days());
        tourResponse.setPrice(tour.getPrice());
        tourResponse.setImage(getAllFiles(tour_id));
        
        return tourResponse;
    }  

     /////////////// BOOKING A PACKAGE //////////////////
    public Bookings addBookings(Long id, Long tour_id,Bookings bookings) {
        logger.info("Inside addBookings() method in DAO");
        Date today = new Date();

        String sqlQuery = "INSERT Into booking(start_date,booked_date,accomodation,food_type,no_of_persons,price,payment_mode,payment_info,status,tour_id,user_id) values(?,?,?,?,?,?,?,?,?,?,?);"; 
        String sqlQuery1 = "SELECT * FROM booking ORDER BY id DESC LIMIT 1"; 
        if(jdbcTemplate.update(sqlQuery,bookings.getStart_date(),today,bookings.getAccomodation(),bookings.getFood_type(),bookings.getNo_of_persons(),bookings.getPrice(),bookings.getPayment_mode(),bookings.getPayment_info(),"Pending for Confirmation",tour_id,id) > 0){
            BookingsRowMapper bookingsMapper = new BookingsRowMapper();
        Bookings bookings2 = (Bookings)jdbcTemplate.queryForObject(sqlQuery1,bookingsMapper);
        return bookings2; 
        }
        return null;
    }  

    /////////////// ADD BOOKING DETAILS //////////////////
    public Boolean addBookingDetails(Long booking_id, BookingDetails[] bookingDetails) {
        logger.info("Inside addBookingDetails() method in DAO");
        String sqlQuery = "INSERT Into booking_details(name, age, gender, id_proof_number,booking_id) values(?,?,?,?,?);"; 
        int c = 1;
        for(BookingDetails bookingDetails1 : bookingDetails){
            if(jdbcTemplate.update(sqlQuery,bookingDetails1.getName(), bookingDetails1.getAge(), 
            bookingDetails1.getGender(), bookingDetails1.getId_proof_number(),booking_id) > 0){
               c++;
            }
        }
        if((c-1) == bookingDetails.length){
               return true;
        }
        return false;
    }


    /////////////// GET ALL BOOKINGS BY ID //////////////////
    public List<BookingResponse> getAllBookingByUserId(Long user_id) {
        logger.info("Inside getAllBookingByUserId() method in DAO");
        String sqlQuery = "select booking.id,user.name,booking.start_date,tour.name as tour_name,booking.status FROM booking  INNER JOIN user ON booking.user_id = user.id INNER JOIN tour ON booking.tour_id =tour.id WHERE booking.user_id = "+user_id+" ORDER BY booking.id DESC ;";
         List<BookingResponse> bookings1 = new ArrayList<BookingResponse>();
          List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlQuery);
          for (Map<String, Object> rs : rows) 
          {
              BookingResponse bookings = new BookingResponse();
        bookings.setBooking_id((Long)rs.get("id"));
        bookings.setName((String)rs.get("name"));
        bookings.setTour_name((String)rs.get("tour_name"));
        bookings.setStatus((String)rs.get("status"));
        bookings.setStart_date((Date)rs.get("start_date"));
        bookings1.add(bookings);
           }
         return bookings1;
    }

     /////////////// UPDATE BOOKING //////////////////
    public Boolean updateBookings(Long booking_id, Bookings bookings) {
        logger.info("Inside updateBookings() method in DAO");
        String status = "Cancelled by User";
        String sqlQuery1 = "UPDATE booking SET  cancel_msg = ?,status=? where id= ?;"; 
        if(jdbcTemplate.update(sqlQuery1,bookings.getCancel_msg(),status,booking_id) > 0){
            return true;
        }
        return false;
    }
    

    /////////////// RAISE ISSUE //////////////////
    public Boolean addIssue(Long user_id, Issue issue) {
        logger.info("Inside addIssue() method in DAO");
        String sqlQuery = "INSERT Into issue(subject,message,user_id) values(?,?,?);"; 
          if(jdbcTemplate.update(sqlQuery,issue.getSubject(),issue.getMessage(),user_id) > 0){
              return true;
          }
        return false;
    } 


    /////////////// ADD FEEDBACK //////////////////'
    public Boolean addFeedback(Long id, Feedback feedback) {
        logger.info("Inside addFeedback() method in DAO");
        String sqlQuery = "INSERT Into feedback(message,user_id) values(?,?);"; 
        if(jdbcTemplate.update(sqlQuery,feedback.getMessage(),id) > 0){
            return true;
        }
        return false;
    }
    
     /////////////// RETRIEVE IMAGES FOR TOUR PACKAGE BY TOUR ID (Images) //////////////////
    public List<ImageDownResponse> getAllFiles(Long tour_id) {
        logger.info("Inside getAllFiles() method in DAO");
        String sqlQuery = "select * from images where tour_id="+tour_id;
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlQuery);
        List<ImageDownResponse> urlResponses = new ArrayList<ImageDownResponse>();
        String url = "http://localhost:8080/api/v1/user/getAllImages/";
        for (Map<String, Object> rs : rows) 
        {
            ImageDownResponse urlResponse = new ImageDownResponse((Long)rs.get("id"), (url+(Long)rs.get("id")));
            urlResponses.add(urlResponse);
        }
         return urlResponses;
            
    }

    //////////// GET DETAILS BY BOOKING ID ////////////
    public List<BookingDetails> getDetailsByBookingId(Long booking_id) {
        logger.info("Inside getDetailsByBookingId() method in DAO");
        String SQL = "select * from booking_details where booking_id = "+booking_id;
        return jdbcTemplate.query(SQL, new BookingDetailsRowMapper());
        
    }


    public Image getFile(Long id) {
        logger.info("Inside getFile() method in DAO");
        String sqlQuery = "select * from images where id=?";
        ImageRowMapper imageRowMapper = new ImageRowMapper();
        Image image = (Image)jdbcTemplate.queryForObject(sqlQuery, imageRowMapper, id);
        
        return image;
    }
    
}
