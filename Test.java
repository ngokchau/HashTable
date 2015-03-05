/**
 * Chau Ngo
 */

public class Test
{
	public static void main(String[] args)
    {

		FileInput.init();

		String[] s = FileInput.readShakespeare();
		String[] b = FileInput.readBacon();

        /**
         * Initialize hash tables.
         */
		ChainingHash ch = new ChainingHash();
		QPHash qph = new QPHash();

        /**
         * Input elements from two files into hash tables.
         * Shakespeare in ChainingHash, Bacon in QPHash.
         */
		for(String w : s)
		{
			ch.insert(w);
		}

		for(String w : b)
		{
			qph.insert(w);
		}

        /**
         * Variables for calculating square error
         */
        String word = "";
        float total = 0;
        float max = 0;

        /**
         * Iterate through the first hash table and add sum the squared error
         * for these words.
         */
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

        /**
         * Find words in the second table that are not in the first and add
         * square error to the total.
         */
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

        /**
         * Print the results.
         */
        System.out.println("Total square error: " + total);
        System.out.println("Most different word: " + word + " (" + max + ")");
	}

}
