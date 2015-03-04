public class Test
{
	public static void main(String[] args)
    {
		FileInput.init();

		String[] s = FileInput.readShakespeare();
		String[] b = FileInput.readBacon();

		ChainingHash ch = new ChainingHash();
		QPHash qph = new QPHash();

		for(String w : s)
		{
			ch.insert(w);
		}

		for(String w : b)
		{
			qph.insert(w);
		}

        String word = "";
        float total = 0;
        float max = 0;

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

        System.out.println("Most different square difference: " + word + " (square difference of " + max + ")");
        System.out.println("Total comparative error: " + total);
	}

}
