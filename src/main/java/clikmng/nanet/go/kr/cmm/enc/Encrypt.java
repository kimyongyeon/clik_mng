package clikmng.nanet.go.kr.cmm.enc;

import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

public class Encrypt
{

    public Encrypt()
    {
    }

    static void des_key(long al[], String s)
    {
        deskey(s, (short)0);
        cpkey(al);
        deskey(s, (short)1);
        cpkey(dk);
    }

    static void DES_SetKey(String s)
    {
        des_key(ek, s);
    }

    static void deskey(String s, short word0)
    {
        char ac[] = new char[56];
        char ac1[] = new char[56];
        long al[] = new long[32];
        char ac2[] = s.toCharArray();
        for(int k1 = 0; k1 < 56; k1++)
        {
            char c = pc1[k1];
            int l = c & 7;
            char c1 = ac2[c >> 3];
            int i2 = c1 & bytebit[l];
            if(i2 == 0)
                ac[k1] = '\0';
            else
                ac[k1] = '\001';
            char c2 = ac[k1];
        }

        for(int i = 0; i < 16; i++)
        {
            int i1;
            if(word0 == 1)
                i1 = 15 - i << 1;
            else
                i1 = i << 1;
            int j1 = i1 + 1;
            al[i1] = al[j1] = 0L;
            for(int l1 = 0; l1 < 28; l1++)
            {
                int j = l1 + totrot[i];
                if(j < 28)
                    ac1[l1] = ac[j];
                else
                    ac1[l1] = ac[j - 28];
            }

            for(int j2 = 28; j2 < 56; j2++)
            {
                int k = j2 + totrot[i];
                if(k < 56)
                    ac1[j2] = ac[k];
                else
                    ac1[j2] = ac[k - 28];
            }

            for(int k2 = 0; k2 < 24; k2++)
            {
                char c3 = ac1[pc2[k2]];
                if(c3 != 0)
                    al[i1] |= bigbyte[k2];
                c3 = ac1[pc2[k2 + 24]];
                if(c3 != 0)
                    al[j1] |= bigbyte[k2];
            }

        }

        cookey(al);
    }

    static void cookey(long al[])
    {
        long al3[] = new long[32];
        long al1[] = al3;
        long al2[] = al;
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < 16) 
        {
            al2[i] = al[j++];
            al1[k] = (al2[i] & 0xfc0000L) << 6;
            al1[k] |= (al2[i] & 4032L) << 10;
            al1[k] |= (al[j] & 0xfc0000L) >> 10;
            al1[k++] |= (al[j] & 4032L) >> 6;
            al1[k] = (al2[i] & 0x3f000L) << 12;
            al1[k] |= (al2[i] & 63L) << 16;
            al1[k] |= (al[j] & 0x3f000L) >> 4;
            al1[k++] |= al[j] & 63L;
            i++;
            j++;
        }
        usekey(al3);
    }

    static void usekey(long al[])
    {
        long al1[] = KnL;
        for(int i = 0; i < 32; i++)
            al1[i] = al[i];

    }

    static void cpkey(long al[])
    {
        long al1[] = KnL;
        for(int i = 0; i < 32; i++)
            al[i] = al1[i];

    }

    static void desfunc(long al[], long al1[])
    {
        boolean flag = false;
        long l3 = al[0];
        long l2 = al[1];
        long l1 = (l3 >> 4 ^ l2) & 0xf0f0f0fL;
        l2 ^= l1;
        l3 ^= l1 << 4;
        l1 = (l3 >> 16 ^ l2) & 65535L;
        l2 ^= l1;
        l3 ^= l1 << 16;
        l1 = (l2 >> 2 ^ l3) & 0x33333333L;
        l3 ^= l1;
        l2 ^= l1 << 2;
        l1 = (l2 >> 8 ^ l3) & 0xff00ffL;
        l3 ^= l1;
        l2 ^= l1 << 8;
        l2 = (l2 << 1 | l2 >> 31 & 1L) & 0xffffffffL;
        l1 = (l3 ^ l2) & 0xaaaaaaaaL;
        l3 ^= l1;
        l2 ^= l1;
        l3 = (l3 << 1 | l3 >> 31 & 1L) & 0xffffffffL;
        int j = 0;
        for(int i = 0; i < 8; i++)
        {
            l1 = l2 << 28 | l2 >> 4;
            l1 ^= al1[j++];
            long l = SP7[(int)(l1 & 63L)];
            l |= SP5[(int)(l1 >> 8 & 63L)];
            l |= SP3[(int)(l1 >> 16 & 63L)];
            l |= SP1[(int)(l1 >> 24 & 63L)];
            l1 = l2 ^ al1[j++];
            l |= SP8[(int)(l1 & 63L)];
            l |= SP6[(int)(l1 >> 8 & 63L)];
            l |= SP4[(int)(l1 >> 16 & 63L)];
            l |= SP2[(int)(l1 >> 24 & 63L)];
            l3 ^= l;
            l1 = l3 << 28 | l3 >> 4;
            l1 ^= al1[j++];
            l = SP7[(int)(l1 & 63L)];
            l |= SP5[(int)(l1 >> 8 & 63L)];
            l |= SP3[(int)(l1 >> 16 & 63L)];
            l |= SP1[(int)(l1 >> 24 & 63L)];
            l1 = l3 ^ al1[j++];
            l |= SP8[(int)(l1 & 63L)];
            l |= SP6[(int)(l1 >> 8 & 63L)];
            l |= SP4[(int)(l1 >> 16 & 63L)];
            l |= SP2[(int)(l1 >> 24 & 63L)];
            l2 ^= l;
        }

        l2 = l2 << 31 | l2 >> 1;
        l1 = (l3 ^ l2) & 0xaaaaaaaaL;
        l3 ^= l1;
        l2 ^= l1;
        l3 = l3 << 31 | l3 >> 1;
        l1 = (l3 >> 8 ^ l2) & 0xff00ffL;
        l2 ^= l1;
        l3 ^= l1 << 8;
        l1 = (l3 >> 2 ^ l2) & 0x33333333L;
        l2 ^= l1;
        l3 ^= l1 << 2;
        l1 = (l2 >> 16 ^ l3) & 65535L;
        l3 ^= l1;
        l2 ^= l1 << 16;
        l1 = (l2 >> 4 ^ l3) & 0xf0f0f0fL;
        l3 ^= l1;
        l2 ^= l1 << 4;
        al[0] = l2;
        al[1] = l3;
    }

    static void scrunch(char ac[], long al[])
    {
        if(ac.length > 0)
        {
            int i = 0;
            if(ac.length > i)
                al[0] = ((long)ac[i++] & 255L) << 24;
            if(ac.length > i)
                al[0] |= ((long)ac[i++] & 255L) << 16;
            if(ac.length > i)
                al[0] |= ((long)ac[i++] & 255L) << 8;
            if(ac.length > i)
                al[0] |= (long)ac[i++] & 255L;
            if(ac.length > i)
                al[1] = ((long)ac[i++] & 255L) << 24;
            if(ac.length > i)
                al[1] |= ((long)ac[i++] & 255L) << 16;
            if(ac.length > i)
                al[1] |= ((long)ac[i++] & 255L) << 8;
            if(ac.length > i)
                al[1] |= (long)ac[i] & 255L;
        } else
        {
            al[0] = 0L;
            al[1] = 0L;
            return;
        }
    }

    static String unscrun(long al[], String s)
    {
        s = "";
        s = s + (char)(int)(al[0] >> 24 & 255L);
        s = s + (char)(int)(al[0] >> 16 & 255L);
        s = s + (char)(int)(al[0] >> 8 & 255L);
        s = s + (char)(int)(al[0] & 255L);
        s = s + (char)(int)(al[1] >> 24 & 255L);
        s = s + (char)(int)(al[1] >> 16 & 255L);
        s = s + (char)(int)(al[1] >> 8 & 255L);
        s = s + (char)(int)(al[1] & 255L);
        return s;
    }

    static String des_dec(String s, int i)
    {
        long al[] = new long[2];
        int l = 0;
        boolean flag = false;
        String s1 = new String();
        String s3 = "";
        char ac[] = s.toCharArray();
        int k = ac.length;
        l = k / 8;
        for(int j = 0; j < i; j++)
        {
            scrunch(ac, al);
            desfunc(al, dk);
            String s2 = unscrun(al, String.valueOf(ac));
            if(l > 0)
            {
                s = s.substring(8);
                int i1 = s.length();
                char ac1[] = s.toCharArray();
                ac = ac1;
            } else
            {
                s = "";
                ac = s.toCharArray();
            }
            l--;
            s3 = s3 + s2;
        }

        return s3;
    }

    static String des_enc(String s, int i)
    {
        long al[] = new long[2];
        int l = 0;
        boolean flag = false;
        String s1 = new String();
        String s3 = "";
        char ac[] = s.toCharArray();
        int k = ac.length;
        l = k / 8;
        for(int j = 0; j < i; j++)
        {
            scrunch(ac, al);
            desfunc(al, ek);
            String s2 = unscrun(al, String.valueOf(ac));
            if(l > 0)
            {
                s = s.substring(8);
                int i1 = s.length();
                char ac1[] = s.toCharArray();
                ac = ac1;
            } else
            {
                s = "";
                ac = s.toCharArray();
            }
            l--;
            s3 = s3 + s2;
        }

        return s3;
    }

    static String DES_Encrypt(String s, int i)
    {
        return des_enc(s, i);
    }

    static String StrToHexBlk(String s, int i)
    {
        byte byte0 = 32;
        String s1 = "";
        String s2 = "";
        StringBuffer stringbuffer = new StringBuffer();
        s1 = "0123456789abcdef";
        for(int j = 0; j < s.length(); j++)
        {
            char c = s.charAt(j);
            stringbuffer.append(s1.charAt((c & 0xf0) >> 4));
            stringbuffer.append(s1.charAt(c & 0xf));
        }

        s2 = stringbuffer.toString();
        return s2;
    }

    public static String com_EncodeJtoJ(String s)
    {
        String s1 = "";
        String s2 = "";
        String s3 = "";
        int i = 0;
        int j = 0;
        s = s + "|";
        s = Ko2En(s);
        s1 = "SINGLE_v_4.0_OFFICE_v_4.0";
        i = s.length() + 8;
        j = (i - 1) / 8 + 1;
        i = j * 8 + 1;
        s2 = s;
        DES_SetKey(s1);
        s2 = DES_Encrypt(s2, j);
        s3 = StrToHexBlk(s2, j * 8);
        int k = s3.length();
        for(int l = 0; l < j * 2 - k; l++)
            s3 = s3 + "0";

        return s3;
    }

    public static String com_Encode(String s)
    {
        String s1 = "";
        String s2 = "";
        String s3 = "";
        int i = 0;
        int j = 0;
        s = Ko2En(s);
        s1 = "SINGLE_v_4.0_OFFICE_v_4.0";
        i = s.length() + 8;
        j = (i - 1) / 8 + 1;
        i = j * 8 + 1;
        s2 = s;
        DES_SetKey(s1);
        s2 = DES_Encrypt(s2, j);
        s3 = StrToHexBlk(s2, j * 8);
        int k = s3.length();
        for(int l = 0; l < j * 2 - k; l++)
            s3 = s3 + "0";

        return s3;
    }

    static String HexToStrBlk(String s)
    {
        boolean flag = false;
        int j = 0;
        boolean flag1 = false;
        String s1 = "";
        String s2 = "";
        j = s.length();
        for(int i = 0; i < j; i++)
        {
            int k;
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9')
                k = s.charAt(i) - 48;
            else
                k = (s.charAt(i) - 97) + 10;
            k *= 16;
            i++;
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9')
                k += s.charAt(i) - 48;
            else
                k += (s.charAt(i) - 97) + 10;
            s2 = s2 + (char)k;
        }

        return s2;
    }

    public static String com_DecodeJtoJ(String s)
    {
        String s1 = "";
        String s2 = "";
        int i = 0;
        int j = 0;
        s2 = "SINGLE_v_4.0_OFFICE_v_4.0";
        i = s.length();
        j = (i / 2 - 1) / 8 + 1;
        i = j * 8 + 1;
        s1 = HexToStrBlk(s);
        DES_SetKey(s2);
        s1 = DES_Decrypt(s1, j);
        s1 = En2Ko(s1);
        StringTokenizer stringtokenizer = new StringTokenizer(s1.trim(), "|");
        s1 = stringtokenizer.nextToken();
        return s1;
    }

    public static String com_Decode(String s)
    {
        String s1 = "";
        String s2 = "";
        int i = 0;
        int j = 0;
        s2 = "SINGLE_v_4.0_OFFICE_v_4.0";
        i = s.length();
        j = (i / 2 - 1) / 8 + 1;
        i = j * 8 + 1;
        s1 = HexToStrBlk(s);
        DES_SetKey(s2);
        s1 = DES_Decrypt(s1, j);
        s1 = En2Ko(s1);
        return s1;
    }

    static String DES_Decrypt(String s, int i)
    {
        return des_dec(s, i);
    }

    public static synchronized String En2Ko(String s)
    {
        String s1 = null;
        if(s == null)
            return null;
        try
        {
            s1 = new String(new String(s.getBytes("8859_1"), "KSC5601"));
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            s1 = new String(s);
        }
        return s1;
    }

    public static synchronized String Ko2En(String s)
    {
        String s1 = null;
        if(s == null)
            return null;
        s1 = new String(s);
        try
        {
            s1 = new String(new String(s.getBytes("KSC5601"), "8859_1"));
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            s1 = new String(s);
        }
        return s1;
    }

    static final short EN0 = 0;
    static final short DE1 = 1;
    static long KnL[] = new long[32];
    static long ek[] = new long[32];
    static long dk[] = new long[32];
    static short bytebit[] = {
        128, 64, 32, 16, 8, 4, 2, 1
    };
    static long bigbyte[] = {
        0x800000L, 0x400000L, 0x200000L, 0x100000L, 0x80000L, 0x40000L, 0x20000L, 0x10000L, 32768L, 16384L, 
        8192L, 4096L, 2048L, 1024L, 512L, 256L, 128L, 64L, 32L, 16L, 
        8L, 4L, 2L, 1L
    };
    static char pc1[] = {
        '8', '0', '(', ' ', '\030', '\020', '\b', '\0', '9', '1', 
        ')', '!', '\031', '\021', '\t', '\001', ':', '2', '*', '"', 
        '\032', '\022', '\n', '\002', ';', '3', '+', '#', '>', '6', 
        '.', '&', '\036', '\026', '\016', '\006', '=', '5', '-', '%', 
        '\035', '\025', '\r', '\005', '<', '4', ',', '$', '\034', '\024', 
        '\f', '\004', '\033', '\023', '\013', '\003'
    };
    static char pc2[] = {
        '\r', '\020', '\n', '\027', '\0', '\004', '\002', '\033', '\016', '\005', 
        '\024', '\t', '\026', '\022', '\013', '\003', '\031', '\007', '\017', '\006', 
        '\032', '\023', '\f', '\001', '(', '3', '\036', '$', '.', '6', 
        '\035', '\'', '2', ',', ' ', '/', '+', '0', '&', '7', 
        '!', '4', '-', ')', '1', '#', '\034', '\037'
    };
    static char totrot[] = {
        '\001', '\002', '\004', '\006', '\b', '\n', '\f', '\016', '\017', '\021', 
        '\023', '\025', '\027', '\031', '\033', '\034'
    };
    static long SP1[] = {
        0x1010400L, 0L, 0x10000L, 0x1010404L, 0x1010004L, 0x10404L, 4L, 0x10000L, 1024L, 0x1010400L, 
        0x1010404L, 1024L, 0x1000404L, 0x1010004L, 0x1000000L, 4L, 1028L, 0x1000400L, 0x1000400L, 0x10400L, 
        0x10400L, 0x1010000L, 0x1010000L, 0x1000404L, 0x10004L, 0x1000004L, 0x1000004L, 0x10004L, 0L, 1028L, 
        0x10404L, 0x1000000L, 0x10000L, 0x1010404L, 4L, 0x1010000L, 0x1010400L, 0x1000000L, 0x1000000L, 1024L, 
        0x1010004L, 0x10000L, 0x10400L, 0x1000004L, 1024L, 4L, 0x1000404L, 0x10404L, 0x1010404L, 0x10004L, 
        0x1010000L, 0x1000404L, 0x1000004L, 1028L, 0x10404L, 0x1010400L, 1028L, 0x1000400L, 0x1000400L, 0L, 
        0x10004L, 0x10400L, 0L, 0x1010004L
    };
    static long SP2[] = {
        0x80108020L, 0x80008000L, 32768L, 0x108020L, 0x100000L, 32L, 0x80100020L, 0x80008020L, 0x80000020L, 0x80108020L, 
        0x80108000L, 0x80000000L, 0x80008000L, 0x100000L, 32L, 0x80100020L, 0x108000L, 0x100020L, 0x80008020L, 0L, 
        0x80000000L, 32768L, 0x108020L, 0x80100000L, 0x100020L, 0x80000020L, 0L, 0x108000L, 32800L, 0x80108000L, 
        0x80100000L, 32800L, 0L, 0x108020L, 0x80100020L, 0x100000L, 0x80008020L, 0x80100000L, 0x80108000L, 32768L, 
        0x80100000L, 0x80008000L, 32L, 0x80108020L, 0x108020L, 32L, 32768L, 0x80000000L, 32800L, 0x80108000L, 
        0x100000L, 0x80000020L, 0x100020L, 0x80008020L, 0x80000020L, 0x100020L, 0x108000L, 0L, 0x80008000L, 32800L, 
        0x80000000L, 0x80100020L, 0x80108020L, 0x108000L
    };
    static long SP3[] = {
        520L, 0x8020200L, 0L, 0x8020008L, 0x8000200L, 0L, 0x20208L, 0x8000200L, 0x20008L, 0x8000008L, 
        0x8000008L, 0x20000L, 0x8020208L, 0x20008L, 0x8020000L, 520L, 0x8000000L, 8L, 0x8020200L, 512L, 
        0x20200L, 0x8020000L, 0x8020008L, 0x20208L, 0x8000208L, 0x20200L, 0x20000L, 0x8000208L, 8L, 0x8020208L, 
        512L, 0x8000000L, 0x8020200L, 0x8000000L, 0x20008L, 520L, 0x20000L, 0x8020200L, 0x8000200L, 0L, 
        512L, 0x20008L, 0x8020208L, 0x8000200L, 0x8000008L, 512L, 0L, 0x8020008L, 0x8000208L, 0x20000L, 
        0x8000000L, 0x8020208L, 8L, 0x20208L, 0x20200L, 0x8000008L, 0x8020000L, 0x8000208L, 520L, 0x8020000L, 
        0x20208L, 8L, 0x8020008L, 0x20200L
    };
    static long SP4[] = {
        0x802001L, 8321L, 8321L, 128L, 0x802080L, 0x800081L, 0x800001L, 8193L, 0L, 0x802000L, 
        0x802000L, 0x802081L, 129L, 0L, 0x800080L, 0x800001L, 1L, 8192L, 0x800000L, 0x802001L, 
        128L, 0x800000L, 8193L, 8320L, 0x800081L, 1L, 8320L, 0x800080L, 8192L, 0x802080L, 
        0x802081L, 129L, 0x800080L, 0x800001L, 0x802000L, 0x802081L, 129L, 0L, 0L, 0x802000L, 
        8320L, 0x800080L, 0x800081L, 1L, 0x802001L, 8321L, 8321L, 128L, 0x802081L, 129L, 
        1L, 8192L, 0x800001L, 8193L, 0x802080L, 0x800081L, 8193L, 8320L, 0x800000L, 0x802001L, 
        128L, 0x800000L, 8192L, 0x802080L
    };
    static long SP5[] = {
        256L, 0x2080100L, 0x2080000L, 0x42000100L, 0x80000L, 256L, 0x40000000L, 0x2080000L, 0x40080100L, 0x80000L, 
        0x2000100L, 0x40080100L, 0x42000100L, 0x42080000L, 0x80100L, 0x40000000L, 0x2000000L, 0x40080000L, 0x40080000L, 0L, 
        0x40000100L, 0x42080100L, 0x42080100L, 0x2000100L, 0x42080000L, 0x40000100L, 0L, 0x42000000L, 0x2080100L, 0x2000000L, 
        0x42000000L, 0x80100L, 0x80000L, 0x42000100L, 256L, 0x2000000L, 0x40000000L, 0x2080000L, 0x42000100L, 0x40080100L, 
        0x2000100L, 0x40000000L, 0x42080000L, 0x2080100L, 0x40080100L, 256L, 0x2000000L, 0x42080000L, 0x42080100L, 0x80100L, 
        0x42000000L, 0x42080100L, 0x2080000L, 0L, 0x40080000L, 0x42000000L, 0x80100L, 0x2000100L, 0x40000100L, 0x80000L, 
        0L, 0x40080000L, 0x2080100L, 0x40000100L
    };
    static long SP6[] = {
        0x20000010L, 0x20400000L, 16384L, 0x20404010L, 0x20400000L, 16L, 0x20404010L, 0x400000L, 0x20004000L, 0x404010L, 
        0x400000L, 0x20000010L, 0x400010L, 0x20004000L, 0x20000000L, 16400L, 0L, 0x400010L, 0x20004010L, 16384L, 
        0x404000L, 0x20004010L, 16L, 0x20400010L, 0x20400010L, 0L, 0x404010L, 0x20404000L, 16400L, 0x404000L, 
        0x20404000L, 0x20000000L, 0x20004000L, 16L, 0x20400010L, 0x404000L, 0x20404010L, 0x400000L, 16400L, 0x20000010L, 
        0x400000L, 0x20004000L, 0x20000000L, 16400L, 0x20000010L, 0x20404010L, 0x404000L, 0x20400000L, 0x404010L, 0x20404000L, 
        0L, 0x20400010L, 16L, 16384L, 0x20400000L, 0x404010L, 16384L, 0x400010L, 0x20004010L, 0L, 
        0x20404000L, 0x20000000L, 0x400010L, 0x20004010L
    };
    static long SP7[] = {
        0x200000L, 0x4200002L, 0x4000802L, 0L, 2048L, 0x4000802L, 0x200802L, 0x4200800L, 0x4200802L, 0x200000L, 
        0L, 0x4000002L, 2L, 0x4000000L, 0x4200002L, 2050L, 0x4000800L, 0x200802L, 0x200002L, 0x4000800L, 
        0x4000002L, 0x4200000L, 0x4200800L, 0x200002L, 0x4200000L, 2048L, 2050L, 0x4200802L, 0x200800L, 2L, 
        0x4000000L, 0x200800L, 0x4000000L, 0x200800L, 0x200000L, 0x4000802L, 0x4000802L, 0x4200002L, 0x4200002L, 2L, 
        0x200002L, 0x4000000L, 0x4000800L, 0x200000L, 0x4200800L, 2050L, 0x200802L, 0x4200800L, 2050L, 0x4000002L, 
        0x4200802L, 0x4200000L, 0x200800L, 0L, 2L, 0x4200802L, 0L, 0x200802L, 0x4200000L, 2048L, 
        0x4000002L, 0x4000800L, 2048L, 0x200002L
    };
    static long SP8[] = {
        0x10001040L, 4096L, 0x40000L, 0x10041040L, 0x10000000L, 0x10001040L, 64L, 0x10000000L, 0x40040L, 0x10040000L, 
        0x10041040L, 0x41000L, 0x10041000L, 0x41040L, 4096L, 64L, 0x10040000L, 0x10000040L, 0x10001000L, 4160L, 
        0x41000L, 0x40040L, 0x10040040L, 0x10041000L, 4160L, 0L, 0L, 0x10040040L, 0x10000040L, 0x10001000L, 
        0x41040L, 0x40000L, 0x41040L, 0x40000L, 0x10041000L, 4096L, 64L, 0x10040040L, 4096L, 0x41040L, 
        0x10001000L, 64L, 0x10000040L, 0x10040000L, 0x10040040L, 0x10000000L, 0x40000L, 0x10001040L, 0L, 0x10041040L, 
        0x40040L, 0x10000040L, 0x10040000L, 0x10001000L, 0x10001040L, 0L, 0x10041040L, 0x41000L, 0x41000L, 4160L, 
        4160L, 0x40040L, 0x10000000L, 0x10041000L
    };

}
