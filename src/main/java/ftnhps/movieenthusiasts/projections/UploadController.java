package ftnhps.movieenthusiasts.projections;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import ftnhps.movieenthusiasts.users.User;
import ftnhps.movieenthusiasts.users.UserType;

@Controller
public class UploadController {

	@Autowired
	private HttpSession session;
	
	@ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/upload")
    public void upload(@RequestParam("file") MultipartFile file, @RequestParam("username") String username ) throws IOException {

		User user = (User) session.getAttribute("user");
		if(user == null)
			return ;
		if(user.getUserType() != UserType.PLACEADMIN)
			return ;
		
        byte[] bytes;

        if (!file.isEmpty()) {
        	try (InputStream input = file.getInputStream()) {
        	    try {
        	        ImageIO.read(input).toString();
        	        // It's an image (only BMP, GIF, JPG and PNG are recognized).
        	    } catch (Exception e) {
        	        return ;
        	    }
        	}
        	
             bytes = file.getBytes();
             Path path = Paths.get("src","main","resources","static","img",file.getOriginalFilename());
             //Path path = Paths.get("/home/horva/Desktop/"+ file.getOriginalFilename());
             Files.write(path, bytes);
        }
        else
        	return;

        System.out.println(String.format("receive %s from %s", file.getOriginalFilename(), username));
    }
}
