import java.util.Random;

public class AQPHashTable extends OAHashTable {

	private ModHash hashFunc;
	public AQPHashTable(int m, long p) {
		super(m);
		this.hashFunc = ModHash.GetFunc(m, p);
	}
	
	@Override
	public int Hash(long x, int i) {

		int initialHash = this.hashFunc.Hash(x);
		int m = this.table.length;
		int addition;

		if (i % 2 == 0)
			addition = 1;
		else
			addition = -1;

		int hashValue = (initialHash + addition * (int)(Math.pow(i,2))) % m;
		if (hashValue < 0)
			hashValue += m;

		return hashValue;
	}
}
