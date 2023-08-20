package com.check.verify;
import redis.clients.jedis.*;

import java.util.List;

public class RedisList {
	public static void main(String[] args) {
		
		JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "localhost", 6379);
		
		System.out.println();
		try (Jedis jedis = jedisPool.getResource()) {
			System.out.println("This is Chinmayi Hegde's Redis. Connection to server successfully");
			//check whether server is running or not
			System.out.println("Server is running: "+jedis.ping());
			System.out.println();
			
			// Use the LPUSH command to add maintenance history items to the beginning of the list for the Honda Civic
			jedis.lpush("hegde_car_maintenance:HondaCivic", "Brake Pad Replacement", "Oil Change");

			// Use the LRANGE command to retrieve the full list of maintenance history for the Honda Civic
			List<String> maintenanceHistory = jedis.lrange("hegde_car_maintenance:HondaCivic", 0, -1);

			// Print the maintenance history
			System.out.println(maintenanceHistory);
			
		}
	}
}
