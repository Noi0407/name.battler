package magic;

import jobs.BasePlayer;

public class Heal implements IMagic {

    final static String MAGIC_NAME = "ヒール";
    final static int MAGIC_MP = 20;
    final static int UP_HP = 50;

    public void doMagic(BasePlayer attacker, BasePlayer defender) {
        System.out.println(attacker.getName() + "は" + MAGIC_NAME + "を使った。");
        attacker.downMp(MAGIC_MP);

        attacker.recoveryHp(UP_HP);
    }
}
