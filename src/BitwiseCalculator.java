import java.util.Scanner;

public class BitwiseCalculator {

    public static void main(String[] args) {
        int result = 0;
        int tmp = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj pierwsza liczbe");
        int val1 = scanner.nextInt();
        System.out.println("Podaj druga liczbe");
        int val2 = scanner.nextInt();
        if (val1 == 0 && val2 == 0)
            System.out.println("Zakończono działanie programu");
        else {
            System.out.println("Podaj operacje");
            char val3 = scanner.next().charAt(0);

            System.out.print(val1 + " - ");
            for (int i = 31; i >= 0; i--) {
                System.out.print((val1 >> i) & 1);
            }
            System.out.println();
            System.out.print(val2 + " - ");
            for (int i = 31; i >= 0; i--) {
                System.out.print((val2 >> i) & 1);
            }
            System.out.println();
            switch (val3) {
                case '+':
                    while (val2 != 0) {
                        tmp = val1 & val2;// szukam wartosci ktora bedziemy przenosic (dodatkowa wartosc przy dodawaniu pisemnym)
                        val1 ^= val2; // "dodawanie"
                        val2 = tmp << 1; //wartosc ktora bedziemy przenosic ustawiamy na wlasciwej pozycji
                    }
                    result = val1;
                    System.out.print(result + " - ");
                    for (int i = 31; i >= 0; i--) {
                        System.out.print((result >> i) & 1);
                    }
                    break;
                case '-':
                    while (val2 != 0) {
                        tmp = (~val1) & val2; //analogicznie do dodawania, natomiast dzieki negacji zamieniamy na odejmowanie
                        val1 ^= val2;
                        val2 = tmp << 1;
                    }
                    result = val1;
                    System.out.print(result + " - ");
                    for (int i = 31; i >= 0; i--) {
                        System.out.print((result >> i) & 1);
                    }
                    break;
                case '*':
                    while (val2 > 0) {
                        int val1tmp = val1;
                        if ((val2 & 1) != 0) {// niepatrzyste
                            while (val1tmp != 0) {// dodawanie pierwszej wartosci do wyniku
                                tmp = result & val1tmp;
                                result ^= val1tmp;
                                val1tmp = tmp << 1;
                            }// z wykorzystaniem zmiennej val1tmp zeby uniknac kolizji wartosci
                        }// metoda chlopow rosyjskich
                        val1 <<=1 ;//podwajamy wartosc 1
                        val2 >>= 1;// dzielimy na pol wartosc 2 (zapisujemy liczby calkowite)
                    }// sumujemy wartosc 1 jesli na tej samej pozycji wartosc 2 jest nieparzysta
                    System.out.print(result + " - ");
                    for (int i = 31; i >= 0; i--) {
                        System.out.print((result >> i) & 1);
                    }
                    break;

                case '/':// odejmuje licznik od mianownika dopoki sie w nim miesci
                    int count = 0;
                    int val2tmp = val2;
                    while (val2 <= val1 ) {
                        int one = 1;
                        while((result & one) >= 1)
                        {
                            result = result ^ one;
                            one <<= 1;
                        }
                        result ^=one ;
                        val2tmp = val2;
                        while (val2tmp != 0) {
                            tmp = (~val1) & val2tmp;
                            val1 ^= val2tmp;
                            val2tmp = tmp << 1;
                        }
                    }
                    System.out.print(result + " - ");
                    for (int i = 31; i >= 0; i--) {
                        System.out.print((result >> i) & 1);
                    }
                    break;
            }

        }
    }
}

