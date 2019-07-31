class MyThread extends Thread {

    //Создать новый поток
    MyThread(String name) {
        super(name);
        start();//сразу стартуем поток из конструктора
    }

    //Стартуем новый поток
    public void run() {
        System.out.println(getName() + " is starting...");
        final int size = 10000000;
        final int h = size / 2;
        float[] a2=new float[h];
        for (int i = 1; i < h; i++) {
            a2[i] = (float)(a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}
