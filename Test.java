public class Test
{
    private class List
    {

    }

	public static void main(String[] args)
    {
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

		//TODO Initialize necessary variables for calculating the square error
		// and most distant word.
        String word = "";
        float total = 0;
        float max = 0;

		//TODO Iterate through the first hash table and add sum the squared error
		// for these words.
        String u = qph.getNextKey();
        while(null != u)
        {
            int uCount = qph.findCount(u);
            int vCount = ch.findCount(u);
            float uFreq = (float) uCount / b.length;
            float vFreq = (float) vCount / s.length;

            total += Math.pow(uFreq - vFreq, 2);

            if(Math.pow(uFreq - vFreq, 2) > max)
            {
                max = (float) Math.pow(uFreq - vFreq, 2);
                word = u;
            }

            u = qph.getNextKey();
        }

		//TODO Find  words in the second hash table that are not in the first and add their squared error
		// to the total

        String v = ch.getNextKey();
        while(null != v)
        {
            int uCount = qph.findCount(v);
            int vCount = ch.findCount(v);
            float uFreq = (float) uCount / b.length;
            float vFreq = (float) vCount / s.length;

            if(vCount > 0 && uCount == 0)
            {
                total += Math.pow(vFreq, 2);
            }

            if(Math.pow(uFreq - vFreq, 2) > max)
            {
                max = (float) Math.pow(uFreq - vFreq, 2);
                word = v;
            }

            v = ch.getNextKey();
        }

		//TODO Print the total calculated squared error for the two tables and also the word with the highest distance.
	}

}
