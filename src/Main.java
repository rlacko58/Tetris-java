import score.HighScore;

public class Main {
    private static void printTetronimo(boolean[][] t){
        for(int i=0; i<t.length; i++){
            for(int j=0; j<t.length; j++){
                System.out.print(t[i][j] ? 1 : 0);
            }
            System.out.print('\n');
        }
        System.out.println('\n');
    }

    public static void main(String[] args) {
    }
}
