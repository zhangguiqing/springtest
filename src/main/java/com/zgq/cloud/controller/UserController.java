package com.zgq.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.zgq.cloud.dao.UserRespository;
import com.zgq.cloud.entity.User;

@RestController
public class UserController {
	@Autowired
	private UserRespository respository;
	@Autowired
	private EurekaClient discoverClient;
	@Autowired
	private DiscoveryClient discoverClient1;
	
	@GetMapping("/simple/{id}")
	public User findById(@PathVariable Long id) {
		return this.respository.findOne(id);
	}
	@GetMapping("/eureka-instance")
	public String serviceUrl() {
		InstanceInfo instance = this.discoverClient.getNextServerFromEureka("PROVIDE-USER", false);
		return instance.getHomePageUrl();
	}
	@GetMapping("/instance-info")
	public ServiceInstance showInfo() {
		ServiceInstance localServiceinstance = this.discoverClient1.getLocalServiceInstance();
		return localServiceinstance;
	}
	@PostMapping("/user")
	public User postUser(@RequestBody User user) {
		return user;
	}
}
