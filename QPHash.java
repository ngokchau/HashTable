/**
 * Chau Ngo
 */

public class QPHash
{
    private class Record
    {
        int count;
        String key;

        /**
         * Constructor
         * @param keyToAdd key to add to field.
         */
        public Record(String keyToAdd)
        {
            this.count = 1;
            this.key = keyToAdd;
        }
    }

    int tableSize;
    int current;
    Record[] table;

    /**
     * Constructor with default size.
     */
    public QPHash()
    {
        this.tableSize = 64301;
        this.current = 0;
        this.table = new Record[this.tableSize];
    }

    /**
     * Constructor
     * @param startSize starting size of hash table.
     */
    public QPHash(int startSize){
        this.tableSize = startSize;
        this.current = 0;
        this.table = new Record[this.tableSize];
    }

    /**
     * This function allows rudimentary iteration through the QPHash.
     * The ordering is not important so long as all added elements are returned only once.
     * It should return null once it has gone through all elements
     * @return Returns the next element of the hash table. Returns null if it is at its end.
     */
    public String getNextKey(){
        Record r = this.nextRecord();

        if(null != r)
        {
            return r.key;
        }
        else
        {
            return null;
        }
    }

    /**
     * Helper method to that retrieves the next Record.
     * @return Record
     */
    private Record nextRecord()
    {
        if(this.current < this.tableSize)
        {
            while(this.current < this.tableSize && null == this.table[this.current])
            {
                this.current++;
            }

            if(this.current == this.tableSize)
            {
                return null;
            }
            else
            {
                Record r = this.table[this.current];
                this.current++;
                return r;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * Adds the key to the hash table.
     * If there is a collision, a new location should be found using quadratic probing.
     * If the key is already in the hash table, it increments that key's counter.
     * @param keyToAdd : the key which will be added to the hash table
     */
    public void insert(String keyToAdd)
    {
        int hc = this.hash(keyToAdd);
        int i = hc % this.tableSize;

        Record r = new Record(keyToAdd);

        if(null == this.table[i])
        {
            this.table[i] = r;
        }
        else
        {
            r = this.table[i];

            if(!r.key.equals(keyToAdd))
            {
                int j = this.probe(i, keyToAdd);

                if(null == this.table[j])
                {
                    r = new Record(keyToAdd);
                    this.table[j] = r;
                }
                else
                {
                    this.table[j].count++;
                }
            }
            else
            {
                r.count++;
            }
        }
    }

    /**
     * Quadratic probing.
     * @param c the starting index.
     * @param k the key to be inserted.
     * @return j the index to insert new key.
     */
    private int probe(int c, String k)
    {
        if(c == this.tableSize - 1)
        {
            c = -1;
        }

        int i = 1;
        int j = (c + i) % this.tableSize;

        while(null != this.table[j] && !k.equals(this.table[j].key))
        {
            i++;
            int x = i * i;
            j = (c + x) % this.tableSize;

            if(j > this.tableSize - 1)
            {
                j = j - tableSize - 1;
            }
        }

        return j;
    }

    /**
     * Returns the number of times a key has been added to the hash table.
     * @param keyToFind : The key being searched for
     * @return returns the number of times that key has been added.
     */
    public int findCount(String keyToFind){
        int hc = this.hash(keyToFind);
        int i = hc % this.tableSize;

        if(null == this.table[i])
        {
            return 0;
        }
        else if(null != this.table[i] && !this.table[i].key.equals(keyToFind))
        {
            int j = this.probe(i, keyToFind);

            if(null == this.table[j])
            {
                return 0;
            }
            else
            {
                return this.table[j].count;
            }
        }
        else
        {
            return this.table[i].count;
        }
    }

    /**
     * @param keyToHash the key to be hashed.
     * @return the hash code of the key.
     */
    private int hash(String keyToHash)
    {
        return Math.abs(keyToHash.hashCode());
    }
}
