import java.util.Scanner;
public class stak {
    private int[] items;
    private int top;
    public stak() {
        items = new int[1_000_000];
    }
    public int pop() throws StackException {
        if (isEmpty())
            throw new StackException();
        return items[--top];
    }
    public void push(int a) throws StackException {
        if (isFull())
            throw new StackException();
        items[top++] = a;
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public boolean isFull() {
        return top == items.length;
    }

    public class StackException extends IllegalAccessException {
    }

    public static void main(String[] args) {
        stak stack = new stak();
        Scanner scanner = new Scanner(System.in);
        String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine().trim();
                try {
                    if (line.startsWith("+")) {
                        int value = Integer.parseInt(line.substring(1).trim());
                        stack.push(value);
                    } else if (line.equals("-")) {
                        int value = stack.pop();
                        System.out.println(value);
                    }
                } catch (StackException | NumberFormatException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
    }
}