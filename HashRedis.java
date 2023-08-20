package com.check.verify;
import redis.clients.jedis.*;

import java.util.Map;

public class HashRedis {
	public static void main(String[] args) {
		
		JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "localhost", 6379);
		
		System.out.println();
		try (Jedis jedis = jedisPool.getResource()) {
			System.out.println("This is Chinmayi Hegde's Redis. Connection to server successfully");
			//check whether server is running or not
			System.out.println("Server is running: "+jedis.ping());
			System.out.println();
			
			// Adding car inventory to Redis hash
			jedis.hset("hegde_car_inventory:Tesla", "Model X", "Used 2018 Model X Performance");
			jedis.hset("hegde_car_inventory:Tesla", "Model 2019", "Cool car");
			jedis.hset("hegde_car_inventory:Ford", "F-150", "New 2022 F-150 XL");

			// Retrieving car details by brand and model
			String carDetails = jedis.hget("hegde_car_inventory:Tesla", "Model X");
			System.out.println(carDetails);
			// Output: Used 2018 Model X Performance

			// Retrieving all cars of a specific brand
			Map<String, String> carInventory = jedis.hgetAll("hegde_car_inventory:Tesla");
			System.out.println(carInventory);
			// Output: {Model X=Used 2018 Model X Performance, Model 2019=Cool car}

			
		}
	}
}
