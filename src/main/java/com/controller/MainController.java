package com.controller;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.gridfs.GridFSDBFile;


@Controller
public class MainController {
	@Autowired GridFsOperations operations;
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping("/")
	public ModelAndView index(){
		return new ModelAndView("index");
	}
	public ModelAndView test(){
		ModelAndView mav = new ModelAndView();
		GridFSDBFile file = new GridFSDBFile();
		mav.addObject("foo", file.getInputStream());
		return mav;
	}
	@RequestMapping(value="/foo")
	public @ResponseBody void getPhoto2(HttpServletRequest request, HttpServletResponse response){
		logger.info("Retrieving photos");
		List<GridFSDBFile> result = operations.find(
	               new Query().addCriteria(Criteria.where("metadata.foo").is("Bar")));
		
		logger.info("{}", result.size());
		
		
		
		for(GridFSDBFile file : result){
			try {
				response.getOutputStream().write(IOUtils.toByteArray(file.getInputStream()));
				response.getOutputStream().flush();
				response.getOutputStream().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@RequestMapping(value="/all")
	public @ResponseBody void getPhoto(HttpServletRequest request, HttpServletResponse response){
		logger.info("Retrieving photos");
		List<GridFSDBFile> result = operations.find(
	               new Query().addCriteria(Criteria.where("metadata.foo").is("Bar")));
		
		logger.info("{}", result.size());
		
		
		
		for(GridFSDBFile file : result){
				logger.info("{}", file.toString());
		}
	
	}
}
