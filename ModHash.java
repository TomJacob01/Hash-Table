import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ModHash {

	private long a;
	private long b;
	private long p;
	private int m;

	public ModHash(long a, long b, long p, int m) {
		this.a = a;
		this.b = b;
		this.p = p;
		this.m = m;
	}
	public static ModHash GetFunc(int m, long p){
		long a = ThreadLocalRandom.current().nextLong(1, p);
		long b = ThreadLocalRandom.current().nextLong(0, p);
		return new ModHash(a,b,p,m);
	}
	
	public int Hash(long key) {
		int hash;
		hash = (int)((this.a * key + this.b) % p);
		if (hash < 0)
			hash += p;
		hash = hash % m;

		return hash;
	}
}
