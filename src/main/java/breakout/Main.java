package breakout;

import breakout.assembly.Launcher;
import breakout.screens.tags.Screen;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Launcher.routes(new ArrayList<>(
            Arrays.asList(Screen.MENU, Screen.ARENA, Screen.EPILOGUE)
        ));

        Launcher.launch(args);

//        Window.launch(args);
    }
}
