package com.gridfs;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.main.Application;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class TestFoo {

	@Autowired
	GridFsOperations operations;
	
	private static final Logger logger = LoggerFactory.getLogger(TestFoo.class);
	
	@Test
	public void testBootstrapped() {
		assertTrue(true);
	}

	@Test
	public void testGrid(){
		DBObject metaData = new BasicDBObject();
		metaData.put("foo", "Bar");
		InputStream file = null;
		Resource resource = new ClassPathResource("iStock_000015201389Small.jpg");
		try {
			file = new FileInputStream("iStock_000015201389Small.jpg");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//operations.store(file,"testfile.jpg", metaData);
	}
}
