package com.example.controller;

import com.example.dao.StorageDao;
import com.example.entity.Request;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.parser.Entity;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

@Controller
public class MainController {


    @Autowired
    private StorageDao storageDao;

    /**
     *
     * @param documentId
     * @return
     */
    @RequestMapping(value = "/storage/documents/{documentId}", method = RequestMethod.GET)
    @ResponseStatus
    public ResponseEntity<?> retrieve(@PathVariable("documentId") String documentId) {

        System.out.println("Controller: " + documentId);

        Request retrieve = storageDao.retrieve(documentId);

        if (retrieve == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        System.out.println(retrieve.getBody());

        return new ResponseEntity<>(retrieve.getBody(),HttpStatus.OK);
    }

    @RequestMapping(value = "/storage/documents", method = RequestMethod.POST)
    @ResponseBody ( )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(  @RequestBody String body) {

        Request r = new Request();
        r.setStrId(RandomStringUtils.random(20, true, true));


        if (body != null) {
            r.setBody(body);
        }


        Serializable result = storageDao.add(r);

        if (result != null){


            return new ResponseEntity<>(result.toString(), HttpStatus.CREATED);
        }

        return null;
    }

    @RequestMapping(value = "/storage/documents/{documentId}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("documentId") String documentId, @RequestBody String body) {
        System.out.println("Controller: " + documentId);

        Request retrieve = storageDao.retrieve(documentId);

        if (retrieve == null) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);

        }

        retrieve.setBody(body);

        storageDao.update(retrieve);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/storage/documents/{documentId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("documentId") String documentId) {

        System.out.println("Controller: " + documentId);

        Request retrieve = storageDao.retrieve(documentId);

        if (retrieve == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        storageDao.delete(retrieve);

        return new ResponseEntity<>( HttpStatus.NO_CONTENT);

    }
}
