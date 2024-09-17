import java.util.Random;
import java.util.Arrays;
import java.util.*;

public class RSA {
	
	public static void main(String[] args) throws IllegalArgumentException {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("What is your message?");
		String input = sc.nextLine();
		System.out.println();
		
		
	//encryption	
		
		int p = generateRandomPrime();
		int q = generateRandomPrime();
		int N = p*q;
		int phi = (p-1)*(q-1);
		int e = generateE(phi, N);
		System.out.println("p = " + p);
		System.out.println("q = " + q);
		System.out.println("N = " + N);
		System.out.println("phi = " + phi);
		System.out.println("e = " + e);
		
		String encrypted = "";
		int pubKey = 0;
		
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			System.out.println();
			System.out.println("Encrypting " + c + ": ");
			int M = computeM(c);
			System.out.println("M = " + M);
			pubKey = generatePublicKey(M, e, N);
			System.out.println("Public key: " + pubKey);
			System.out.println("Encrypted Message: " + encryptLetter(pubKey));
			
			encrypted = encrypted + encryptLetter(pubKey);
		}
		
		System.out.println("\n" + input + " encrypted: " + encrypted);
		
		
				
		
		
	}
	
	public static char originalLetter(int pubKey, int privKey, int N) {
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

		int a = (int) Math.pow(pubKey, privKey);
		int b = a % N;
		
		char ch = alphabet[b];
		return ch;
	}
	
	
	
	public static int privKey(int e, int phi, int p, int q) {
		for (int i = 0; i < 1000; i++) {
			int d = e*i;
			if (d % phi == 1) {
				return d;
			}
		}
		return 0;
	}
	
	
	public static char encryptLetter(int pubKey) {
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	    if (pubKey > 26 || pubKey <= 0) {
	    	Random random = new Random(); pubKey = random.nextInt(25)+1;
	    }
	    return (alphabet[pubKey - 1]);
	}
	
	public static int generatePublicKey(int M, int e, int N) {
		int pubKey = (int) (Math.pow(M, e)) % N;
		if (pubKey == 0) { 
			Random random = new Random(); pubKey = random.nextInt(N-1)+1;
			//System.out.println("pubKey was zero generated a new random : " + pubKey);
		}
		return pubKey;
	}
	
	//computes the M value for each letter
	public static int computeM(char c) {
		return Character.getNumericValue(c) - 9;
	}
		

		
		
		
	//generates e value
	 public static int generateE(int phi, int N) {
	       System.out.println(N);
	       Random random = new Random();
	        int e;
	        
	        do {
	            e = random.nextInt(N - 2) + 2; 
	            // Generates a random number between 2 and N-1
	        } while (gcd(e, phi) != 1);
		    //System.out.println ("E : " + e);    
	        return e;
	 }
		 
	 public static int gcd(int a, int b) {
		    //System.out.println("GCD: " + a + ", " + b);
	        if (b == 0)
	            return a;
	        else
	            return gcd(b, a % b);
	 }
	
//generates random prime number between 1-10
	public static int generateRandomPrime() {
        Random random = new Random();
        int prime;
        boolean isPrime;

        do {
            prime = random.nextInt(10) + 1;
            isPrime = true;

            if (prime == 1) {
                isPrime = false;
                continue;
            }

            for (int i = 2; i <= Math.sqrt(prime); i++) {
            	if (prime % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        } while (!isPrime);

        return prime;
    }
		
	
	 
	 
	 
	 
	
	
	
	

}
