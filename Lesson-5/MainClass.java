/**
 * Java. Level 2; Lesson 4; HomeWork 4
 *
 * @coauthor: Раков Валерий;
 * @version dated: 31 июля 2019 года
 */

public class MainClass {

    static final int size = 10000000;
    static final int h = size / 2;
    static float[] arr = new float[size];

    public static void main(String[] args){    //основной поток
        nopotok();
        potok();
    }

    public static void nopotok() {

        for (int i=1;i<size;i++){
            arr[i]=1;
        }
        long a = System.currentTimeMillis();
        for (int i=1;i<size;i++){
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long b=System.currentTimeMillis();
        System.out.println(b-a);
    }

    public static void potok(){

        float[] a1=new float[h];
        float[] a2=new float[h];
        for (int i=1;i<size;i++){
            arr[i]=1;
        }
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        MyThread mt1 = new MyThread("Child");
        for (int i=1;i<h;i++){
            a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        try {
            mt1.join();   //ожидает завершение потока   mt1
        } catch (InterruptedException ex) {
            System.out.println("Мain thread is interrupted.");
        }
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        long b=System.currentTimeMillis();
        System.out.println(b-a);
    }
}


