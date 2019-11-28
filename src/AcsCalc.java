import java.util.Scanner;

public class AcsCalc {

    /*
    * 直推 1人拿 1 代           50％;
    直推 2人拿 2 代          35％
    直推 3人拿 3 代          20％
    直推 5人拿 4~6代        10％ 
    直推 7人拿 7~10代       7％ 
    直推10人拿11~15代       5％ 
    直推15人拿16~21代      1％ 
    * */
    public static void main(String[] args) {
//        int intE;
        double douA = 0,douB = 0,douC = 0;
        System.out.println("A最高投资:");
        Scanner scannerA = new Scanner(System.in);
        String stringA = scannerA.nextLine();
        System.out.println("B投资:");
        Scanner scannerB = new Scanner(System.in);
        String stringB = scannerB.nextLine();
        System.out.println("A直推人数:");
        Scanner scannerC = new Scanner(System.in);
        String stringC = scannerC.nextLine();
        System.out.println("B为A的第几代：");
        Scanner scannerD = new Scanner(System.in);
        String stringD = scannerD.nextLine();
        System.out.println("B投资玩法收益比例：");
        Scanner scannerE = new Scanner(System.in);
        String stringE = scannerE.nextLine();

        double intA = Integer.parseInt(stringA);
        double intB = Integer.parseInt(stringB);
        double intC = Integer.parseInt(stringC);
        double intD = Integer.parseInt(stringD);
        double intE = Double.parseDouble(stringE);
//        douC = intE;
        if (intA < 1000){
            System.out.println("直推收益烧伤");
            if (intA > intB){
                douB = intB;
            }else {
                douB = intA;
            }
        }else {
            System.out.println("直推收益无烧伤");
            douB = intB;
        }
        if (intD == 1){
            if (intC >= 1){
                System.out.println("直推1代的奖励50%");
                douA = 0.5;
            }
        }else if(intD == 2){
            if (intC >= 2) {
                System.out.println("直推2代的奖励35%");
                douA = 0.35;
            }
        }else if(intD == 3){
            if (intC >= 3) {
                System.out.println("直推3代的奖励20%");
                douA = 0.2;
            }
        }else if(intD >= 4 && intD <= 6){
            if (intC >= 5) {
                System.out.println("直推4-6代的奖励10%");
                douA = 0.1;
            }
        }else if(intD >= 7 && intD <= 10){
            if (intC >= 7) {
                System.out.println("直推7-10代的奖励7%");
                douA = 0.07;
            }
        }else if(intD >= 11 && intD <= 15){
            if (intC >= 10) {
                System.out.println("直推11-15代的奖励5%");
                douA = 0.05;
            }
        }else if (intD >= 16 && intD <= 21){
            if (intC >= 15) {
                System.out.println("直推16-21代的奖励1%");
                douA = 0.01;
            }
        }
        System.out.println("A推荐B的收益："+douB*intE *douA);
    }
}
