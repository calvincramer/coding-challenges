package problem078;

import java.util.ArrayList;
import java.util.List;
import mathtools.MF;

public class Problem078 {

    static int TARGET = 5;
    static long ways = 0;
    static long[] waysPerLength;
    
    public static void main(String[] args) {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        final int div = 1000000;
        int n = 1;
        long res = p(n);
        while ( !(res % div == 0)) {
            n++;
            MF.startTimer();
            res = p(n);
            //System.out.println(n + ":\t" + res + "\t\t" + MF.getElapsedSeconds() + "s");
            for (int i = 1; i < waysPerLength.length; i++) {
                //System.out.print("("+i+","+waysPerLength[i] + "), ");
                System.out.print(waysPerLength[i] + " \t");
            }
            System.out.println("");
        }
        
        System.out.println("winner winner");
        System.out.println("p("+n + ") is divisible by " + div);
        System.out.println("p("+n+") is " + res);
        System.out.println(res + "/" + div + " = " + (res * 1.0 / div));
            
        
    }
    
    public static long p(int n) {
        TARGET = n;
        ways = 0;
        waysPerLength = new long[n+1]; //0 for 0 piles, 1 for 1 pile, n  for n piles
        permute(0, TARGET, new ArrayList<Integer>());
        return ways;
    }

    public static void permute(int sum, int lastNumber, List<Integer> list) {
        
        for (int i = 1; i <= lastNumber; i++) {
            if (sum + i > TARGET)
                return;
            
            list.add(i);
            
            if (sum + i == TARGET) {
                ways++;
                waysPerLength[list.size()] = waysPerLength[list.size()] + 1;
                //printList(list);
            }

            permute(sum+i, i, list);
            
            list.remove(list.size()-1);
        }
    }
    
    public static void permuteWithoutList(int sum, int lastNumber) {
        
        for (int i = 1; i <= lastNumber; i++) {
            if (sum + i > TARGET)
                return;
            
            if (sum + i == TARGET)
                ways++;

            permuteWithoutList(sum+i, i);
        }
    }
    
    public static void printList(List<Integer> list) {
        for (Integer i : list)
            System.out.print(i + " ");
        System.out.println();
    }
}
/*
2:	2		1.00861E-4s
3:	3		4.678E-6s
4:	5		4.678E-6s
5:	7		4.385E-6s
6:	11		5.555E-6s
7:	15		6.724E-6s
8:	22		9.355E-6s
9:	30		1.2279E-5s
10:	42		1.5202E-5s
11:	56		1.7833E-5s
12:	77		1.9587E-5s
13:	101		8.479E-6s
14:	135		9.94E-6s
15:	176		9.647E-6s
16:	231		1.1401E-5s
17:	297		1.3448E-5s
18:	385		2.6312E-5s
19:	490		2.7189E-5s
20:	627		2.6312E-5s
21:	792		3.0697E-5s
22:	1002		3.9175E-5s
23:	1255		4.8238E-5s
24:	1575		6.0517E-5s
25:	1958		7.7765E-5s
26:	2436		1.23665E-4s
27:	3010		1.38866E-4s
28:	3718		1.48222E-4s
29:	4565		1.37405E-4s
30:	5604		8.7413E-5s
31:	6842		1.06708E-4s
32:	8349		1.30389E-4s
33:	10143		1.59039E-4s
34:	12310		1.96752E-4s
35:	14883		2.47329E-4s
36:	17977		2.92058E-4s
37:	21637		3.49359E-4s
38:	26015		4.24494E-4s
39:	31185		5.16584E-4s
40:	37338		6.18614E-4s
41:	44583		7.46956E-4s
42:	53174		8.96348E-4s
43:	63261		0.001078774s
44:	75175		0.001330196s
45:	89134		0.001545366s
46:	105558		0.001844149s
47:	124754		0.002288522s
48:	147273		0.002618878s
49:	173525		0.003171713s
50:	204226		0.003741505s
51:	239943		0.004489046s
52:	281589		0.005196827s
53:	329931		0.006205729s
54:	386155		0.007260238s
55:	451276		0.00854512s
56:	526823		0.010068267s
57:	614154		0.011751038s
58:	715220		0.013965302s
59:	831820		0.016375733s
60:	966467		0.018931464s
61:	1121505		0.022289112s
62:	1300156		0.025819539s
63:	1505499		0.030239882s
64:	1741630		0.0353759s
65:	2012558		0.041164152s
66:	2323520		0.048064214s
67:	2679689		0.055318898s
68:	3087735		0.064096143s
69:	3554345		0.074323449s
70:	4087968		0.086611242s
71:	4697205		0.102778531s
72:	5392783		0.115946007s
73:	6185689		0.134297447s
74:	7089500		0.154307686s
75:	8118264		0.17678858s
76:	9289091		0.20363164s
77:	10619863		0.233011426s
78:	12132164		0.268272433s
79:	13848650		0.307631026s
80:	15796476		0.352300464s
81:	18004327		0.404858109s
82:	20506255		0.46278536s
83:	23338469		0.530290605s
84:	26543660		0.605520049s
85:	30167357		0.690880616s
86:	34262962		0.789456897s
87:	38887673		0.910288378s
88:	44108109		1.033748676s
89:	49995925		1.171627128s
90:	56634173		1.332524399s
91:	64112359		1.517302047s
92:	72533807		1.723081587s
93:	82010177		1.962960624s
94:	92669720		2.224487064s
95:	104651419		2.521931129s
96:	118114304		2.85747668s
97:	133230930		3.24030353s
98:	150198136		3.668376627s
99:	169229875		4.22135014s
100:	190569292		4.706342169s
101:	214481126		5.344257896s
102:	241265379		6.05060328s
103:	271248950		6.788746193s
104:	304801365		7.659019356s
105:	342325709		8.788856994s
106:	384276336		9.88507722s
107:	431149389		10.969088295s
108:	483502844		12.376187273s
109:	541946240		13.924146972s
110:	607163746		15.934306744s
111:	679903203		17.580284043s
112:	761002156		19.841162925s
113:	851376628		22.312318286s
114:	952050665		26.062635267s
115:	1064144451		28.409254771s
*/