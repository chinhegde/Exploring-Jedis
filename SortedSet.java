package com.check.verify;
import redis.clients.jedis.*;

import java.util.Set;

public class SortedSet {
	public static void main(String[] args) {
		
		JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "localhost", 6379);
		
		System.out.println();
		try (Jedis jedis = jedisPool.getResource()) {
			System.out.println("This is Chinmayi Hegde's Redis. Connection to server successfully");
			
			//check whether server is running or not
			System.out.println("Server is running: "+jedis.ping());
			System.out.println();
			

			// Use the ZADD command to add the given value for the Honda Civic along with the date
			jedis.zadd("hegde_car_value:HondaCivic", 20220101, "50000");
			jedis.zadd("hegde_car_value:HondaCivic", 20220102, "49500");

			// Use the ZRANGE command to retrieve the full list of value history with dates for the Honda Civic
			Set<Tuple> valueHistory = jedis.zrangeWithScores("hegde_car_value:HondaCivic", 0, -1);

			// Print the value history with dates
			for (Tuple tuple : valueHistory) {
			    System.out.println("Date: " + tuple.getElement() + ", Value: " + tuple.getScore());
			}
		
		}
	}
}
