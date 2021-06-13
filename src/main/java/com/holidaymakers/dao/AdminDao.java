package com.holidaymakers.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.holidaymakers.dao.rowmapper.BookingDetailsRowMapper;
import com.holidaymakers.dao.rowmapper.ImageRowMapper;
import com.holidaymakers.dao.rowmapper.IssueRowMapper;
import com.holidaymakers.dao.rowmapper.TourRowMapper;
import com.holidaymakers.dao.rowmapper.UserRowMapper;
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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class AdminDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(AdminDao.class);
    

     /////////////// DISPLAY ALL USERS //////////////////'
     public List<User> listUsers() {
          logger.info("Inside listUsers() method in DAO");
          String SQL = "select * from user where role = 'user'";
          return jdbcTemplate.query(SQL, new UserRowMapper());
     }

     /////////////// DELETE USER BY ID //////////////////'
    public Boolean DeleteUser(Long id) {
          logger.info("Inside DeleteUser() method in DAO");
          String sqlQuery = "DELETE from user where id = ?";
          if(jdbcTemplate.update(sqlQuery, id) > 0){
              return true;
          }
          return false;
    }


    /////////////// UPDATE PASSWORD //////////////////'
    public Boolean updatePassword(Long id, String password) {
      logger.info("Inside updatePassword() method in DAO");
        String sqlQuery = "UPDATE user SET password= ?  WHERE id = ?;";
          if(jdbcTemplate.update(sqlQuery, password, id) > 0){
              return true;
          }
          return false;
    }

      /////////////// UPDATE BOOKING //////////////////
    public Boolean updateBookings(Long booking_id, Bookings bookings) {
        logger.info("Inside updateBookings() method in DAO");
        String sqlQuery1 = "UPDATE booking SET  cancel_msg = ?,status=? where id= ?;"; 
          if(jdbcTemplate.update(sqlQuery1,bookings.getCancel_msg(),bookings.getStatus(),booking_id) > 0){
              return true;
          }
        return false;
    }

    /////////////// GET ALL BOOKINGS BY ID //////////////////
    public List<BookingResponse> getAllBooking() {
        logger.info("Inside getAllBooking() method in DAO");
        String sqlQuery = "select booking.id,user.name,tour.name as tour_name,booking.status FROM booking INNER JOIN user ON booking.user_id =user.id INNER JOIN tour ON booking.tour_id=tour.id";
         List<BookingResponse> bookings1 = new ArrayList<BookingResponse>();
          List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlQuery);
          for (Map<String, Object> rs : rows) 
          {
              BookingResponse bookings = new BookingResponse();
        bookings.setBooking_id((Long)rs.get("id"));
        bookings.setName((String)rs.get("name"));
        bookings.setTour_name((String)rs.get("tour_name"));
        bookings.setStatus((String)rs.get("status"));

        bookings1.add(bookings);
           }
         return bookings1;
    }

    public List<Issue> getAllIsues() {
          logger.info("Inside getAllIsues() method in DAO");
          String SQL = "select * from issue ORDER BY id DESC";
          return jdbcTemplate.query(SQL, new IssueRowMapper());
     }

    


    public List<IssueResponse> listIssue() {
          logger.info("Inside listIssue() method in DAO");
          String SQL = "select issue.id, subject, user.name as user_name, issue.reply from issue INNER JOIN user ON issue.user_id = user.id ORDER BY issue.id DESC;";
          List<IssueResponse> issueResponseList = new ArrayList<IssueResponse>();
          List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL);
          for (Map<String, Object> row : rows) 
          {
               IssueResponse issueResponse = new IssueResponse();
               issueResponse.setId((Long)row.get("id"));
               issueResponse.setSubject((String)row.get("subject"));
               issueResponse.setUser_name((String)row.get("user_name"));
               issueResponse.setReply((String)row.get("reply"));
               issueResponseList.add(issueResponse);
           }
         return issueResponseList;
     }

    public Issue getIssuebyId(Long id) {
		      logger.info("Inside getIssueById() method of DAO");
		      String sqlquery = "select * from issue where id = "+id;
          try {
            return (Issue)jdbcTemplate.queryForObject(sqlquery, new IssueRowMapper());
        }
        catch(EmptyResultDataAccessException ex) {
            return null;
        }
			    
		}

     /////////////// REPLY TO ISSUE //////////////////
    public Boolean replyIssue(Long booking_id, Issue issue) {
        logger.info("Inside replyIssue() method in DAO");
        String sqlQuery = "UPDATE  issue  SET reply = ? WHERE id=?"; 
          if(jdbcTemplate.update(sqlQuery,issue.getReply(),booking_id) > 0){
              return true;
          }
        return false;
    } 

     public Boolean deleteIssue(Long issue_id) {
      logger.info("Inside deleteIssue() method in DAO");
        String sqlQuery = "DELETE from issue where id= ?";

           if(jdbcTemplate.update(sqlQuery, issue_id) > 0){
              return true ;
            }
          
          return false;
    }


    /////////////// ADD TOUR PACKAGE //////////////////
    public Tour addTour(Tour tour){

         logger.info("Inside addTour() method in DAO");

        String sqlQuery1 = "INSERT Into tour(name, details, places,no_of_days,price) values(?,?,?,?,?);"; 
        String sqlQuery2 = "SELECT * FROM tour ORDER BY id DESC LIMIT 1"; 
        if(jdbcTemplate.update(sqlQuery1,tour.getName(),tour.getDetails(),tour.getPlaces(),tour.getNo_of_days(),tour.getPrice()) > 0){
          TourRowMapper tourRowMapper = new TourRowMapper();
          Tour tour1 = (Tour)jdbcTemplate.queryForObject(sqlQuery2, tourRowMapper);
          return tour1;
          }
         return null;
    }

    /////////////// UPDATE TOUR PACKAGE //////////////////
    public Boolean updateTour(Long id, Tour tour){
        logger.info("Inside updateTour() method in DAO");

        String sqlQuery = "UPDATE tour SET name= ?, details=? , places=?, price=?, no_of_days=?  WHERE id = ?;";
          if(jdbcTemplate.update(sqlQuery, tour.getName(),tour.getDetails(),tour.getPlaces(),tour.getPrice(),tour.getNo_of_days(),id) > 0){
              return true;
          }
          return false;
    }


     /////////////// ADD OR UPDATE TOUR PACKAGE -PART 2 (Images) //////////////////
    public List<ImageUpResponse> addTourImages(Long tour_id,MultipartFile[] files) throws DataAccessException, IOException {
        logger.info("Inside addTourImages() method in DAO");
        List<ImageUpResponse> c = new ArrayList<ImageUpResponse>();
        

        String sqlQuery = "INSERT Into images(content_type, data, name, size,tour_id) values(?,?,?,?,?);"; 

        for(MultipartFile file: files) {
                if(file.getOriginalFilename().contains(".jpg") || file.getOriginalFilename().contains(".jpeg") || file.getOriginalFilename().contains(".png") || file.getOriginalFilename().contains(".gif") ){
                        if(jdbcTemplate.update(sqlQuery, file.getContentType(), file.getBytes(),StringUtils.cleanPath(file.getOriginalFilename()),file.getSize(),tour_id) > 0){
                                        c.add(new ImageUpResponse(StringUtils.cleanPath(file.getOriginalFilename()) + " Uploaded Successfully")); 
                           }    
                }
                else{
                            c.add(new  ImageUpResponse(StringUtils.cleanPath(file.getOriginalFilename()) + " not Supported")); 
                        }
        }   
        return  c;
    }    

    /////////////// RETRIEVE TOUR PACKAGE BY TOUR ID  //////////////////
    public Tour viewTour(Long tour_id) {

        logger.info("Inside viewTour() method in DAO");

        String sqlQuery = "SELECT * from tour where id = ?";
        TourRowMapper tourRowMapper = new TourRowMapper();
        
        try {
            return (Tour)jdbcTemplate.queryForObject(sqlQuery, tourRowMapper, tour_id);
        }
        catch(EmptyResultDataAccessException ex) {
            return null;
        }
    }    


    
     /////////////// RETRIEVE IMAGES FOR TOUR PACKAGE BY TOUR ID (Images) //////////////////
    public List<ImageDownResponse> getAllFiles(Long id) {
        logger.info("Inside getAllFiles() method in DAO");
        String sqlQuery = "select * from images where tour_id="+id;      
          List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlQuery);
          List<ImageDownResponse> urlResponses = new ArrayList<ImageDownResponse>();
          String url = "https://holiday-makers.herokuapp.com/api/v1/admin/getAllImages/";
          for (Map<String, Object> rs : rows) 
          {
               ImageDownResponse urlResponse = new ImageDownResponse((Long)rs.get("id"), (url+(Long)rs.get("id")));
               urlResponses.add(urlResponse);
          }
         return urlResponses;        
    }


    public Image getFile(Long id) {
        logger.info("Inside getFile() method in DAO");
        String sqlQuery = "select * from images where id=?";
        ImageRowMapper imageRowMapper = new ImageRowMapper();
        Image image = (Image)jdbcTemplate.queryForObject(sqlQuery, imageRowMapper, id);
        return image;
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


    public Boolean deleteTour(Long id) {
        logger.info("Inside deleteTour() method in DAO");
         String sqlQuery = "DELETE from tour where id = ?";        
            if(jdbcTemplate.update(sqlQuery, id) > 0){
              return true ;
            }       
          return false;
    }

     public Boolean deleteImagesById(Long id) {
        logger.info("Inside deleteImagesById() method in DAO");
        String sqlQuery = "DELETE from images where id= ?";
           if(jdbcTemplate.update(sqlQuery, id) > 0){
              return true ;
            }     
          return false;
    }

    public List<BookingDetails> getDetailsByBookingId(Long booking_id) {
        logger.info("Inside getDetailsByBookingId() method in DAO");
        String SQL = "select * from booking_details where booking_id = "+booking_id;
        return jdbcTemplate.query(SQL, new BookingDetailsRowMapper());
    }

    public DashboardResponse getInfo() {
        logger.info("Inside getInfo() method in DAO");
        Integer USERS = listUsers().size();
        Long BOOKING = getAllBooking().stream().filter(booking -> booking.getStatus().equalsIgnoreCase("Pending for Confirmation")).count();
        Long ISSUE =  getAllIsues().stream().filter(issue -> issue.getReply() == null || issue.getReply().equals("")).count(); 
        DashboardResponse dashboardResponse = new DashboardResponse(BOOKING,ISSUE,USERS);
        return dashboardResponse;
    }

    
    
}
