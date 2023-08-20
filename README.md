# Exploring Jedis Programming
Using Redis' Java client Jedis as a part of CS 157C

## String
Function: Update car mileage
### Why is this function useful?
This function is useful for customers who are interested in buying a used car as it helps
them verify the accuracy of the odometer reading, which can have a significant impact on
the car's resale value.

**Source Code:** Refer StringRedis.java
This code uses the Jedis Java client library (redis.clients.jedis.*) to interact with a
Redis server running on the localhost to demonstrate how to use Jedis to set and retrieve a value from a Redis server using the SET and GET commands for STRING.
```
JedisPool jedispool = new JedisPool(new JedisPoolConfig(),
“localhost”, 6379)
```
This creates a new Jedis instance by passing the hostname or IP
address of the Redis server (Jedis jedis = jedisPool.getResource()) to the
constructor
- SET command (jedis.set): "car_mileage:Smith" as the key and "35000" as the value.
- GET command (jedis.get): "car_mileage:Smith" as the key, and assigning the returned
value to a string variable named mileage.
- jedis.incrBy() is used to increment the mileage.

## Set
Function: Add car accessories
**Source code:** Refer SetRedis.java

- SADD command (jedis.sadd) is used to add multiple items to a Redis set. The set is identified by the key "hegde_car_accessories:HondaCivic" - using a prefix to group related keys. The items to add to the set are "Sunroof", "Leather Seats", and "Backup
Camera".
- jedis.sismember() is used to check if a car has a certain attribute.

### Why is this function useful?
This is useful for customers who are looking to purchase a car with specific features or
amenities and want to filter through listings to find what they are looking for, while also providing a potential seller with an opportunity to highlight unique selling points of their car.

We can use the following code to implement taking user input to retrieving the information:
```
Scanner scanner = new Scanner(System.in);
System.out.println("Enter car model: ");
String carModel = scanner.nextLine();
String accessoriesKey = "hegde_car_accessories:" + carModel;
Set<String> accessories = jedis.smembers(accessoriesKey);
```

## List
Function: Show car maintenance history
### Why is this function useful to customers?
This function is useful for customers who are interested in buying a used car as it allows
them to verify the level of care that was taken with the vehicle, which can help them make
an informed decision about the car's overall condition and potential maintenance costs.

**Source code:** RedisList.java

- LPUSH command (jedis.lpush) is used to add two maintenance history items, "Brake
Pad Replacement" and "Oil Change", to the beginning of the list for the Honda Civic car.
The list is created with the key "hegde_car_maintenance:HondaCivic".
- LRANGE command (jedis.lrange) is used to retrieve the full list of maintenance history
for the Honda Civic, with an index range of 0 to -1, which means the entire list is retrieved. The retrieved list is stored in a Java List<String>.

## Sorted Set
Function: Show car value history
### Why is this function useful to customers?
This function is useful for customers who are looking to buy or sell a car as it provides a historical record of the car's value over time, which can help inform pricing decisions and provide insight into market trends.

**Source code:** SortedSet.java

- ZADD command (jedis.zadd) Add a new element to the sorted set named "hegde_car_value:HondaCivic" with a score (date) and a value of car value.
- ZRANGE - argument WITH SCORES (jedis.zrangeWithScores) retrieves all elements with scores from the sorted set named "hegde_car_value:HondaCivic".

## Hash
Function: Search car by brand/model
### Why is this feature useful?
This function is useful for customers who are looking for a specific type of car and want to quickly filter through listings to find what they are looking for, while also providing a seller with an opportunity to highlight key features or selling points of their car.

- HSET command (jedis.hset) method is used to add car details to the hash. Three car
details are added in total; two for Tesla and one for Ford.
- The first argument of hset method specifies the hash key, which consists of a database
name and brand name concatenated using a colon separator.
- The second argument specifies the field name, which is the car model name.
- The third argument specifies the value, which is the car details.

- HGET command (jedis.hget) method is used to retrieve the car details of a specific
model of a specific brand.
- HGETALL (jedis.hgetAll) method is used to retrieve all cars of a specific brand.
