package singispace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import singispace.service.ImageService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping("/uploadProfile")
    public ResponseEntity<String> uploadProfilePhoto(@RequestParam("file") MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            imageService.saveProfilePhoto(file);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/images/profile_photos/")
                    .path(fileName)
                    .toUriString();
            System.out.println(fileDownloadUri);
            return new ResponseEntity<String>(fileDownloadUri, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/uploadTheme")
    public ResponseEntity<String> uploadThemePhoto(@RequestParam("file") MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            imageService.saveThemePhoto(file);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/images/themes/")
                    .path(fileName)
                    .toUriString();
            System.out.println(fileDownloadUri);
            return new ResponseEntity<String>(fileDownloadUri, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/uploadPage")
    public ResponseEntity<String> uploadPagePhoto(@RequestParam("file") MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            imageService.savePagePhoto(file);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/images/pages/")
                    .path(fileName)
                    .toUriString();
            System.out.println(fileDownloadUri);
            return new ResponseEntity<String>(fileDownloadUri, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("profile_photos/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getProfilePhoto(@PathVariable String filename) {
        Resource file = imageService.loadProfile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("themes/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getThemePhoto(@PathVariable String filename) {
        Resource file = imageService.loadThemePhoto(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("pages/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getPagePhoto(@PathVariable String filename) {
        Resource file = imageService.loadPagePhoto(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
