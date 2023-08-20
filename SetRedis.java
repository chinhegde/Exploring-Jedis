package com.check.verify;
import redis.clients.jedis.*;

import java.util.Set;

public class SetRedis {
	public static void main(String[] args) {
		
		JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "localhost", 6379);
		
		System.out.println();
		try (Jedis jedis = jedisPool.getResource()) {
			System.out.println("This is Chinmayi Hegde's Redis. Connection to server successfully");
			//check whether server is running or not
			System.out.println("Server is running: "+jedis.ping());
			System.out.println();
			
			// Use the SADD command to add accessories to the Honda Civic
			jedis.sadd("hegde_car_accessories:HondaCivic", "Sunroof", "Leather Seats", "Backup Camera");

			// Use the SMEMBERS command to retrieve all the accessories for the Honda Civic
			Set<String> accessories = jedis.smembers("hegde_car_accessories:HondaCivic");

			// Print the accessories
			System.out.println(accessories);

			
		}
	}
}
