package pers.zhoulingbo.leetcode;

public class BitManipulation
{

    public static void main(String[] args)
    {

    }

    /**
     * 405. 数字转换为十六进制数(convert-a-number-to-hexadecimal)
     * @param num
     * @return
     */
    public static String toHex(int num)
    {
        if (num == 0)
            return "0";

        char[] a = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        StringBuffer strb = new StringBuffer();
        if (num > 0)
        {
            while (num > 0)
            {
                int t = num & 0xf;
                strb.append(a[t]);
                num = num >> 4;
            }
            return strb.reverse().toString();
        }
        else
        {
            char[] b = Integer.toBinaryString(num).toCharArray();
            for (int i = 0; i < b.length; i+=4)
            {
                int t = (int)(b[i]-'0')*8 + (int)(b[i+1]-'0')*4 + (int)(b[i+2]-'0')*2 + (int)(b[i+3]-'0');
                strb.append(a[t]);
            }
            return strb.toString();
        }
    }
}
