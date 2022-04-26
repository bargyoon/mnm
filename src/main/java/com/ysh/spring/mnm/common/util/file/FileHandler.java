package com.ysh.spring.mnm.common.util.file;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@Slf4j
public class FileHandler {


    @GetMapping("/download")
    public ResponseEntity<UrlResource> downloadFile(FileInfo file) throws UnsupportedEncodingException, URISyntaxException, MalformedURLException {
        UrlResource resource = new UrlResource(new URI(file.getLink()));
        ResponseEntity<UrlResource> response =
                ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType("application/octet-stream"))
                        .header("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(file.getOriginFileName(), "UTF-8")+"\"")
                        .body(resource);
        return response;
    }

}
