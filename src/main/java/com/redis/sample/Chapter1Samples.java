package com.redis.sample;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

public class Chapter1Samples {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Jedis conn = new Jedis("localhost");
		
		//String example
		conn.set("taco-key", "taco uno");
		System.out.println(conn.get("taco-key"));
		conn.del("taco-key");
		System.out.println(conn.get("taco-key"));
		
		//List example
		List<String> tacoList = conn.lrange("taco-list", 0, -1);
		conn.rpush("taco-list", "taco 1");
		conn.rpush("taco-list", "taco 2");
		conn.rpush("taco-list", "taco 3");
		tacoList = conn.lrange("taco-list", 0, -1);
		for(String taco: tacoList){
			System.out.println(taco);
		}
		System.out.println(conn.lindex("taco-list", 0));
		conn.lpop("taco-list");
		
		tacoList = conn.lrange("taco-list", 0, -1);
		for(String taco: tacoList){
			System.out.println(taco);
		}
		
		//clear list
		tacoList = conn.lrange("taco-list", 0, -1);
		for(String taco: tacoList){
			conn.lpop("taco-list");
		}
		
		//Set Example
		conn.sadd("taco-set", "cheese taco");
		conn.sadd("taco-set", "chicken taco");
		conn.sadd("taco-set", "beef taco");
		conn.sadd("taco-set", "cheese taco");
		
		Set<String> tacoSet = conn.smembers("taco-set");
		for(String taco : tacoSet){
			System.out.println(taco);
		}
		if(conn.sismember("taco-set", "cheese taco")){
			System.out.println("We have Cheese Tacos");
		}
		
		conn.srem("taco-set", "cheese taco");
		tacoSet = conn.smembers("taco-set");
		for(String taco : tacoSet){
			System.out.println(taco);
		}
		
		//HashSet Example
		conn.hset("taco-hash-key", "tacoKey1", "Taco Hash Uno");
		conn.hset("taco-hash-key", "tacoKey2", "Taco Hash Dos");
		
		Map<String,String> tacoHash = conn.hgetAll("taco-hash-key");
		for (Map.Entry<String, String> entry : tacoHash.entrySet()){
			System.out.println("Entry Key: " + entry.getKey() +" Entry Value: " + entry.getValue());
		}
		conn.hdel("taco-hash-key", "tacoKey1");
		tacoHash = conn.hgetAll("taco-hash-key");
		for (Map.Entry<String, String> entry : tacoHash.entrySet()){
			System.out.println("Entry Key: " + entry.getKey() +" Entry Value: " + entry.getValue());
		}
		
		//Zset Example
		conn.zadd("zTaco-Key", 90, "zTaco-1");
		conn.zadd("zTaco-Key", 80, "zTaco-2");
		conn.zadd("zTaco-Key", 70, "zTaco-3");
		Set<String> zTacoset = conn.zrange("zTaco-Key", 0, -1);
		for(String taco : zTacoset){
			System.out.println(taco);
		}
		Set<Tuple> zScoreTacoSet = conn.zrangeByScoreWithScores("zTaco-Key", 0,80);
		for(Tuple tacoTuple : zScoreTacoSet){
			System.out.println("Key: " + tacoTuple.getElement() + " Score:" + tacoTuple.getScore());
		}
		
		
		
		
	
		

	}

}
