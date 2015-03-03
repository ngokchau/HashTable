
public class Test {

	public static void main(String[] args) {
		FileInput.init();

		String[] s = FileInput.readShakespeare();
		String[] b = FileInput.readBacon();

		//TODO Initialize the hash tables
		ChainingHash ch = new ChainingHash();
		QPHash qph = new QPHash();

		//TODO Use the FileInput functions to read the two files.
		// Input the elements of those two files into two hash tables,
		// one file into a ChainingHash object and the other into a QPHash object.
		for(String w : s)
		{
			ch.insert(w);
		}

		for(String w : b)
		{
			qph.insert(w);
		}

		int a = 0;

		//TODO Initialize necessary variables for calculating the square error
		// and most distant word.
		
		//TODO Iterate through the first hash table and add sum the squared error
		// for these words.
		
		//TODO Find  words in the second hash table that are not in the first and add their squared error
		// to the total
		
		//TODO Print the total calculated squared error for the two tables and also the word with the highest distance.
	}

}
