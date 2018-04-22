// you can also use imports, for example:
// import java.util.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class Coordinates {
    private int x;
    private int y;
    private int bitX;
    private int bitY;

    public Coordinates(int x, int y, int bitX, int bitY) {
        this.x = x;
        this.y = y;
        this.bitX = bitX;
        this.bitY = bitY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getBitX() {
        return bitX;
    }



    public int getBitY() {
        return bitY;
    }


}

class Solution {
    public int solution(String[] B) {
        Coordinates coordinatesOfO = null;
        for (int i = 0; i < B.length; i++) {
            if (B[i].indexOf("O") >= 0) {
                coordinatesOfO = new Coordinates(i, B[i].indexOf("O"), -1, -1);
                break;
            }
        }
        if (coordinatesOfO != null)
            return findPossibleMoves(coordinatesOfO, B, 0);

        return -1;
    }

    private int findPossibleMoves(Coordinates coordinates, String[] B, int counter) {

        List<Coordinates> retVal = new ArrayList<Coordinates>();
        if (coordinates.getX() > 1 && coordinates.getY() > 1) {
            if (ifMovePossibile(coordinates, B, -1, -1)) {
                retVal.add(new Coordinates(coordinates.getX() - 2,
                        coordinates.getY() - 2,
                        coordinates.getX() - 1,
                        coordinates.getY() - 1));
            }
        }
        if (coordinates.getX() > 1 && coordinates.getY() < B.length - 2) {
            if (ifMovePossibile(coordinates, B, -1, 1)) {
                retVal.add(new Coordinates(coordinates.getX() - 2,
                        coordinates.getY() + 2,
                        coordinates.getX() - 1,
                        coordinates.getY() + 1));
            }
        }
        if (coordinates.getX() < B.length - 2 && coordinates.getY() > 1) {
            if (ifMovePossibile(coordinates, B, 1, -1)) {
                retVal.add(new Coordinates(coordinates.getX() + 2,
                        coordinates.getY() - 2,
                        coordinates.getX() + 1,
                        coordinates.getY() - 1));
            }
        }
        if (coordinates.getX() < B.length - 2 && coordinates.getY() < B.length - 2) {
            if (ifMovePossibile(coordinates, B, 1, 1)) {
                retVal.add(new Coordinates(coordinates.getX() + 2,
                        coordinates.getY() + 2,
                        coordinates.getX() + 1,
                        coordinates.getY() + 1));
            }
        }
        int longestPath = counter;
        for (Coordinates c : retVal) {
            String[] s = Arrays.copyOf(B, B.length);
            char[] charr = s[c.getBitX()].toCharArray();
            charr[c.getBitY()] = '.';
            s[c.getBitX()] = String.copyValueOf(charr);
            int tmp = findPossibleMoves(c, s, counter + 1);
            if (tmp > longestPath) {
                longestPath = tmp;
            }
        }
        counter = longestPath;
        return counter;
    }

    private boolean ifMovePossibile(Coordinates coordinates, String[] B, int movementX, int movementY) {
        int moveToX = coordinates.getX() + movementX *2;
        int moveToY = coordinates.getY() + movementY *2;
        int deleteX = coordinates.getX() + movementX ;
        int deleteY = coordinates.getY() + movementY ;

        if ((B[deleteX].charAt(deleteY) == 'X')) {
            if (B[moveToX].charAt(moveToY) == '.') {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void main(String [] args){
        Solution s = new Solution();
        String[] B = {"........",
                      "........",
                      "....X.X.",
                      "........",
                      "..X.X.X.",
                      "........",
                      "..X.....",
                      "...O...."};
        System.out.print(s.solution(B));
    }


}