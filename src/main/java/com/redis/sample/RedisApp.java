package com.redis.sample;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * Hello world!
 *
 */
public class RedisApp 
{
    public static void main( String[] args ) throws IOException, ClassNotFoundException
    {
       // System.out.println( "redis" );
        Jedis jedis = new Jedis("localhost");
      jedis.mset("ella:notification-0x2a23680bd8fc1f9eb76a9dd0410eK", "send:Y, email:arokapil@gmail.com");
      jedis.mset("ella:notification-0x2a23680bd8fc1f9ebb6316349e39276a9dd0410e", "send:Y, email:arokapil@gmail.com");
       List<String> names=null;
       Set<String> allKey= jedis.keys("*:notification-*");
       
      
       System.out.println(allKey);

       Iterator<String> itset = allKey.iterator();
       int j =0;
       while (itset.hasNext()) {
           String s = itset.next();
           names=jedis.mget(s);
           for (String string : names) {
        	   j++;
			if(string.contains("send:Y")) {
				String Str [] = string.split(",");
				System.out.println();
				if(Str.length>0){
					for (String string2 : Str) {
						System.out.print(string2);
						String emailStr [] = string.split(":");						
						if(null!=emailStr && emailStr.length==3) {
							System.out.print(emailStr[2]);
							SendMailJavaAPI.sendEmail(emailStr[2]);
						}
					}
				}
				
			}
		}	jedis.mset(s, "sent:N");
	System.out.println(jedis.mget(s));
       }
    }
}
