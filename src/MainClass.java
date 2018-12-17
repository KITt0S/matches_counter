import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 *  Программа, считающая минимальное количество спичек для создания заданного количества квадратов
 */
public class MainClass {

    public static void main(String[] args) {

        try( BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) ) ) {

            while( true ) {

                System.out.println( "Введите количество квадратов:");
                String text = reader.readLine();
                if( text.equals( "exit" ) ) {

                    break;
                } else {

                    try {

                        int n = Integer.parseInt( text );
                        int matches = countMatches( n );
                        System.out.println( "Количество необходимых спичек: " + matches + "." );
                    } catch ( NumberFormatException e ) {

                        System.out.println( "Ошибка! Неверный формат ввода." );
                    } catch ( MyNumberException e ) {

                        System.out.println( e.toString() );
                    }
                }
            }
        } catch ( IOException e ) {

            e.printStackTrace();
        }
    }

    public static int countMatches( int n ) throws MyNumberException { // метод, считающий общее количество спичек

        if( n >= 1 &&  n <= 1000000000 ) {

            int innerSquareWidth = getInnerSquareWidth( n );
            int matchesInInnerSquare = countMatchesInSquare( innerSquareWidth );
            int restOfSquares = ( int )( n - Math.pow( innerSquareWidth, 2 ) );
            int outerLayerWidth = innerSquareWidth + 1;
            int matchesInOuterLayer = countMatchesInOuterLayer( restOfSquares, outerLayerWidth );
            return matchesInInnerSquare + matchesInOuterLayer;
        } else{

            throw new MyNumberException();
        }
    }

    public static int getInnerSquareWidth( int n ) { // метод, определяющий ширину внутреннего квадрата

        return  ( int ) Math.floor( Math.sqrt( n ) );
    }

    public static int countMatchesInSquare( int squareWidth ) { // метод, считающий количество спичек в квадрате заданной ширины

        if( squareWidth == 1 ) {

            return 4;
        } else if( squareWidth > 1) {

            return 4 + 2 * 3 * ( squareWidth - 1 ) + 2 * ( squareWidth - 2 + squareWidth - 1 );
        }
        return 0;
    }

    public static int countMatchesInOuterLayer( int squaresInLayer, int layerWidth ) { // метод, считающий количество спичек во внешнем слое

        int countMatches = 0;
        if( squaresInLayer > 0 ) {

            if( squaresInLayer < layerWidth ) {

                countMatches = 3 + 2 * ( squaresInLayer - 1 );
            } else if( squaresInLayer >= layerWidth ) {

                countMatches = 2 * 3 + 2 * ( squaresInLayer - 2 );
            }
            return countMatches;
        }
        return countMatches;
    }
}
