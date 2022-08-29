import java.util.Random;

public class DoubleHashTable extends OAHashTable {

	private ModHash firstHashFunc;
	private ModHash secondHashFunc;

	public DoubleHashTable(int m, long p) {
		super(m);
		this.firstHashFunc = ModHash.GetFunc(m, p);
		this.secondHashFunc = ModHash.GetFunc(m - 1,p);
	}
	
	@Override
	public int Hash(long x, int i) {

		int initialHash1 = this.firstHashFunc.Hash(x);
		int initialHash2 = this.secondHashFunc.Hash(x) + 1;

		int m = this.table.length;
		int hashValue = (initialHash1 + i * initialHash2) % m;
		return hashValue;
	}
	
}
