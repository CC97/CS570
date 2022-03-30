package Homework1;

/*
    Homework 1
    Name: Cheng Chen
    CWID: 10473438

    Big-endian is used in the code below
    The operation named shiftR(int amount) actually shift all digits to the left!
*/
public class BinaryNumber{
    private int data[];
    private boolean overflow;

    public  BinaryNumber(int length)
    {
        data = new int[length];
        for (int i = 0; i < length; i++)
        {
            data[i] = 0;
        }
    }

    public BinaryNumber(String str)
    {
        int length = str.length();
        data = new int[length];

        for (int i = 0; i < length; i++)
        {
            if (Character.getNumericValue(str.charAt(i)) == 1 || Character.getNumericValue(str.charAt(i)) == 0)
            {
                data[i] = Character.getNumericValue(str.charAt(i));
            }
            else
            {
                System.out.println("Invalid Number!");
            }
        }
    }

    public int getLength()
    {
        return data.length;
    }

    public int getDigit(int index)
    {
        if (index >= data.length)
        {
            System.out.println("Index is out of range");
        }
        else
        {
            return data[index];
        }
        return 0;
    }

    public void shiftR(int amount)
    {
        int[] newData = new int[amount + data.length];

        for (int i = 0; i < data.length; i++)
        {
            newData[i] = data[i];
        }
        for (int i = 0; i < amount; i++)
        {
            newData[i + data.length] = 0;
        }
        data = newData;
    }

    public void add(BinaryNumber aBinaryNumber)
    {
        this.clearOverflow();
        int data2[] = new int[aBinaryNumber.getLength()];
        data2 = aBinaryNumber.data;
        
        if (this.data.length == data2.length)
        {
            int a = 0;
            int arr[] = new int[this.data.length];
            
            for (int i = arr.length - 1; i >= 0; i--)
            {
                int sum = a + data[i] + data2[i];
                if (sum == 0)
                {
                    arr[i] = 0;
                    a = 0;
                }
                else if (sum == 1)
                {
                    arr[i] = 1;
                    a = 0;
                }
                else if (sum == 2)
                {
                    arr[i] = 0;
                    a = 1;
                }
                else if (sum == 3)
                {
                    arr[i] = 1;
                    a = 1;
                }
            }
            if (a == 1)
            {
                overflow = true;
            }
            else
            {
                this.data = arr;
            }
        }
        else
        {
            System.out.println("The lengths of the binary numbers do not coincide!");
        }
    }

    public String toString()
    {
        if (overflow)
        {
            return "overflow";
        }
        else
        {
            for (int i = 0; i < data.length; i++)
            {
                System.out.print(data[i]);
            }
        }
        return " ";
    }

    public int toDecimal()
    {
        int decimal = 0;
        int length = data.length - 1;

        while(length >= 0)
        {
            decimal += data[length] * Math.pow(2, data.length - length - 1);
            length --;
        }
        return decimal;
    }

    public void clearOverflow()
    {
        overflow = false;
    }
    
    public static void main(String[] args) {
        BinaryNumber bn1 = new BinaryNumber(7);
        BinaryNumber bn2 = new BinaryNumber("1011");
        BinaryNumber bn3 = new BinaryNumber("0100110");
        BinaryNumber bn4 = new BinaryNumber("0000001");

        System.out.println(bn1.toString());
        System.out.println(bn2.getLength());
        System.out.println(bn2.getDigit(3));

        bn2.shiftR(3);
        System.out.println(bn2.toString());
        System.out.println(bn2.toDecimal());

        bn2.add(bn3);
        System.out.println(bn2.toString());

        bn2.add(bn3);
        System.out.println(bn2.toString());

        bn2.add(bn4);
        System.out.println(bn2.toString());
    }
}