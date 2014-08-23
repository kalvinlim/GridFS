package com.gridfs;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.domain.User;
import com.main.Application;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.persistence.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class TestFoo {

	@Autowired
	GridFsOperations operations;
	
	@Autowired
	UserRepository userRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(TestFoo.class);
	
	@Test
	public void testBootstrapped() {
		assertTrue(true);
	}

	@Ignore @Test
	public void testGrid(){
		DBObject metaData = new BasicDBObject();
		metaData.put("foo", "Bar");
		
		Resource file = new ClassPathResource("iStock_000015201389Small.jpg");
		try {
			operations.store(file.getInputStream(),"testfile.jpg", metaData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Ignore @Test 
	public void testGridFsRetrieve(){
		List<GridFSDBFile> result = operations.find(
	               new Query().addCriteria(Criteria.where("metadata.foo").is("Bar")));
		
		logger.info("{}", result.size());
		
		for(GridFSDBFile file : result){
			try {
				file.writeTo("/cache/fooFileTest.jpg");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testGridFsRetrieve2(){
		//List<GridFSDBFile> result = operations.find(new Query().addCriteria(Criteria.where("metadata.foo").is("Bar")));
		
		List<GridFSDBFile> result = operations.find(new Query().addCriteria(Criteria.where("_id").is("53f3b681e30e5fece034441d")));
		
		logger.info("{}", result.size());
		
		for(GridFSDBFile file : result){
			logger.info("{}", file.toString());
			logger.info("{}", file.getId());
			
			logger.info("==========================================");
		}
	}
	
	@Test
	public void testUser(){
	
		User user = new User();
		user.setUsername("FooUserName");
		
		//userRepository.save(user);
	}
}
