public class ChainingHash
{
    private class Record
    {
        int count;
        boolean visited;
        String key;
        Record next;

        /**
         * Constructor
         * @param keyToAdd key to add to field.
         */
        public Record(String keyToAdd)
        {
            this.count = 1;
            this.visited = false;
            this.key = keyToAdd;
            this.next = null;
        }
    }

    int tableSize;
    int current;
    Record[] table;

    /**
     * Constructor with default size.
     */
    public ChainingHash()
    {
        this.tableSize = 31753;
        this.current = 0;
        this.table = new Record[this.tableSize];
    }

    /**
     * Constructor
     * @param startSize starting size of hash table.
     */
    public ChainingHash(int startSize)
    {
        this.tableSize = startSize;
        this.current = 0;
        this.table = new Record[startSize];
    }

    /**
     * This function allows rudimentary iteration through the ChainingHash.
     * The ordering is not important so long as all added elements are returned only once.
     * It should return null once it has gone through all elements
     * @return Returns the next element of the hash table. Returns null if it is at its end.
     */
    public String getNextKey()
    {
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
                while(r.visited)
                {
                    r = r.next;
                }

                r.visited = true;

                if(r.visited && r.next == null)
                {
                    this.current++;
                }

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
     * If there is a collision, it should be dealt with by chaining the keys together.
     * If the key is already in the hash table, it increments that key's counter.
     * @param keyToAdd : the key which will be added to the hash table
     */
    public void insert(String keyToAdd){
        int hashCode = this.hash(keyToAdd);
        int i = hashCode % this.tableSize;

        Record r = new Record(keyToAdd);

        if(null == this.table[i])
        {
            this.table[i] = r;
        }
        else
        {
            r = this.table[i];

            while(!r.key.equals(keyToAdd) && r.next != null)
            {
                r = r.next;
            }

            if(r.key.equals(keyToAdd))
            {
                r.count++;
            }
            else
            {
                r.next = new Record(keyToAdd);
            }
        }
    }

    /**
     * Returns the number of times a key has been added to the hash table.
     * @param keyToFind : The key being searched for
     * @return returns the number of times that key has been added.
     */
    public int findCount(String keyToFind){
        int hashCode = this.hash(keyToFind.toLowerCase());
        int i = hashCode % this.tableSize;

        if(null == this.table[i])
        {
            return 0;
        }
        else
        {
            Record r = this.table[i];

            while(!r.key.equals(keyToFind) && null != r.next)
            {
                r = r.next;
            }

            if(r.key.equals(keyToFind))
            {
                return r.count;
            }
            else
            {
                return 0;
            }
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
