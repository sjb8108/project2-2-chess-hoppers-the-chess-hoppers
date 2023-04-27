package puzzles.hoppers.ptui;

import puzzles.common.Observer;
import puzzles.hoppers.model.HoppersModel;
import puzzles.chess.ptui.ChessPTUI;

import java.io.IOException;
import java.util.Scanner;

public class HoppersPTUI implements Observer<HoppersModel, String> {
    private HoppersModel model;

    public void init(String filename) throws IOException {
        this.model = new HoppersModel(filename);
        System.out.println("Loaded :" + filename);
        System.out.println(this.model.getCurrentConfig().toString());
        this.model.addObserver(this);
        displayHelp();
    }

    @Override
    public void update(HoppersModel model, String data) {
        if (data.equals("Complete")) {
            System.out.println("You completed the puzzle!");
            System.out.println(model.getCurrentConfig().toString());
        } else if (data.equals("new game")) {

        } else {
            System.out.println(data);
            System.out.println(model.getCurrentConfig().toString());
        }
    }

    private void displayHelp() {
        System.out.println( "h(int)              -- hint next move" );
        System.out.println( "l(oad) filename     -- load new puzzle file" );
        System.out.println( "s(elect) r c        -- select cell at r, c" );
        System.out.println( "q(uit)              -- quit the game" );
        System.out.println( "r(eset)             -- reset the current game" );
    }

    public void run() {
        Scanner in = new Scanner( System.in );
        while (true) {
            System.out.print( "> " );
            String line = in.nextLine();
                if (line.startsWith( "q" )) {
                    break;
                } else if (line.startsWith( "h" )) {
                    this.model.hint();
                } else if (line.startsWith( "l" )) {
                    String[] loading = line.split(" ");
                    this.model.load(loading[1]);
                } else if (line.startsWith( "s" )) {
                    String[] args = line.split(" ");
                    int row = Integer.parseInt(args[1]);
                    int col = Integer.parseInt(args[2]);
                    this.model.selectPiece(row, col);
                } else if (line.startsWith( "r" )) {
                    this.model.reset();
                } else {
                    System.out.println();
                    displayHelp();
                }
            }
        }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java HoppersPTUI filename");
        } else {
            try {
                HoppersPTUI ptui = new HoppersPTUI();
                ptui.init(args[0]);
                ptui.run();
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
    }
}
