package com.check.verify;
import redis.clients.jedis.*;

public class StringRedis {
	public static void main(String[] args) {
		
		JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "localhost", 6379);
		
		System.out.println();
		try (Jedis jedis = jedisPool.getResource()) {
			System.out.println("This is Chinmayi Hegde's Redis. Connection to server successfully");
			//check whether server is running or not
			System.out.println("Server is running: "+jedis.ping());
			System.out.println();
			
			//Use the SET command to set the mileage of Smith's car to 35,000
			jedis.set("car_mileage:Smith", "35000");
			
			//Use the GET command to retrieve the mileage value for Smith's car
			String mileage = jedis.get("car_mileage:Smith");
			
			//Print the mileage value
			System.out.println("The mileage of the car is "+mileage);
			
		}
	}
}
