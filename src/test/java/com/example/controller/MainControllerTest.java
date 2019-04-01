package com.example.controller;


import com.example.dao.StorageDao;
import com.example.entity.Request;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {

    @InjectMocks
    MainController controller;

    @Mock
    StorageDao storageDao;


    @Test
    public void retrieveNotFound() {
        Request request = new Request();

        request.setStrId("ASDFCNVJFMDISJCIDJIS");
        request.setBody("Hello Word!!");
        Mockito.when(storageDao.retrieve("")).thenReturn(null);

        ResponseEntity<?> result = controller.retrieve("ASDFCNVJFMDISJCIDJIS");

        assertTrue(result.getStatusCode().value() == 404);
    }

    @Test
    public void retrieveFound() {
        Request request = new Request();

        request.setStrId("ASDFCNVJFMDISJCIDJIS");
        request.setBody("Hello Word!!");
        Mockito.when(storageDao.retrieve("ASDFCNVJFMDISJCIDJIS")).thenReturn(request);

        ResponseEntity<?> result = controller.retrieve("ASDFCNVJFMDISJCIDJIS");


        assertTrue(result.getStatusCode().value() == 200);
    }

    @Test
    public void add(){


        ResponseEntity<?> responseEntity = controller.create("Hello World");

        System.out.println(responseEntity.getStatusCode());

        assertTrue(responseEntity.getStatusCode().value() == 201);

    }

    @Test
    public void removeNotFound() {
        Request request = new Request();

        request.setStrId("ASDFCNVJFMDISJCIDJIS");
        request.setBody("Hello Word!!");
        Mockito.when(storageDao.retrieve("ASDFCNVJFMDISJCIDJIS")).thenReturn(null);

        ResponseEntity<?> responseEntity = controller.delete("ASDFCNVJFMDISJCIDJI");

        assertTrue(responseEntity.getStatusCode().value() == 404);


    }

    @Test
    public void updateNotFound() {
        Request request = new Request();

        request.setStrId("ASDFCNVJFMDISJCIDJIS");
        request.setBody("Hello Word!!");
        Mockito.when(storageDao.retrieve("ASDFCNVJFMDISJCIDJIS")).thenReturn(null);

        ResponseEntity<?> responseEntity = controller.update("ASDFCNVJFMDISJCIDJAS","Hello Word!!");

        assertTrue(responseEntity.getStatusCode().value() == 404);


    }

    @Test
    public void removeFound() {
        Request request = new Request();

        request.setStrId("ASDFCNVJFMDISJCIDJIS");
        request.setBody("Hello Word!!");
        Mockito.when(storageDao.retrieve("ASDFCNVJFMDISJCIDJIS")).thenReturn(request);

        ResponseEntity<?> responseEntity = controller.delete("ASDFCNVJFMDISJCIDJIS");

        System.out.println(responseEntity.getStatusCode());

        assertTrue(responseEntity.getStatusCode().value() == 204);


    }

    @Test
    public void updateFound() {
        Request request = new Request();

        request.setStrId("ASDFCNVJFMDISJCIDJIS");
        request.setBody("Hello Word!!");
        Mockito.when(storageDao.retrieve("ASDFCNVJFMDISJCIDJIS")).thenReturn(request);

        ResponseEntity<?> responseEntity = controller.update("ASDFCNVJFMDISJCIDJIS","Hello Word!!");

        System.out.println(responseEntity.getStatusCode());

        assertTrue(responseEntity.getStatusCode().value() == 204);


    }

}
