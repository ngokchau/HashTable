public class ChainingHash
{
    private class Node
    {
        int count;
        String key;
        Node next;

        /**
         * Constructor
         * @param keyToAdd key to add to field.
         */
        public Node(String keyToAdd)
        {
            this.count = 1;
            this.key = keyToAdd;
            this.next = null;
        }
    }

    int tableSize;
    int current;
    Node[] table;

    /**
     * Constructor with default size.
     */
    public ChainingHash()
    {
        this.tableSize = 31753;
        this.current = 0;
        this.table = new Node[this.tableSize];
    }

    /**
     * Constructor
     * @param startSize starting size of hash table.
     */
    public ChainingHash(int startSize)
    {
        this.tableSize = startSize;
        this.current = 0;
        this.table = new Node[startSize];
    }

    /**
     * This function allows rudimentary iteration through the ChainingHash.
     * The ordering is not important so long as all added elements are returned only once.
     * It should return null once it has gone through all elements
     * @return Returns the next element of the hash table. Returns null if it is at its end.
     */
    public String getNextKey(){
        //TODO returns the next key in the hash table.
        //You will need external tracking variables to account for this.
        return "";
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

        Node r = new Node(keyToAdd);

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
                r.next = new Node(keyToAdd);
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
            Node r = this.table[i];

            while(!r.key.equals(keyToFind) && null != r.next)
            {
                r = r.next;
            }

            return r.count;
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
