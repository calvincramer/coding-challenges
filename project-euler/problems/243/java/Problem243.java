package problem243;

import mathtools.MF;

public class Problem243 {

    public static void main(String[] args) {
        double frac = 15499 / 94744;
        long minN;
        double minRes = Double.MAX_VALUE;
        for (long n = 2; ; n++) {
            double resilience = MF.eulersTotientFunction(n) * 1.0 / (n-1);
            if (resilience < minRes) {
                minRes = resilience;
                minN = n;
                System.out.println("n = " + minN + "\tres = " + minRes + " = " + MF.eulersTotientFunction(n) + " / " + (n-1));
            }
            if (resilience < frac) {
                System.out.println("WINNER! n = " + n + "\t");
            }
        }
        
    }

}

/*
n = 2	res = 1.0 = 1 / 1
n = 4	res = 0.6666666666666666 = 2 / 3
n = 6	res = 0.4 = 2 / 5
n = 12	res = 0.36363636363636365 = 4 / 11
n = 18	res = 0.35294117647058826 = 6 / 17
n = 24	res = 0.34782608695652173 = 8 / 23
n = 30	res = 0.27586206896551724 = 8 / 29
n = 60	res = 0.2711864406779661 = 16 / 59
n = 90	res = 0.2696629213483146 = 24 / 89
n = 120	res = 0.2689075630252101 = 32 / 119
n = 150	res = 0.2684563758389262 = 40 / 149
n = 180	res = 0.2681564245810056 = 48 / 179
n = 210	res = 0.22966507177033493 = 48 / 209
n = 420	res = 0.22911694510739858 = 96 / 419
n = 630	res = 0.2289348171701113 = 144 / 629
n = 840	res = 0.22884386174016685 = 192 / 839
n = 1050	res = 0.22878932316491898 = 240 / 1049
n = 1260	res = 0.22875297855440826 = 288 / 1259
n = 1470	res = 0.22872702518720217 = 336 / 1469
n = 1680	res = 0.22870756402620607 = 384 / 1679
n = 1890	res = 0.22869242985706723 = 432 / 1889
n = 2100	res = 0.2286803239637923 = 480 / 2099
n = 2310	res = 0.2078822000866176 = 480 / 2309
n = 4620	res = 0.20783719419787833 = 960 / 4619
n = 6930	res = 0.2078221965651609 = 1440 / 6929
n = 9240	res = 0.20781469856045026 = 1920 / 9239
n = 11550	res = 0.2078102000173175 = 2400 / 11549
n = 13860	res = 0.20780720109676024 = 2880 / 13859
n = 16170	res = 0.2078050590636403 = 3360 / 16169
n = 18480	res = 0.20780345256777963 = 3840 / 18479
n = 20790	res = 0.20780220308817163 = 4320 / 20789
n = 23100	res = 0.2078012035153037 = 4800 / 23099
n = 25410	res = 0.2078003856901098 = 5280 / 25409
n = 27720	res = 0.20779970417403226 = 5760 / 27719
n = 30030	res = 0.19181457924006792 = 5760 / 30029
n = 60060	res = 0.19181138547095355 = 11520 / 60059
n = 90090	res = 0.19181032090488295 = 17280 / 90089
n = 120120	res = 0.19180978862627893 = 23040 / 120119
n = 150150	res = 0.19180946926053455 = 28800 / 150149
n = 180180	res = 0.1918092563506291 = 34560 / 180179
n = 210210	res = 0.1918091042724146 = 40320 / 210209
n = 240240	res = 0.19180899021391198 = 46080 / 240239
n = 270270	res = 0.19180890150183705 = 51840 / 270269
n = 300300	res = 0.1918088305322362 = 57600 / 300299
n = 330330	res = 0.1918087724662382 = 63360 / 330329
n = 360360	res = 0.1918087240779334 = 69120 / 360359
n = 390390	res = 0.19180868313400223 = 74880 / 390389
n = 420420	res = 0.19180864803921802 = 80640 / 420419
n = 450450	res = 0.19180861762374876 = 86400 / 450449
n = 480480	res = 0.19180859101022105 = 92160 / 480479
n = 510510	res = 0.18052571061430847 = 92160 / 510509
n = 1021020	res = 0.18052553380495368 = 184320 / 1021019
n = 1531530	res = 0.18052547486857906 = 276480 / 1531529
*/
/*
n = 2	res = 1.0 = 1 / 1
n = 4	res = 0.6666666666666666 = 2 / 3
n = 6	res = 0.4 = 2 / 5
n = 12	res = 0.36363636363636365 = 4 / 11
n = 18	res = 0.35294117647058826 = 6 / 17
n = 24	res = 0.34782608695652173 = 8 / 23
n = 30	res = 0.27586206896551724 = 8 / 29
n = 60	res = 0.2711864406779661 = 16 / 59
n = 90	res = 0.2696629213483146 = 24 / 89
n = 120	res = 0.2689075630252101 = 32 / 119
n = 150	res = 0.2684563758389262 = 40 / 149
n = 180	res = 0.2681564245810056 = 48 / 179
n = 210	res = 0.22966507177033493 = 48 / 209
n = 420	res = 0.22911694510739858 = 96 / 419
n = 630	res = 0.2289348171701113 = 144 / 629
n = 840	res = 0.22884386174016685 = 192 / 839
n = 1050	res = 0.22878932316491898 = 240 / 1049
n = 1260	res = 0.22875297855440826 = 288 / 1259
n = 1470	res = 0.22872702518720217 = 336 / 1469
n = 1680	res = 0.22870756402620607 = 384 / 1679
n = 1890	res = 0.22869242985706723 = 432 / 1889
n = 2100	res = 0.2286803239637923 = 480 / 2099
n = 2310	res = 0.2078822000866176 = 480 / 2309
n = 4620	res = 0.20783719419787833 = 960 / 4619
n = 6930	res = 0.2078221965651609 = 1440 / 6929
n = 9240	res = 0.20781469856045026 = 1920 / 9239
n = 11550	res = 0.2078102000173175 = 2400 / 11549
n = 13860	res = 0.20780720109676024 = 2880 / 13859
n = 16170	res = 0.2078050590636403 = 3360 / 16169
n = 18480	res = 0.20780345256777963 = 3840 / 18479
n = 20790	res = 0.20780220308817163 = 4320 / 20789
n = 23100	res = 0.2078012035153037 = 4800 / 23099
n = 25410	res = 0.2078003856901098 = 5280 / 25409
n = 27720	res = 0.20779970417403226 = 5760 / 27719
n = 30030	res = 0.19181457924006792 = 5760 / 30029
n = 60060	res = 0.19181138547095355 = 11520 / 60059
n = 90090	res = 0.19181032090488295 = 17280 / 90089
n = 120120	res = 0.19180978862627893 = 23040 / 120119
n = 150150	res = 0.19180946926053455 = 28800 / 150149
n = 180180	res = 0.1918092563506291 = 34560 / 180179
n = 210210	res = 0.1918091042724146 = 40320 / 210209
n = 240240	res = 0.19180899021391198 = 46080 / 240239
n = 270270	res = 0.19180890150183705 = 51840 / 270269
n = 300300	res = 0.1918088305322362 = 57600 / 300299
n = 330330	res = 0.1918087724662382 = 63360 / 330329
n = 360360	res = 0.1918087240779334 = 69120 / 360359
n = 390390	res = 0.19180868313400223 = 74880 / 390389
n = 420420	res = 0.19180864803921802 = 80640 / 420419
n = 450450	res = 0.19180861762374876 = 86400 / 450449
n = 480480	res = 0.19180859101022105 = 92160 / 480479
n = 510510	res = 0.18052571061430847 = 92160 / 510509
n = 1021020	res = 0.18052553380495368 = 184320 / 1021019
n = 1531530	res = 0.18052547486857906 = 276480 / 1531529
n = 2042040	res = 0.18052544540040616 = 368640 / 2042039
n = 2552550	res = 0.18052542771950705 = 460800 / 2552549
n = 3063060	res = 0.1805254159322429 = 552960 / 3063059
n = 3573570	res = 0.18052540751276944 = 645120 / 3573569
n = 4084080	res = 0.18052540119816488 = 737280 / 4084079
n = 4594590	res = 0.18052539628680606 = 829440 / 4594589
n = 5105100	res = 0.18052539235771922 = 921600 / 5105099
n = 5615610	res = 0.18052538914301192 = 1013760 / 5615609
n = 6126120	res = 0.18052538646408925 = 1105920 / 6126119
n = 6636630	res = 0.1805253841973086 = 1198080 / 6636629
n = 7147140	res = 0.1805253822543538 = 1290240 / 7147139
n = 7657650	res = 0.1805253805704597 = 1382400 / 7657649
n = 8168160	res = 0.18052537909705235 = 1474560 / 8168159
n = 8678670	res = 0.18052537779698707 = 1566720 / 8678669
n = 9189180	res = 0.18052537664137353 = 1658880 / 9189179
n = 9699690	res = 0.1710240400491191 = 1658880 / 9699689
n = 19399380	res = 0.17102403123316473 = 3317760 / 19399379
n = 29099070	res = 0.17102402829451346 = 4976640 / 29099069
n = 38798760	res = 0.17102402682518789 = 6635520 / 38798759
n = 48498450	res = 0.17102402594359253 = 8294400 / 48498449
n = 58198140	res = 0.17102402535586234 = 9953280 / 58198139
n = 67897830	res = 0.17102402493605504 = 11612160 / 67897829
n = 77597520	res = 0.17102402462119956 = 13271040 / 77597519
n = 87297210	res = 0.17102402437631195 = 14929920 / 87297209
n = 96996900	res = 0.17102402418040188 = 16588800 / 96996899
n = 106696590	res = 0.17102402402011183 = 18247680 / 106696589
n = 116396280	res = 0.17102402388653679 = 19906560 / 116396279
n = 126095970	res = 0.17102402377351175 = 21565440 / 126095969
n = 135795660	res = 0.17102402367663314 = 23224320 / 135795659
n = 145495350	res = 0.1710240235926717 = 24883200 / 145495349
n = 155195040	res = 0.1710240235192054 = 26542080 / 155195039
*/