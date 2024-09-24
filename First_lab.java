class HighArray
{
    private final long[] a;
    private int lenArray;
    private long maxValArr;
    private long minValArr;
    private long medianArr;
    public HighArray(int n)
    {
        a = new long[n];
        lenArray = 0;
    }
    public void insert(long val)
    {
        int i = 0;
        while(i < lenArray && a[i] < val)
        {
            i++;
        }
        for(int j = lenArray; j > i; j --)
        {
            a[j] = a[j-1];
        }
        a[i] = val;
        lenArray ++;
        //System.out.println(lenArray);
        if(lenArray == 1)
        {
            maxValArr = val;
            minValArr = val;
        }
        else if(maxValArr < val) maxValArr = val;
        else if(minValArr > val) minValArr = val;
    }
    public void max()
    {
        for(int i = 0; i < lenArray; i ++)
        {
            if(maxValArr < a[i])
                maxValArr = a[i];
        }
    }
    public void min()
    {
        for(int i = 0; i < lenArray; i ++)
        {
            if(minValArr > a[i])
                maxValArr = a[i];
        }
    }
    public void print()
    {
        for(int i = 0; i < lenArray; i ++)
            System.out.print(a[i] + " ");
        System.out.println();
    }
    public void median()
    {
        if(lenArray % 2 == 1) medianArr = a[lenArray / 2];
        else medianArr = (a[lenArray / 2] + a[lenArray / 2 - 1]) / 2;
    }
    public boolean delete(long val)
    {
        int i;
        for(i = 0; i < lenArray; i ++)
        {
            if(a[i] == val) break;
        }
        if(i != lenArray)
        {
            for( ; i < lenArray - 1; i ++)
            {
                a[i] = a[i+1];
            }
            lenArray --;
            max();
            min();
            median();
            return true;
        }
        else return false;
    }
    public long getMax()
    {
        return maxValArr;
    }
    public long getMin()
    {
        return minValArr;
    }
    public long getMedian()
    {
        median();
        return medianArr;
    }
    public boolean findLinear(long Key)
    {
        for(int i = 0; i < lenArray; i ++)
        {
            if(a[i] == Key)
            {
                return true;
            }
            if(a[i] > Key)
            {
                return false;
            }
        }
        return false;
    }
    /*public boolean findBin(long Key)
    {
        int l = 0, r = lenArray, m;
        while(r - l > 1)
        {
            m = (l + r) / 2;
            if(a[m] > Key) r = m;
            else l = m;
        }
        return a[l] == Key || a[r] == Key;
    }*/
    public  boolean findBin(long Key, int l, int r)
    {
        if(a[l] == Key) return true;
        if(r - l <= 1) return false;
        return (findBin(Key, (l+r)/2, r) || findBin(Key, l, (l+r)/2));
    }
}

public class First_lab {
    public static void main(String[] args) {
        int maxlen = 100;

        HighArray arr = new HighArray(maxlen);

        arr.insert(33);
        arr.insert(33);
        arr.insert(11);
        arr.insert(32);
        arr.insert(12);
        arr.insert(23);
        //arr.insert(1);

        arr.print();
        long Key = 11;
        if(arr.findLinear(Key))
        {
            System.out.println("Find " + Key);
        }
        else System.out.println("Can't find " + Key);

        if(arr.findBin(Key, 0, maxlen-1))
        {
            System.out.println("Find " + Key);
        }
        else System.out.println("Can't find " + Key);

        System.out.print("Median: ");
        System.out.println(arr.getMedian());

        System.out.print("Max: ");
        System.out.println(arr.getMax());

        System.out.print("Min: ");
        System.out.println(arr.getMin());

        System.out.println("Delete " + 1);
        arr.delete(1);

        arr.print();

        System.out.println("Median: ");
        System.out.println(arr.getMedian());
    }
}