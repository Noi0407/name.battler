package magic;

import jobs.BasePlayer;

public class Poison implements IMagic {

    final static String MAGIC_NAME = "ポイズン";
    final static int MAGIC_MP = 10;

    final static int POISON_TURN = 3;

    @Override
    public void doMagic(BasePlayer attacker, BasePlayer defender) {
        
        System.out.println(attacker.getName() + "は" + defender.getName() + "に" + MAGIC_NAME + "を使った。");
        attacker.downMp(MAGIC_MP);

        defender.bePoisonus(POISON_TURN);
    }

}