package baseball;

import camp.nextstep.edu.missionutils.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        int keepGoing = 1;
        int strike = 0;
        int numOfBall;
        int numOfStrike;
        List<Integer> result = new ArrayList<>();
        String input = Console.readLine();

        System.out.println("숫자 야구 게임을 시작합니다.");
        while (keepGoing == 1) {
            // Get Computer Data
            List<Integer> computer = getCompGameData();

            while (strike < 3) {
                // Get user Data
                List<Integer> user = getUserGameData();
                result = compareData(computer, user);
                numOfBall = result.get(0);
                numOfStrike = result.get(1);

                // for no ball and no strike
                if (numOfBall == 0 && numOfStrike == 0) {
                    System.out.println("낫싱");
                }

                // for with strike
                printBall(numOfBall);
                strike = printStrike(numOfStrike);
            }

            printRestart();
            keepGoing = Integer.parseInt(input);

            if (keepGoing == 1) {
                strike = 0;
            } else if (keepGoing == 2) {
                return;
            } else {
                System.out.println("값이 유효하지 않습니다. 프로그램을 종료합니다.");

            }
        }
    }

    public static void printBall(int ball) {
        if (ball > 0) {
            System.out.print(ball + "볼 ");
        }
    }

    public static int printStrike(int strike) {
        if (strike > 0) {
            System.out.println(strike + "스트라이크 ");
            return strike;
        } else {
            return -1;
        }
    }

    public static void printRestart() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
    }

    /*
     * @return Random Computer Data
     */
    public static List<Integer> getCompGameData() {
        List<Integer> computer = new ArrayList<>();
        while (computer.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
        return computer;
    }

    /*
     * @return User Input Data
     */
    public static List<Integer> getUserGameData() {
        String str = "";
        char[] userStr = "".toCharArray();
        List<Integer> user = new ArrayList<Integer>();

        // Check If Input Data has 3 Numbers, and Only Includes Integer
        System.out.print("숫자를 입력해주세요 : ");
        str = Console.readLine();

        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }

        if (str.length() != 3) {
            throw new IllegalArgumentException();
        }

        userStr = str.toCharArray();

        // Check If There's Any Same Number
        for (int i = 0; i < str.length() - 1; i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (userStr[i] == userStr[j]) {
                    throw new IllegalArgumentException();
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            user.add(userStr[i] - '0');
        }

        return user;
    }

    /*
     * @return [number of Balls, number of Strikes]
     */
    public static List<Integer> compareData(List<Integer> computer, List<Integer> user) {
        List<Integer> ballStrike = new ArrayList<>();
        int ball = 0, strike = 0;

        for (int i = 0; i < user.size(); i++) {
            for (int j = 0; j < computer.size(); j++) {
                if (user.get(i) == computer.get(j)) {
                    if (i == j) {
                        strike++;
                    } else {
                        ball++;
                    }
                }
            }
        }

        ballStrike.add(ball);
        ballStrike.add(strike);

        return ballStrike;
    }
}