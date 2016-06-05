package cz.concrea.conferences.business.service;



import java.util.Locale;

import org.springframework.stereotype.Service;

@Service
public class CryptLongParameterService {

	public String encryptLong(long source) throws Exception{
		return Obfuscate.obfuscate(source);
	}
	
	
	public long decryptLong(String source) throws Exception{
		return Obfuscate.illuminate(source);
	}
	
	
	static class Obfuscate {

	    final static long feistelRounds = 4;
	    final static long randRounds = 4;
	    final static long seed = 12345;

	    final static long mod = 60466176; //36^5

	    private static long f (long x) {
	        final long a = 12+1;
	        final long c = 1361423303;
	        x = (x + seed) % mod;
	        long r = randRounds;
	        while (r-- != 0) {
	            x = (a*x+c) % mod;
	        }
	        return x;
	    }

	    public static String obfuscate (long i) {
	        long a = i / mod;
	        long b = i % mod;
	        long r = feistelRounds;
	        while (r-- != 0) {
	            a = (a + f(b)) % mod;
	            b = (b + f(a)) % mod;
	        }
	        return pad5(Long.toString(a, 36)) + pad5(Long.toString(b, 36));
	    }

	    public static long illuminate (String s) {
	        long a = Long.valueOf(s.substring(0,5),36);
	        long b = Long.valueOf(s.substring(5,10),36);
	        long r = feistelRounds;
	        while (r-- != 0) {
	            b = (b - f(a)) % mod;
	            a = (a - f(b)) % mod;
	        }
	        a = (a + mod)%mod;
	        b = (b + mod)%mod;

	        return a*mod+b;
	    }

	    public static String pad5(String s) {
	        return String.format("%5s", s).replace(' ', '0').toUpperCase(Locale.ENGLISH);
	    }

	    public static String pad10(String s) {
	        return String.format("%10s", s).replace(' ', '0').toUpperCase(Locale.ENGLISH);
	    }

	}
}
