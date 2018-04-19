package ftnhps.movieenthusiasts.projections;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

	@ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/upload")
    public void upload(@RequestParam("file") MultipartFile file, @RequestParam("username") String username ) throws IOException {

        byte[] bytes;

        if (!file.isEmpty()) {
             bytes = file.getBytes();
             Path path = Paths.get("src","main","resources","static","img",file.getOriginalFilename());
             //Path path = Paths.get("/home/horva/Desktop/"+ file.getOriginalFilename());
             Files.write(path, bytes);
        }

        System.out.println(String.format("receive %s from %s", file.getOriginalFilename(), username));
    }
}
