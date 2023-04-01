import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class stakMain {
    private int[] items;
    private int top;
    private Stack<Integer> minStack;
    private Stack<Integer> lengthStack;

    public stakMain() {
        items = new int[1_000_000];
        minStack = new Stack<>();
        lengthStack = new Stack<>();
    }

    public int pop() throws StackException {
        if (isEmpty())
            throw new StackException();
        int value = items[--top];
        if (value == minStack.peek()) {
            minStack.pop();
        }
        lengthStack.pop();
        return value;
    }

    public void push(int a) throws StackException {
        if (isFull())
            throw new StackException();
        items[top++] = a;
        if (minStack.isEmpty() || a <= minStack.peek()) {
            minStack.push(a);
        }
        lengthStack.push(a);
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
        int shortestLength = Integer.MAX_VALUE;
        int shortestLineNumber = -1;
        try {
            File file = new File(args[0]);
            Scanner scanner = new Scanner(file);
            String line;
            int lineNumber = 1;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine().trim();
                int length = line.length();
                try {
                    int value = Integer.parseInt(line.length() + "");
                    stack.push(value);
                    if (length < shortestLength) {
                        shortestLength = length;
                        shortestLineNumber = lineNumber;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (stak.StackException e) {
                    throw new RuntimeException(e);
                }
                lineNumber++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Stack contents: ");
        while (!stack.isEmpty()) {
            int value;
            try {
                value = stack.pop();
                System.out.println(value);
            } catch (stak.StackException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Shortest line number: " + shortestLineNumber + ", length: " + shortestLength);
    }
}