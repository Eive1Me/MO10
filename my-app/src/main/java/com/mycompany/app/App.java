package com.mycompany.app;

/**
 * Hello world!
 *
 */
public class App 
{
    static final double n = 2.0; //Кол-во потоков
    static final double λ = 4.0; //Поток покупателей
    static final double tService = 5.0; //Время обслуживания (минуты)
    static final double m = 6.0; //Длина очереди
    static final double μ = 1.0/tService; //Интенсивность обслуживания
    static final double ρ = (double) λ/μ; //Показатель нагрузки системы
    public static void main( String[] args )
    {
        double p0 = 0.0, pDec = 0.0, pQ = 0.0, lQ = 0.0, tQ = 0.0, tSys = 0.0, kBusy = 0.0, kFree = 0.0, q = 0.0, a = 0.0, nBusy = 0.0, nFree = 0.0;
        System.out.println(ρ/n);
        if (Double.compare(ρ/n, 1.0) == 0) {
            p0 = calc1P0();
            lQ = calc1LQ(p0);
        } else {
            p0 = calcP0();
            lQ = calcLQ(p0);
        }
        pQ = calcPQ(p0);
        pDec = calcPN(p0);
        tQ = (double) lQ / λ;
        q = 1.0 - pDec;
        a = (double) λ * q;
        nBusy = (double) ρ * q;
        nFree = (double) n - nBusy;
        tSys = tQ + (double) q / μ;
        kBusy = (double) nBusy / nFree;
        kFree = (double) 1 - kBusy;
        System.out.println("Вероятность простоя системы: ");
        System.out.printf("P0 = %.7f\n", p0);
        System.out.println("Вероятность образования очереди: ");
        System.out.printf("Pоч = %.7f\n", pQ);
        System.out.println("Вероятность отказа: ");
        System.out.printf("Pотк = %.7f\n", pDec);
        System.out.println("Относительная пропускная способность системы (вероятность обслуживания): ");
        System.out.printf("Q = Pобсл = %.7f\n", q);
        System.out.println("Абсолютная пропускная способность системы: ");
        System.out.printf("A = %.7f\n", a);
        System.out.println("Среднее число занятых каналов: ");
        System.out.printf("nз = %.7f\n", nBusy);
        System.out.println("Среднее число каналов простоя: ");
        System.out.printf("nпр = %.7f\n", nFree);
        System.out.println("Среднее число заявок в очереди: ");
        System.out.printf("Lоч = %.7f\n", lQ);
        System.out.println("Среднее время ожидании в очереди: ");
        System.out.printf("Tоч = %.7f\n", tQ);
        System.out.println("Среднее время пребывания в системе: ");
        System.out.printf("Tсмо = %.7f\n", tSys);
        System.out.println("Коэфицент занятости: ");
        System.out.printf("Kз = %.7f\n", kBusy);
        System.out.println("Коэфицент простоя: ");
        System.out.printf("Kпр = %.7f\n", kFree);
    }

    public static double calcP0(){
        double res = 0.0;
        for (double i = 0.0; i < n; i = i + 1.0) {
            res += (double) ((double) Math.pow(ρ, i)/factorialUsingForLoop((int) i)) + ((double) Math.pow(ρ, n + 1)/((double) factorialUsingForLoop((int) n) * (n - ρ)))*(1.0 - Math.pow(ρ / n, m));
        }
        res = 1.0 / res;
        return res;
    }

    public static double calc1P0(){
        double res = 1.0;
        for (double i = 1.0; i <= n; i = i + 1.0) {
            res += (double) (((double) Math.pow(ρ, i)) / ((double) factorialUsingForLoop((int) i)));
        }
        res += (double) (((double) m * Math.pow(ρ, n + 1)) / ((double) n * factorialUsingForLoop((int) n)));
        res = (Double) 1.0 / res;
        return res;
    }

    public static double factorialUsingForLoop(int n) {
        double fact = 1;
        for (double i = 2; i <= n; i = i + 1.0) {
            fact = fact * i;
        }
        return fact;
    }

    public static double calcPQ(double p0){
        double res = (double) p0 * ((double) Math.pow(ρ, n) / (double) factorialUsingForLoop((int) n)) * ((1.0 - (double) Math.pow(ρ / n, m)) / (1.0 - ρ / n));
        return res;
    }

    public static double calcPN(double p0){
        double res = 0.0;
        res = (double) (p0 * (((double) Math.pow(ρ, n + m)) / ((double) Math.pow(n, m) * factorialUsingForLoop((int) n))));
        return res;
    }

    public static double calcLQ(double p0){
        double res = 0.0;
        res = (double) (p0 * ((double) Math.pow(ρ, n + 1)/(factorialUsingForLoop((int) n) * n)) * ((1 - Math.pow(ρ / n, m) * (m + 1 - m * ρ / n))/Math.pow(1 - ρ / n, 2)));
        return res;
    }

    public static double calc1LQ(double p0){
        double res = 0.0;
        res = (double) ((double) Math.pow(ρ, n + 1) / (factorialUsingForLoop((int) n) * n)) * ((m * (m + 1)) / 2.0) * p0;
        return res;
    }
}
