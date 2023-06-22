import java.io.*;

public class MathExpression {

    private double x;
    private double y;

    public MathExpression(double x) {
        this.x = x;
        calculate();
    }
    public void calculate() {
        y = x - Math.sin(x
        );
    }

    public void saveState() {
        try {
            FileWriter writer = new FileWriter("state.txt");
            writer.write(Double.toString(x) + "\n");
            writer.write(Double.toString(y));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadState() {
        try {
            BufferedReader read = new BufferedReader(new FileReader("state.txt"));
            x = Double.parseDouble(read.readLine());
            y = Double.parseDouble(read.readLine());
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        MathExpression ex = null;
        boolean flag = true;
        while (flag) {
            System.out.println("Введите x или команду (save/upload/exit):");
            String input = reader.readLine();
            switch (input) {
                case "save":
                    if (ex != null) {
                        ex.saveState();
                        System.out.println("Состояние сохранено");
                    } else {
                        System.out.println("Нечего сохранять");
                    }
                    break;
                case "upload":
                    if (ex != null) {
                        ex.uploadState();
                        ex.calculate();
                        System.out.println("Состояние загружено. Результат: y = " + ex.getY());
                    } else {
                        System.out.println("Нечего загружать");
                    }
                    break;
                case "exit":
                    flag = false;
                    break;
                default:
                    try {
                        double x = Double.parseDouble(input);
                        ex = new MathExpression(x);
                        System.out.println("y = " + ex.getY());
                    } catch (NumberFormatException e) {
                        System.out.println("Некорректный ввод");
                    }
            }
        }
    }

    public double getY() {
        return y;
    }
}
