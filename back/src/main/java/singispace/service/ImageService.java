package singispace.service;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.stream.Stream;

@Service
public class ImageService {

    private final Path ProfilePhotoroot = Paths.get("images/profile_photos");
    private final Path ThemePhotoroot = Paths.get("images/themes");
    private final Path PagePhotoroot = Paths.get("images/pages");
    private final Path PostPhotoroot = Paths.get("images/posts");

    public void saveProfilePhoto(MultipartFile file){
        try {
            Files.copy(file.getInputStream(), this.ProfilePhotoroot.resolve(file.getOriginalFilename()));
        } catch (Exception e){
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    public void saveThemePhoto(MultipartFile file){
        try {
            Files.copy(file.getInputStream(), this.ThemePhotoroot.resolve(file.getOriginalFilename()));
        } catch (Exception e){
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    public void savePagePhoto(MultipartFile file){
        try {
            Files.copy(file.getInputStream(), this.PagePhotoroot.resolve(file.getOriginalFilename()));
        } catch (Exception e){
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    public void savePostPhoto(MultipartFile file){
        try {
            Files.copy(file.getInputStream(), this.PostPhotoroot.resolve(file.getOriginalFilename()));
        } catch (Exception e){
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    public Resource loadProfile(String filename) {
        try {
            Path file = ProfilePhotoroot.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public Resource loadThemePhoto(String filename) {
        try {
            Path file = ThemePhotoroot.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public Resource loadPagePhoto(String filename) {
        try {
            Path file = PagePhotoroot.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public Resource loadPostPhoto(String filename) {
        try {
            Path file = PostPhotoroot.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }



//    public Stream<Path> loadAll() {
//        try {
//            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
//        } catch (IOException e) {
//            throw new RuntimeException("Could not load the files!");
//        }
//    }

}
