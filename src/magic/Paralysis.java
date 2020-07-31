package magic;

import java.util.Random;

import jobs.BasePlayer;

public class Paralysis implements IMagic {

    final static String MAGIC_NAME = "パライズ";
    final static int MAGIC_MP = 10;

    final static int PARALYSIS_TURN = 3;

    @Override
    public void doMagic(BasePlayer attacker, BasePlayer defender) {
        
        System.out.println(attacker.getName() + "は" + MAGIC_NAME + "を使った。");
        attacker.downMp(MAGIC_MP);

        if (successParalysis()) {
            defender.beParalysis(PARALYSIS_TURN);
            return;
        }

        System.out.println(MAGIC_NAME + "は失敗した。");
    }

    // 5分の1の確率で麻痺になる
    private boolean successParalysis() {
        Random random = new Random();
        return random.nextInt(5) == 0 ? true : false;
    }

}