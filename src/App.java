import parser.Automata;

public class App {
    public static void main(String[] args) { Application.run(args); }
}

class Application {
    public static void run(String[] args)
    {
        var auto = new Automata();
        auto.run();
    }
}
